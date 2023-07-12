package tn.eesprit.gestionevenementback.Services;

import tn.eesprit.gestionevenementback.Entities.Facture;

import java.util.List;

public interface IFactureService {
    Facture affecteFactureToUser(Facture facture, Long id);
    List<Facture> listFactureByUser(Long id);
}
