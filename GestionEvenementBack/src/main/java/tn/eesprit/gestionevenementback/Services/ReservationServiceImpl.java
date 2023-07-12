package tn.eesprit.gestionevenementback.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.eesprit.gestionevenementback.Entities.Reservation;
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
    @Override
    public List<Reservation> retrieveAllReservations(){return ReservationRepo.findAll();}
    @Override
    public Reservation addOrUpdateReservation(Reservation reservation){return ReservationRepo.save(reservation);}
   @Override
    public Reservation UpdateSeated(Reservation reservation){
       Reservation r=ReservationRepo.findById(reservation.getReservationId()).orElse(null);
       r.setTransport(reservation.getTransport());
       r.setSeated(reservation.getSeated());
       return ReservationRepo.save(r);
   }
    @Override
    public Reservation retrieveReservation(Integer id){return ReservationRepo.findById(id).orElse(null);}
    @Override
    public void removeReservation(Integer id){ReservationRepo.deleteById(id);}

    public String calculateDistance(String origin, String destination) throws IOException {
        String apiKey = "GATXUWWYDFFHN4SK64F6H3X6UVUCRGMR6BXJ4JAPT2MMG5QI5VRQLQNE";
        String apiUrl = "https://api.openrouteservice.org/v2/directions/driving-car?api_key=" + apiKey
                + "&start=" + origin + "&end=" + destination;

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
