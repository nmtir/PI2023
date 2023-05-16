package tn.eesprit.gestionevenementback.Services;

import tn.eesprit.gestionevenementback.Entities.Facture;

import java.util.List;

public interface IFactureService {
    List<Facture> retrieveAllFactures();
    Facture addOrUpdateFacture(Facture Facture);
    Facture retrieveFacture(Integer id);
    void removeFacture(Integer id);
}
