package tn.eesprit.gestionevenementback.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.eesprit.gestionevenementback.Entities.Activity;
import tn.eesprit.gestionevenementback.Entities.Facture;
import tn.eesprit.gestionevenementback.Exception.ResourceNotFoundException;
import tn.eesprit.gestionevenementback.Services.IActivityService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
 @RequestMapping("/api/activity")
public class ActivityController {
     @Autowired
     IActivityService iActivityService;

    @GetMapping("/events/{id}/activity")
    public ResponseEntity<List<Activity>> getAllActivityByEventId(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {


        return new ResponseEntity<>(iActivityService.listActivityByEvent(id), HttpStatus.OK);
    }
    @PostMapping("/events/{id}/activity")
    public ResponseEntity<Activity> creatActivity(@PathVariable(value = "id") Long id,
                                                    @RequestBody Activity activity) throws ResourceNotFoundException {


        return new ResponseEntity<>(iActivityService.affecteActivityToEvent(activity,id), HttpStatus.CREATED);
    }

}
