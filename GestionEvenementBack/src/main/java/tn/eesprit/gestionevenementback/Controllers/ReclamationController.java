package tn.eesprit.gestionevenementback.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.eesprit.gestionevenementback.Entities.Facture;
import tn.eesprit.gestionevenementback.Entities.Reclamation;
import tn.eesprit.gestionevenementback.Entities.User;
import tn.eesprit.gestionevenementback.Exception.ResourceNotFoundException;
import tn.eesprit.gestionevenementback.Repository.FactureRepository;
import tn.eesprit.gestionevenementback.Repository.UserRepository;
import tn.eesprit.gestionevenementback.Services.IFactureService;
import tn.eesprit.gestionevenementback.Services.IReclamationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController

@RequestMapping("/api/reclamation")
@CrossOrigin(origins = "*", maxAge = 3600)

public class ReclamationController {
    @Autowired
    IReclamationService iReclamationService;

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
            iReclamationService.affecteReclamatioToUser(reclamation,user.getId());
            return new ResponseEntity<>(HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }

}
