package tn.eesprit.gestionevenementback.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.eesprit.gestionevenementback.Entities.Badword;
import tn.eesprit.gestionevenementback.Services.IBadwordService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/badword")
public class BadwordController {
    private final IBadwordService badwordService;
    @PostMapping("/add")
    Badword addBadword(@RequestBody Badword badword)
    {
        return badwordService.addOrUpdateBadword(badword);
    } @PostMapping("/add/badwords")
    List<Badword> addBadwords(@RequestBody String badword)
    {
        return badwordService.addOrUpdateBadwords(badword);
    }
    @PutMapping("/update")
    Badword updateBadword(@RequestBody Badword badword){
        return badwordService.addOrUpdateBadword(badword);
    }
    @GetMapping("/get/{id}")
    Badword getBadword(@PathVariable("id") Integer id){
        return badwordService.retrieveBadword(id);
    }
    @GetMapping("/all")
    List<Badword> getAllBadwords(){return badwordService.retrieveAllBadwords();}
    @DeleteMapping("/delete/{id}")
    void deleteBadword(@PathVariable("id") Integer id){
        badwordService.removeBadword(id);
    }

}
