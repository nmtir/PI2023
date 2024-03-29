package tn.eesprit.gestionevenementback.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.eesprit.gestionevenementback.Entities.Event;
import tn.eesprit.gestionevenementback.Entities.Transport;
import tn.eesprit.gestionevenementback.Repository.EventRepository;
import tn.eesprit.gestionevenementback.Repository.TransportRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransportServiceImpl implements ITransportService{
    private final TransportRepository TransportRepo;
    private final EventRepository EventRepo;
    @Override
    public List<Transport> retrieveAllTransports(){return TransportRepo.findAll();}
    @Override
    public Transport addOrUpdateTransport(Transport transport){return TransportRepo.save(transport);}
    @Override
    public Transport addTransportAndAssignToEvent(Long eventId,Transport transport){
            Event event=EventRepo.findById(eventId).orElse(null);
            transport.setEvent(event);
            TransportRepo.save(transport);
            event.getTransports().add(transport);
            EventRepo.save(event);
            return transport;
    }
    @Override
    public Transport retrieveTransport(Integer id){return TransportRepo.findById(id).orElse(null);}
    @Override
    public void removeTransport(Integer id){TransportRepo.deleteById(id);}

}
