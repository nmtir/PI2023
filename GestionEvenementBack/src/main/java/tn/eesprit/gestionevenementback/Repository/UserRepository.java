package tn.eesprit.gestionevenementback.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.eesprit.gestionevenementback.Entities.User;
import tn.eesprit.gestionevenementback.Entities.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

}