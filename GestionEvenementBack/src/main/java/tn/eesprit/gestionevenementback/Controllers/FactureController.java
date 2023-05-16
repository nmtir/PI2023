package tn.eesprit.gestionevenementback.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.eesprit.gestionevenementback.Entities.Facture;
import tn.eesprit.gestionevenementback.Services.IFactureService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/facture")
public class FactureController {
    private final IFactureService factureService;
    @PostMapping("/add")
    Facture addFacture(@RequestBody Facture facture)
    {
        return factureService.addOrUpdateFacture(facture);
    }
    @PutMapping("/update")
    Facture updateFacture(@RequestBody Facture facture){
        return factureService.addOrUpdateFacture(facture);
    }
    @GetMapping("/get/{id}")
    Facture getFacture(@PathVariable("id") Integer id){
        return factureService.retrieveFacture(id);
    }
    @GetMapping("/all")
    List<Facture> getAllFactures(){return factureService.retrieveAllFactures();}
    @DeleteMapping("/delete/{id}")
    void deleteFacture(@PathVariable("id") Integer id){
        factureService.removeFacture(id);
    }

}
