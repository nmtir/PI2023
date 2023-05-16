package tn.eesprit.gestionevenementback.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.eesprit.gestionevenementback.Entities.Category;
import tn.eesprit.gestionevenementback.Entities.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}