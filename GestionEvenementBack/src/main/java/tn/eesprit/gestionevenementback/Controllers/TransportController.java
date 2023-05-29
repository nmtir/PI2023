package tn.eesprit.gestionevenementback.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.eesprit.gestionevenementback.Entities.Transport;
import tn.eesprit.gestionevenementback.Services.ITransportService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transport")
public class TransportController {
    private final ITransportService transportService;
    @PostMapping("/add")
    Transport addTransport(@RequestBody Transport transport)
    {
        return transportService.addOrUpdateTransport(transport);
    }
    @PutMapping("/update")
    Transport updateTransport(@RequestBody Transport transport){
        return transportService.addOrUpdateTransport(transport);
    }
    @GetMapping("/get/{id}")
    Transport getTransport(@PathVariable("id") Integer id){
        return transportService.retrieveTransport(id);
    }
    @GetMapping("/all")
    List<Transport> getAllTransports(){return transportService.retrieveAllTransports();}
    @DeleteMapping("/delete/{id}")
    void deleteTransport(@PathVariable("id") Integer id){
        transportService.removeTransport(id);
    }

}
