package tn.eesprit.gestionevenementback.Services;

import tn.eesprit.gestionevenementback.Entities.Logistique;

import java.util.List;

public interface ILogistiqueService {
    List<Logistique> retrieveAllLogistiques();
    Logistique addOrUpdateLogistique(Logistique Logistique);
    Logistique retrieveLogistique(Integer id);
    Logistique retrieveLogistiqueByEventId(Long id);
    Logistique calculDepensesMaterielLogistique(Logistique logistique);
    void removeLogistique(Integer id);
}
