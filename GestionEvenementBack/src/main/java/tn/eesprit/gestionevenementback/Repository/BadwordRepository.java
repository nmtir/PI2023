package tn.eesprit.gestionevenementback.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.eesprit.gestionevenementback.Entities.Badword;

public interface BadwordRepository extends JpaRepository<Badword, Integer> {
}