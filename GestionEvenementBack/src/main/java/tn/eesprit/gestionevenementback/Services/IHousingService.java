package tn.eesprit.gestionevenementback.Services;

import tn.eesprit.gestionevenementback.Entities.Housing;

import java.util.List;

public interface IHousingService {
    List<Housing> retrieveAllHousings();
    Housing addOrUpdateHousing(Housing housing);
    Housing retrieveHousing(Integer id);
    void removeHousing(Integer id);
}
