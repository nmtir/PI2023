package tn.eesprit.gestionevenementback.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.eesprit.gestionevenementback.Entities.Facture;
import tn.eesprit.gestionevenementback.Entities.Reservation;
import tn.eesprit.gestionevenementback.Entities.User;
import tn.eesprit.gestionevenementback.Exception.ResourceNotFoundException;
import tn.eesprit.gestionevenementback.Repository.FactureRepository;
import tn.eesprit.gestionevenementback.Repository.ReservationRepository;
import tn.eesprit.gestionevenementback.Repository.UserRepository;
import tn.eesprit.gestionevenementback.Services.EmailService;
import tn.eesprit.gestionevenementback.Services.IFactureService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/facture")
@CrossOrigin(origins = "*", maxAge = 3600)

public class FactureController {
    private final IFactureService iFactureService;

    private final UserRepository userRepository;


    private final ReservationRepository reservationRepository;


    private final EmailService emailService;
    @GetMapping("/users/{id}/factures")
    public ResponseEntity<List<Facture>> getAllFactureByUserid(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {


        return new ResponseEntity<>(iFactureService.listFactureByUser(id), HttpStatus.OK);
    }
    @PostMapping("/users/{id}/facture/{resId}")
    public ResponseEntity<Facture> creatFacture(@PathVariable(value = "id") Long id,@PathVariable(value = "resId") Long resId,
                                                        @RequestBody Facture facture) throws ResourceNotFoundException {


        Optional<Reservation> reservation=reservationRepository.findById(resId);

        Optional<User> user=userRepository.findById(id);
        String txt="Votre reservation a éte confirmer ";
        reservation.get().setStatus("Confirmed");
        reservationRepository.save(reservation.get());

        emailService.sendMailConfirmation(user.get().getEmail(),txt);

        return new ResponseEntity<>(iFactureService.affecteFactureToUser(facture,id), HttpStatus.CREATED);
    }

}
