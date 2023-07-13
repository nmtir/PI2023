package tn.eesprit.gestionevenementback.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.eesprit.gestionevenementback.Entities.Event;
import tn.eesprit.gestionevenementback.Entities.Reservation;
import tn.eesprit.gestionevenementback.Entities.Transport;
import tn.eesprit.gestionevenementback.Repository.EventRepository;
import tn.eesprit.gestionevenementback.Repository.ReservationRepository;
import tn.eesprit.gestionevenementback.Repository.TransportRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TransportServiceImpl implements ITransportService {
    private final TransportRepository TransportRepo;
    private final EventRepository EventRepo;
    private final ReservationRepository ReservationRepo;

    @Override
    public List<Transport> retrieveAllTransports() {
        return TransportRepo.findAll();
    }

    @Override
    public List<Transport> retrieveAllByStartingAddressAndEvent(String adr, Long eventId) {
        List<Transport> transports = TransportRepo.findAll();
        List<Transport> transports1 = new ArrayList<Transport>();
        for (Transport t : transports) {
            Set<Reservation> passengers = t.getPassangers();
            Reservation anyPassenger = passengers.stream().findAny().orElse(null);
            if (anyPassenger.getEvent().getId() == eventId) {
                if (t.getFromAdress().contains(adr)) {
                    transports1.add(t);
                }
            }
        }
        return transports1;


    }
    @Override
    public Transport addOrUpdateTransport(Transport transport){
        return TransportRepo.save(transport);
    }
    @Override
    public Transport addTransportAndAssignToEvent(Transport transport){
            return TransportRepo.save(transport);
    }
    @Override
    public Transport retrieveTransport(Integer id){return TransportRepo.findById(id).orElse(null);}
    @Override
    public void removeTransport(Integer id){TransportRepo.deleteById(id);}

}
