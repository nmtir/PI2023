package tn.eesprit.gestionevenementback.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.eesprit.gestionevenementback.Entities.User;
import tn.eesprit.gestionevenementback.Entities.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByToken(String token);
    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
    List<User> findUserByEmail (String value);


    Optional<User> findByEmail(String email);

   // @Query("SELECT  COUNT(c.events) as counts FROM User AS c GROUP BY c.events ORDER BY counts DESC")
   // @Query("SELECT  c.events from User as c GROUP BY c.events_Id ")
    //@Query("select COUNT(g.events) as all_event from User as g join Event as u on g.events.id = u.id  group by g.events")

  //  @Query( "SELECT d.username ,COUNT(e) FROM User d JOIN d.events e GROUP BY d.username")
      int limit = 10;


}