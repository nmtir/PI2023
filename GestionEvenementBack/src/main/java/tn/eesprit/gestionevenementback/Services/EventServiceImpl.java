package tn.eesprit.gestionevenementback.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.eesprit.gestionevenementback.Entities.Event;
import tn.eesprit.gestionevenementback.Repository.EventRepository;
import tn.eesprit.gestionevenementback.dto.EventRequest;
import tn.eesprit.gestionevenementback.utils.ImageUtils;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements IEventService{
    private final EventRepository eventRepository;
    @Override
    public List<Event> retrieveAllEvents(){return eventRepository.findAll();}

    @Override
    public Event addEvent(EventRequest event, MultipartFile file) throws IOException {

        Event _event=new Event(
                event.getTitle(), event.getDescription(),event.getStartDate(),event.getEndDate(),
                event.getLieu(),event.getType(),event.getLang(),event.getLat()
                 ,ImageUtils.compressImage(file.getBytes()));

        return eventRepository.save(_event);
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
