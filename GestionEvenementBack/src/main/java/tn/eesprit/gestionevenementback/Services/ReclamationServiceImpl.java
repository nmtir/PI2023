package tn.eesprit.gestionevenementback.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import tn.eesprit.gestionevenementback.Entities.Facture;
import tn.eesprit.gestionevenementback.Entities.Reclamation;
import tn.eesprit.gestionevenementback.Entities.User;
import tn.eesprit.gestionevenementback.Exception.ResourceNotFoundException;
import tn.eesprit.gestionevenementback.Repository.ReclamationRepository;
import tn.eesprit.gestionevenementback.Repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReclamationServiceImpl implements IReclamationService {
    @Autowired
    ReclamationRepository reclamationRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public List<Reclamation> listReclamationByUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found user with id = " + id));
        List<Reclamation> reclamations = new ArrayList<Reclamation>();
        reclamations.addAll(user.getReclamations());
            System.out.println(reclamations);
        return reclamations;
    }
    @Override
    public Reclamation affecteReclamatioToUser(Reclamation reclamation, Long id) {
        Reclamation _reclamation = userRepository.findById(id).map((User user) -> {
            user.getReclamations().add(reclamation);
            return reclamationRepository.save(reclamation);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found User with id = " + id));
        return _reclamation;
    }


}
