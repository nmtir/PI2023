package tn.eesprit.gestionevenementback.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.eesprit.gestionevenementback.Entities.Ordre;

import java.util.List;

public interface OrdreRepository extends JpaRepository<Ordre, Integer> {
    List<Ordre> findByLogistiqueLogistiqueId(Integer id);
}