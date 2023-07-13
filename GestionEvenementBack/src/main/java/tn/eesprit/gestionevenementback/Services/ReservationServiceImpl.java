package tn.eesprit.gestionevenementback.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.eesprit.gestionevenementback.Entities.Event;
import tn.eesprit.gestionevenementback.Entities.Reservation;
import tn.eesprit.gestionevenementback.Entities.Transport;
import tn.eesprit.gestionevenementback.Repository.EventRepository;
import tn.eesprit.gestionevenementback.Repository.ReservationRepository;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import tn.eesprit.gestionevenementback.Repository.TransportRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements IReservationService{
    private final ReservationRepository ReservationRepo;
    private final TransportRepository TransportRepo;
    private final EventRepository EventRepo;
    @Override
    public List<Reservation> retrieveAllReservations(){return ReservationRepo.findAll();}
    @Override
    public Reservation addOrUpdateReservation(Reservation reservation){return ReservationRepo.save(reservation);}

    @Override
    public Reservation updateReservation(Long eventId,Reservation reservation){
        Event event=EventRepo.findById(eventId).orElse(null);
        reservation.setEvent(event);
        return ReservationRepo.save(reservation);
    }
   @Override
    public Reservation UpdateSeated(Integer id,Reservation reservation) throws IOException {
       Reservation r=ReservationRepo.findById(reservation.getReservationId()).orElse(null);
       Transport t=TransportRepo.findById(id).orElse(null);
       r.setTransport(t);
       r.setSeated(reservation.getSeated());
       return ReservationRepo.save(r);
   }
   @Override
    public Reservation UpdateStatusReservation(Reservation reservation) {
      Reservation r=ReservationRepo.findById(reservation.getReservationId()).orElse(null);
      r.setStatus(reservation.getStatus());
      return ReservationRepo.save(r);
   }
    @Override
    public Reservation retrieveReservation(Integer id){return ReservationRepo.findById(id).orElse(null);}
    @Override
    public void removeReservation(Integer id){ReservationRepo.deleteById(id);}

    public static String calculateDistance(String origin, String destination) throws IOException {
        // Geocode origin and destination to get their coordinates
        double[] originCoords = geocodeLocation(origin);
        double[] destinationCoords = geocodeLocation(destination);

        if (originCoords == null || destinationCoords == null) {
            return "Coordinates not found";
        }

        String apiKey = "5b3ce3597851110001cf624837916ea6b0c44c34aeeb1a16a4c713bb";
        String apiUrl = "https://api.openrouteservice.org/v2/directions/driving-car?api_key=" + apiKey
                + "&start=" + originCoords[1] + "," + originCoords[0] + "&end=" + destinationCoords[1] + "," + destinationCoords[0]+"&country=TN";

        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        String distance = parseDistanceFromResponse(response.toString());

        return distance;
    }

    public static double[] geocodeLocation(String location) throws IOException {
        String apiUrl = "https://nominatim.openstreetmap.org/search?q=" + location + "&format=json&limit=1";

        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        Gson gson = new Gson();
        JsonArray results = gson.fromJson(response.toString(), JsonArray.class);

        if (results.size() > 0) {
            JsonObject result = results.get(0).getAsJsonObject();
            double lat = result.get("lat").getAsDouble();
            double lon = result.get("lon").getAsDouble();
            return new double[]{lat, lon};
        }

        return null;
    }

    public static String parseDistanceFromResponse(String response) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(response, JsonObject.class);

        JsonArray routes = jsonObject.getAsJsonArray("routes");
        if (routes.size() > 0) {
            JsonObject route = routes.get(0).getAsJsonObject();
            JsonObject summary = route.getAsJsonObject("summary");
            JsonElement distanceElement = summary.get("distance");

            if (distanceElement != null) {
                double distance = distanceElement.getAsDouble() / 1000; // Convert meters to kilometers
                return String.format("%.2f km", distance);
            }
        }

        return "Distance calculation error";
    }


}
