package tn.eesprit.gestionevenementback.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.eesprit.gestionevenementback.Entities.*;
import tn.eesprit.gestionevenementback.Exception.ResourceNotFoundException;
import tn.eesprit.gestionevenementback.Repository.EventRepository;
import tn.eesprit.gestionevenementback.Repository.UserRepository;
import tn.eesprit.gestionevenementback.Services.IReservationService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservation")
@CrossOrigin(origins = "*", maxAge = 3600)

public class ReservationController {
    private final IReservationService iReservationService;

    private final UserRepository userRepository;

    private final EventRepository eventRepository;


    @PostMapping("/user/{userId}/reservation/{eventId}")
    public ResponseEntity<?> affecteReservatioToUser(@PathVariable (value="userId") Long userId,
                                        @PathVariable (value="eventId") Long eventId
                                        ) {

        Optional<User> _user = userRepository.findById(userId);
        User user = _user.get();
        Optional<Event> event = eventRepository.findById(eventId);
        if (user.getEvents().contains(event.get())) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {

            return new ResponseEntity<>(iReservationService.affecteReservatioToUser(eventId, userId), HttpStatus.OK);
        }
    }

    @GetMapping("/users/reservations")
    public ResponseEntity<List<Reservation>> getAllReservation()   {


        return new ResponseEntity<>(iReservationService.retrieveAllReservations(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteReservation(@PathVariable Long id){
        iReservationService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
