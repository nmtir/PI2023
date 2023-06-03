package tn.eesprit.gestionevenementback.Services;

import tn.eesprit.gestionevenementback.Entities.Reclamation;

import java.util.List;

public interface IReclamationService {

    List<Reclamation> listReclamationByUser(Long id);
    Reclamation affecteReclamatioToUser(Reclamation reclamation,Long id);
}
