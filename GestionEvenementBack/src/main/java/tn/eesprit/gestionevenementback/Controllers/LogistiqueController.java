package tn.eesprit.gestionevenementback.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.eesprit.gestionevenementback.Entities.Logistique;
import tn.eesprit.gestionevenementback.Services.ILogistiqueService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/logistique")
public class LogistiqueController {
    private final ILogistiqueService logistiqueService;
    @PostMapping("/add")
    Logistique addLogistique(@RequestBody Logistique logistique)
    {
        return logistiqueService.addOrUpdateLogistique(logistique);
    }
    @PutMapping("/update")
    Logistique updateLogistique(@RequestBody Logistique logistique){
        return logistiqueService.addOrUpdateLogistique(logistique);
    }
    @PutMapping("/update/depences")
    Logistique updateDepencesLogistique(@RequestBody Logistique logistique){
        return logistiqueService.calculDepencesMaterielLogistique(logistique);
    }
    @GetMapping("/get/{id}")
    Logistique getLogistique(@PathVariable("id") Integer id){
        return logistiqueService.retrieveLogistique(id);
    }
    @GetMapping("/get/event/{id}")
    Logistique getLogistiqueByEventId(@PathVariable("id") Integer id){
        return logistiqueService.retrieveLogistiqueByEventId(id);
    }
    @GetMapping("/all")
    List<Logistique> getAllLogistiques(){return logistiqueService.retrieveAllLogistiques();}
    @DeleteMapping("/delete/{id}")
    void deleteLogistique(@PathVariable("id") Integer id){
        logistiqueService.removeLogistique(id);
    }

}
