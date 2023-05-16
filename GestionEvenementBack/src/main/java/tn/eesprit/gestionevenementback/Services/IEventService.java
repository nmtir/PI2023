package tn.eesprit.gestionevenementback.Services;

import tn.eesprit.gestionevenementback.Entities.Event;

import java.util.List;

public interface IEventService {
    List<Event> retrieveAllEvents();
    Event addOrUpdateEvent(Event Event);
    Event retrieveEvent(Integer id);
    void removeEvent(Integer id);
}
