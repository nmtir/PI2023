package tn.eesprit.gestionevenementback.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.eesprit.gestionevenementback.Entities.Housing;
import tn.eesprit.gestionevenementback.Services.IHousingService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/housing")
public class HousingController {
    private final IHousingService housingService;
    @PostMapping("/add")
    Housing addHousing(@RequestBody Housing housing)
    {
        return housingService.addOrUpdateHousing(housing);
    }
    @PutMapping("/update")
    Housing updateHousing(@RequestBody Housing housing){
        return housingService.addOrUpdateHousing(housing);
    }
    @GetMapping("/get/{id}")
    Housing getHousing(@PathVariable("id") Integer id){
        return housingService.retrieveHousing(id);
    }
    @GetMapping("/all")
    List<Housing> getAllHousings(){return housingService.retrieveAllHousings();}
    @DeleteMapping("/delete/{id}")
    void deleteHousing(@PathVariable("id") Integer id){
        housingService.removeHousing(id);
    }

}
