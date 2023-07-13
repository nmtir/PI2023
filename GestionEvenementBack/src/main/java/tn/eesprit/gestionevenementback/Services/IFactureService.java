package tn.eesprit.gestionevenementback.Services;

import tn.eesprit.gestionevenementback.Entities.Facture;

import javax.mail.MessagingException;
import java.util.List;

public interface IFactureService {
    Facture affecteFactureToUser(Facture facture, Long id);
    List<Facture> listFactureByUser(Long id);

    void sednFacture(String toEmail, String attachment)throws MessagingException;
}
