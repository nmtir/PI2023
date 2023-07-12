package tn.eesprit.gestionevenementback.Services;

import tn.eesprit.gestionevenementback.Entities.Product;

import java.util.List;

public interface IProductService {
    List<Product> retrieveAllProducts();
    Product addOrUpdateProduct(Product Product);
    Product retrieveProduct(Integer id);
    void removeProduct(Integer id);
    public List<Product> retrieveProductsInStock();
}
