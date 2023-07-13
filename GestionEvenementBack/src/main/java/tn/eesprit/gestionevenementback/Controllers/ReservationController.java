package tn.eesprit.gestionevenementback.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.eesprit.gestionevenementback.Entities.*;
import tn.eesprit.gestionevenementback.Exception.ResourceNotFoundException;
import tn.eesprit.gestionevenementback.Repository.EventRepository;
import tn.eesprit.gestionevenementback.Repository.ReservationRepository;
import tn.eesprit.gestionevenementback.Repository.UserRepository;
import tn.eesprit.gestionevenementback.Services.IReservationService;

import java.time.LocalDateTime;
import java.util.Date;
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

    private final ReservationRepository reservationRepository;


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
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
    @PostMapping("/reserver/{userId}/reservation/{eventId}")
    public ResponseEntity<?> reserver(@PathVariable (value="userId") Long userId,
                                                     @PathVariable (value="eventId") Long eventId,
                                      @RequestBody List<Long> activites
    ) {

        return  new ResponseEntity<>(iReservationService.affecteReservatioToUser(eventId,userId,activites),HttpStatus.OK);
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

@GetMapping("/getReservation/{start}/{endD}")
    public ResponseEntity<List<Reservation>> getReservation(@PathVariable
                                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date start,
                                                            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                            Date endD){

        List<Reservation> reservationList=reservationRepository.findAllByDateReservationBetweenss(start,endD);
    return new ResponseEntity<>(reservationList, HttpStatus.OK);
    }
    @GetMapping("/statistique")
    public ResponseEntity< List<Object[]>> statEvent(){
        List<Object[]> list=reservationRepository.statUser();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
