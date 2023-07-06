package tn.eesprit.gestionevenementback.Services;

import tn.eesprit.gestionevenementback.Entities.Badword;

import java.util.List;

public interface IBadwordService {
    List<Badword> retrieveAllBadwords();
    Badword addOrUpdateBadword(Badword badword);
    Badword retrieveBadword(Integer id);
    void removeBadword(Integer id);
}
