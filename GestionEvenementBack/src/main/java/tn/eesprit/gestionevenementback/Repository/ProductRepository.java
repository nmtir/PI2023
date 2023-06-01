package tn.eesprit.gestionevenementback.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.eesprit.gestionevenementback.Entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}