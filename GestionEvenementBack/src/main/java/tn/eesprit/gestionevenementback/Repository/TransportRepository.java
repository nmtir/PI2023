package tn.eesprit.gestionevenementback.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.eesprit.gestionevenementback.Entities.Transport;

import java.util.List;

public interface TransportRepository extends JpaRepository<Transport, Integer> {
}