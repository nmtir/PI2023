package tn.eesprit.gestionevenementback.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.eesprit.gestionevenementback.Entities.Forum;
import tn.eesprit.gestionevenementback.Entities.Logistique;

import java.util.List;

public interface ForumRepository extends JpaRepository<Forum, Integer> {
    Forum findByEvent_EventId(Integer id);
}