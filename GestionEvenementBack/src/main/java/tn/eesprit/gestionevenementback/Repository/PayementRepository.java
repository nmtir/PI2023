package tn.eesprit.gestionevenementback.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.eesprit.gestionevenementback.Entities.Payement;
import tn.eesprit.gestionevenementback.Entities.Payement;

import java.util.List;

public interface PayementRepository extends JpaRepository<Payement, Long> {
    @Query( "SELECT d.reservation ,SUM (d.sum) AS price FROM Payement d  GROUP BY d.reservation    ORDER BY price desc ")
    List<Object[]> getStatistique( );

}