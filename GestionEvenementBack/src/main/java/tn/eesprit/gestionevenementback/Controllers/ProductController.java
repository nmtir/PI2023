package tn.eesprit.gestionevenementback.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.eesprit.gestionevenementback.Entities.Product;
import tn.eesprit.gestionevenementback.Services.IProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final IProductService productService;
    @PostMapping("/add")
    Product addProduct(@RequestBody Product product)
    {
        return productService.addOrUpdateProduct(product);
    }
    @PutMapping("/update")
    Product updateProduct(@RequestBody Product product){
        return productService.addOrUpdateProduct(product);
    }
    @GetMapping("/get/{id}")
    Product getProduct(@PathVariable("id") Integer id){
        return productService.retrieveProduct(id);
    }
    @GetMapping("/all")
    List<Product> getAllProducts(){return productService.retrieveAllProducts();}
    @GetMapping("/stock")
    List<Product> getAllProductsInStock(){return productService.retrieveProductsInStock();}
    @DeleteMapping("/delete/{id}")
    void deleteProducts(@PathVariable("id") Integer id){
        productService.removeProduct(id);
    }

}
