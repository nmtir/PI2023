package tn.eesprit.gestionevenementback.Services;

import tn.eesprit.gestionevenementback.Entities.Activity;
import tn.eesprit.gestionevenementback.Entities.Facture;

import java.util.List;

public interface IActivityService {
    Activity affecteActivityToEvent(Activity activity, Long id);
    List<Activity> listActivityByEvent(Long id);
}