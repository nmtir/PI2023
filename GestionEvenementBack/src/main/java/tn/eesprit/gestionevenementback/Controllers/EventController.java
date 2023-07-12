package tn.eesprit.gestionevenementback.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.eesprit.gestionevenementback.Entities.Activity;
import tn.eesprit.gestionevenementback.Entities.Event;
import tn.eesprit.gestionevenementback.Entities.User;
import tn.eesprit.gestionevenementback.Exception.ResourceNotFoundException;
import tn.eesprit.gestionevenementback.Repository.ActivityRepository;
import tn.eesprit.gestionevenementback.Repository.EventRepository;
import tn.eesprit.gestionevenementback.Repository.ReservationRepository;
import tn.eesprit.gestionevenementback.Repository.UserRepository;
import tn.eesprit.gestionevenementback.Services.IEventService;
import org.springframework.http.ResponseEntity;
import tn.eesprit.gestionevenementback.dto.EventRequest;
import tn.eesprit.gestionevenementback.utils.ImageUtils;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/event")
public class EventController {
    private final IEventService iEventService;
    private final EventRepository eventRepository;

    private final UserRepository userRepository;

    private  final ReservationRepository reservationRepository;

    private final ActivityRepository activityRepository;
    @PostMapping("/add-events")
    public ResponseEntity<Event> createEvent(@RequestParam("file") MultipartFile file,
                                             @RequestParam("event") String event) throws IOException {
        EventRequest newEvent = new ObjectMapper().readValue(event, EventRequest.class);




        Event _event = iEventService.addEvent(newEvent,file);
        return new ResponseEntity<>(_event, HttpStatus.CREATED);
    }

    @GetMapping(path="/event/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getPhoto(@PathVariable("id") Long id) throws Exception{
        Optional<Event> emp = eventRepository.findById(id);
        if(emp.isPresent()) {
            System.out.println(emp.get().toString());

            byte[] images = ImageUtils.decompressImage(emp.get().getImageData());
            return images;
        }else{
            return null;
        }
    }
@GetMapping("/get-event/{id}")
public ResponseEntity<Event> getEvent(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
    Event event = eventRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Not found user with id = " + id));
    return new ResponseEntity<>(event, HttpStatus.OK);

    }


    @GetMapping("/all-events")
    public ResponseEntity<List<Event>> getAllEvents(){
       return  new ResponseEntity<>( iEventService.retrieveAllEvents(), HttpStatus.OK);
    }
    @DeleteMapping("/delete-event/{id}")
    public ResponseEntity<HttpStatus> deleteEvent(@PathVariable("id") long id) {
        iEventService.deleteEvent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping ("/update-event/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable(value = "id") Long id,@RequestBody Event event) throws ResourceNotFoundException {
        Event _event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found user with id = " + id));
        _event.setTitle(event.getTitle());
        _event.setLieu(event.getLieu());
        _event.setEndDate(event.getEndDate());
        _event.setStartDate(event.getStartDate());
        _event.setDescription(event.getDescription());
        _event.setType(event.getType());
        eventRepository.save(_event);

        return new ResponseEntity<>(_event, HttpStatus.OK);

    }
    @GetMapping("/event-by-type")
    public ResponseEntity< List<Object[]>> getEventByType(){
        List<Object[]> list=eventRepository.countTotalEventsByType();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/findAllByStartDateBetween/{start}/{end}")
    public ResponseEntity<List <Event>> findAllByStartDateBetween(@PathVariable("start")    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date start,
                                                  @PathVariable("end")    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date end){
        return new ResponseEntity<>(iEventService.findAllByStartDateBetween(start,end),HttpStatus.OK);

    }
    @GetMapping("/findEventByTitleContainingOrDescriptionContaining/{title}/{description}")
    public ResponseEntity<List <Event>> findEventByTitleContainingOrDescriptionContaining(@PathVariable("title")
                                                                                              String title     ,
                                                                  @PathVariable("description")    String description){
        return new ResponseEntity<>(iEventService.findEventByTitleContainingOrDescriptionContaining(title,description),HttpStatus.OK);

    }
    @DeleteMapping("/deleteActivity/{id}/{activitieId}")
    public void deleteActivity(@PathVariable Long id,@PathVariable Long activitieId){

        Event _event=eventRepository.findById(id).get();

   Activity _activity=activityRepository.findByActivityId(activitieId).get();

   System.out.println(activitieId);


boolean activities=_event.getActivites().remove(_activity);
        eventRepository.save(_event);


    }

    @GetMapping("/statEvent")

    public ResponseEntity< List<Object[]>> statEvent(){

        List<Object[]> list=reservationRepository.statEvent();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    @PutMapping("/like/{eventId}/{id}")

    public ResponseEntity<HttpStatus> like(@PathVariable Long eventId,@PathVariable Long id) {

        Optional<Event> event = eventRepository.findById(eventId);

        Optional<User> user = userRepository.findById(id);
        User _user = user.get();

        Event _event = event.get();

        if (_event.getLikes().contains(_user)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            _event.getLikes().add(_user);

            eventRepository.save(_event);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PutMapping("/deslike/{eventId}/{id}")

    public ResponseEntity<HttpStatus> deslike(@PathVariable Long eventId,@PathVariable Long id) {
        Optional<Event> event = eventRepository.findById(eventId);
        Optional<User> user = userRepository.findById(id);
        User _user = user.get();
        Event _event = event.get();
        if (_event.getDeslikes().contains(_user)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            _event.getDeslikes().add(_user);
            eventRepository.save(_event);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

}



