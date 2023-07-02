package tn.eesprit.gestionevenementback.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.eesprit.gestionevenementback.Entities.Facture;
import tn.eesprit.gestionevenementback.Entities.Reclamation;
import tn.eesprit.gestionevenementback.Entities.User;
import tn.eesprit.gestionevenementback.Exception.ResourceNotFoundException;
import tn.eesprit.gestionevenementback.Repository.FactureRepository;
import tn.eesprit.gestionevenementback.Repository.ReclamationRepository;
import tn.eesprit.gestionevenementback.Repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service

public class FactureServiceImpl implements IFactureService{
    @Autowired
    FactureRepository factureRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public List<Facture> listFactureByUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found user with id = " + id));
        List<Facture> factures = new ArrayList<Facture>();
        factures.addAll(user.getFactures());

        return factures;
    }
    @Override
    public Facture affecteFactureToUser(Facture facture, Long id) {
        Facture _facture = userRepository.findById(id).map((User user) -> {
            user.getFactures().add(facture);
            return factureRepository.save(facture);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found User with id = " + id));
        return _facture;
    }

}
