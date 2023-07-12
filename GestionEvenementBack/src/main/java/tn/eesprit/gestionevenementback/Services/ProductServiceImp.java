package tn.eesprit.gestionevenementback.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.eesprit.gestionevenementback.Entities.Ordre;
import tn.eesprit.gestionevenementback.Entities.Product;
import tn.eesprit.gestionevenementback.Repository.OrdreRepository;
import tn.eesprit.gestionevenementback.Repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements IProductService {
    private final ProductRepository ProductsRepo;
    private final OrdreRepository OrderRepo;
    @Override
    public List<Product> retrieveAllProducts(){return ProductsRepo.findAll();}
    @Override
    public List<Product> retrieveProductsInStock()
    {
        List<Product> products= ProductsRepo.findAll();
        List<Product> productsfinal = new ArrayList<>();

        for (Product g:products ){
            if (g.getStock()!=0){
                productsfinal.add(g);
            }


        }
        return productsfinal;
    }
    @Override
    public Product addProduct(Product product){return ProductsRepo.save(product);}
    @Override
    public Product UpdateProduct(Product product){
        Product p =ProductsRepo.findById(product.getProductId()).orElse(null);
        p.setStock(product.getStock());
        p.setNameProduct(product.getNameProduct());
        p.setPrice(product.getPrice());
        return ProductsRepo.save(p);
    }
    @Override
    public Product retrieveProduct(Integer id){return ProductsRepo.findById(id).orElse(null);}

    @Override
    public void removeProduct(Integer id){
        ProductsRepo.deleteById(id);
    }

}
