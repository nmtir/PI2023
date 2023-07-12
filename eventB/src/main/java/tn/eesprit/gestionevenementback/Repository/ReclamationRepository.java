package tn.eesprit.gestionevenementback.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.eesprit.gestionevenementback.Entities.Reclamation;

import java.util.Date;
import java.util.List;

public interface ReclamationRepository extends JpaRepository<Reclamation,Long> {
    List<Reclamation> findAllByDateReclamationBetween(Date start, Date end);
}
