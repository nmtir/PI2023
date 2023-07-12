package tn.eesprit.gestionevenementback.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.eesprit.gestionevenementback.Entities.Activity;

import java.util.List;
import java.util.Optional;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

    Optional<Activity> findByActivityId(Long ActivityId);
}