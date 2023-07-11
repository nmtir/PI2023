package tn.eesprit.gestionevenementback.Services;

import tn.eesprit.gestionevenementback.Entities.Product;

import java.util.List;

public interface IProductService {
    List<Product> retrieveAllProducts();
    Product addProduct(Product product);
    Product UpdateProduct(Product product);
    Product retrieveProduct(Integer id);
    void removeProduct(Integer id);
    public List<Product> retrieveProductsInStock();
}
