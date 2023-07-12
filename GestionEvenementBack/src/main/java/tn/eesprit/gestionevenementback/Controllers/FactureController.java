package tn.eesprit.gestionevenementback.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.eesprit.gestionevenementback.Entities.Facture;
import tn.eesprit.gestionevenementback.Entities.Payement;
import tn.eesprit.gestionevenementback.Entities.Reservation;
import tn.eesprit.gestionevenementback.Entities.User;
import tn.eesprit.gestionevenementback.Exception.ResourceNotFoundException;
import tn.eesprit.gestionevenementback.Repository.FactureRepository;
import tn.eesprit.gestionevenementback.Repository.PayementRepository;
import tn.eesprit.gestionevenementback.Repository.ReservationRepository;
import tn.eesprit.gestionevenementback.Repository.UserRepository;
import tn.eesprit.gestionevenementback.Services.EmailService;
import tn.eesprit.gestionevenementback.Services.IFactureService;
import tn.eesprit.gestionevenementback.Services.PayementServiceImpl;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/facture")
@CrossOrigin(origins = "*", maxAge = 3600)

public class FactureController {
    private final IFactureService iFactureService;

    private final PayementRepository payementRepository;

    private final UserRepository userRepository;


    private final ReservationRepository reservationRepository;


    private final EmailService emailService;
    @GetMapping("/users/{id}/factures")
    public ResponseEntity<List<Facture>> getAllFactureByUserid(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {


        return new ResponseEntity<>(iFactureService.listFactureByUser(id), HttpStatus.OK);
    }
    @PostMapping("/api/facture/{resId}/{id}")
    public ResponseEntity<Facture> creatFacture(@PathVariable(value = "resId") Long resId,@PathVariable(value = "id") Long id,
                                                        @RequestBody Double sum) throws ResourceNotFoundException {


        Optional<Reservation> reservation=reservationRepository.findById(resId);

        Payement payement=new Payement(reservation.get(),sum);
        payementRepository.save(payement);
        Optional<User> user=userRepository.findById(id);
        String txt="Votre reservation a éte confirmer ";
        reservation.get().setStatus("Confirmed");
        reservationRepository.save(reservation.get());

        emailService.sendMailConfirmation(user.get().getEmail(),txt);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
@PostMapping("/sendFacture/{attechment}")
    public ResponseEntity<?> sendFacture(@RequestBody String to,@PathVariable String attechment) throws MessagingException {
        iFactureService.sednFacture(to,attechment);
        return new ResponseEntity<>("",HttpStatus.OK);
}


}
