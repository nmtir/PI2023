package tn.eesprit.gestionevenementback.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.eesprit.gestionevenementback.Entities.User;
import tn.eesprit.gestionevenementback.Entities.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);
    Optional<User> findByToken(String token);
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
    List<User> findByEmailContaining (String value);
    @Query( "SELECT d.username , d.nbConnexion FROM User d  ORDER BY d.nbConnexion desc ")
    List<Object[]> statUser( );
}