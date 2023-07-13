package tn.eesprit.gestionevenementback.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.eesprit.gestionevenementback.Entities.Reservation;
import tn.eesprit.gestionevenementback.Services.IReservationService;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservation")
public class ReservationController {
    private final IReservationService reservationService;
    @PostMapping("/add")
    Reservation addReservation(@RequestBody Reservation reservation)
    {
        return reservationService.addOrUpdateReservation(reservation);
    }
    @PostMapping("/user/{userid}/reservation/{eventid}")
        Reservation AddReservation(@PathVariable("userid")Long userId,@PathVariable("eventid")Long eventId, @RequestBody Reservation reservation)
    {
        return reservationService.updateReservation(eventId,reservation);
    }
    @PutMapping("/update/status")
    Reservation updateReservation(@RequestBody Reservation reservation){
        return reservationService.UpdateStatusReservation(reservation);
    }
    @PutMapping("/update/seated/{id}")
    Reservation updateSeated(@PathVariable("id")Integer id,@RequestBody Reservation reservation) throws IOException {
        return reservationService.UpdateSeated (id,reservation);
    }
    @GetMapping("/get/{id}")
    Reservation getReservation(@PathVariable("id") Integer id){
        return reservationService.retrieveReservation(id);
    }
    @GetMapping("/all")
    List<Reservation> getAllReservations(){return reservationService.retrieveAllReservations();}
    @DeleteMapping("/delete/{id}")
    void deleteReservation(@PathVariable("id") Integer id){
        reservationService.removeReservation(id);
    }

}
