package tn.eesprit.gestionevenementback.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.eesprit.gestionevenementback.Entities.Event;
import tn.eesprit.gestionevenementback.Repository.EventRepository;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements IEventService{
    private final EventRepository eventRepository;
    @Override
    public List<Event> retrieveAllEvents(){return eventRepository.findAll();}

    @Override
    public Event addEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public List<Event> findAllByStartDateBetween(Date start, Date end) {
        return eventRepository.findAllByStartDateBetween(start,end);
    }

    @Override
    public List<Event> findEventByTitleContainingOrDescriptionContaining(String title, String description) {
        return eventRepository.findEventByTitleContainingOrDescriptionContaining(title,description);
    }


    @Override
    public void  deleteEvent (Long id){
        eventRepository.deleteById(id);
    }

}
