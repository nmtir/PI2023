package tn.eesprit.gestionevenementback.Controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.eesprit.gestionevenementback.Entities.Facture;
import tn.eesprit.gestionevenementback.Entities.Reclamation;
import tn.eesprit.gestionevenementback.Entities.Reservation;
import tn.eesprit.gestionevenementback.Entities.User;
import tn.eesprit.gestionevenementback.Exception.ResourceNotFoundException;
import tn.eesprit.gestionevenementback.Repository.FactureRepository;
import tn.eesprit.gestionevenementback.Repository.ReclamationRepository;
import tn.eesprit.gestionevenementback.Repository.UserRepository;
import tn.eesprit.gestionevenementback.Services.EmailService;
import tn.eesprit.gestionevenementback.Services.IFactureService;
import tn.eesprit.gestionevenementback.Services.IReclamationService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController

@RequestMapping("/api/reclamation")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Reclamation", description = "Reclamation management APIs")
public class ReclamationController {
    @Autowired
    IReclamationService iReclamationService;

    @Autowired
    ReclamationRepository reclamationRepository;

    @Autowired
    EmailService emailService;

    @Autowired
    UserRepository userRepository;
    @GetMapping("/users/{id}/reclamations")
    public ResponseEntity<List<Reclamation>> getAllReclamationByUserid(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {


        return new ResponseEntity<>(iReclamationService.listReclamationByUser(id), HttpStatus.OK);
    }
    @PostMapping("/users/{username}/reclamation")
    public ResponseEntity<HttpStatus> creatReclamation(@PathVariable(value = "username") String username,
                                                       @RequestBody Reclamation reclamation) throws ResourceNotFoundException {

        Optional<User> _user=userRepository.findByUsername(username);
        if(_user.isPresent()){
            User user=_user.get();
            emailService.sendMailConfirmation(user.getEmail(),"Merci de nous envoyons votre Reclamation. Nous allons l'examiner attentivement.");
            iReclamationService.affecteReclamatioToUser(reclamation,user.getId());
            return new ResponseEntity<>(HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }



    }

    @GetMapping("/filterByDate/{start}/{end}")
    public ResponseEntity<List<Reclamation>> getReclmation( @PathVariable(value = "start")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date start, @PathVariable(value = "end")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date end){

        List<Reclamation> reclamationList=reclamationRepository.findAllByDateReclamationBetween(start,end);
        return new ResponseEntity<>(reclamationList, HttpStatus.OK);
    }
}
