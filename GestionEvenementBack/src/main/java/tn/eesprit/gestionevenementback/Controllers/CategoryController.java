package tn.eesprit.gestionevenementback.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.eesprit.gestionevenementback.Entities.Category;
import tn.eesprit.gestionevenementback.Services.ICategoryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {
    private final ICategoryService categoryService;
    @PostMapping("/add")
    Category addCategory(@RequestBody Category category)
    {
        return categoryService.addOrUpdateCategory(category);
    }
    @PutMapping("/update")
    Category updateCategory(@RequestBody Category category){
        return categoryService.addOrUpdateCategory(category);
    }
    @GetMapping("/get/{id}")
    Category getCategory(@PathVariable("id") Integer id){
        return categoryService.retrieveCategory(id);
    }
    @GetMapping("/all")
    List<Category> getAllCategorys(){return categoryService.retrieveAllCategories();}
    @DeleteMapping("/delete/{id}")
    void deleteCategory(@PathVariable("id") Integer id){
        categoryService.removeCategory(id);
    }

}
