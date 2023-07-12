package tn.eesprit.gestionevenementback.Services;

import org.springframework.web.multipart.MultipartFile;
import tn.eesprit.gestionevenementback.Entities.Event;
import tn.eesprit.gestionevenementback.dto.EventRequest;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface IEventService {
    List<Event> retrieveAllEvents();
    Event addEvent(EventRequest event, MultipartFile file) throws IOException;

    List<Event> findAllByStartDateBetween(Date start, Date end);

    List<Event> findEventByTitleContainingOrDescriptionContaining(String title,String description);

    public void  deleteEvent (Long id);
}
