package tn.eesprit.gestionevenementback.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.eesprit.gestionevenementback.Entities.Event;
import tn.eesprit.gestionevenementback.Services.IEventService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/event")
public class EventController {
    private final IEventService eventService;
    @PostMapping("/add")
    Event addEvent(@RequestBody Event event)
    {
        return eventService.addOrUpdateEvent(event);
    }
    @PutMapping("/update")
    Event updateEvent(@RequestBody Event event){
        return eventService.addOrUpdateEvent(event);
    }
    @GetMapping("/get/{id}")
    Event getEvent(@PathVariable("id") Integer id){
        return eventService.retrieveEvent(id);
    }
    @GetMapping("/all")
    List<Event> getAllEvents(){return eventService.retrieveAllEvents();}
    @DeleteMapping("/delete/{id}")
    void deleteEvent(@PathVariable("id") Integer id){
        eventService.removeEvent(id);
    }

}
