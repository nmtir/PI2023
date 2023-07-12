package tn.eesprit.gestionevenementback.Services;

import tn.eesprit.gestionevenementback.Entities.Event;

import java.util.Date;
import java.util.List;

public interface IEventService {
    List<Event> retrieveAllEvents();
    Event addEvent(Event event);

    List<Event> findAllByStartDateBetween(Date start, Date end);

    List<Event> findEventByTitleContainingOrDescriptionContaining(String title,String description);

    public void  deleteEvent (Long id);
}
