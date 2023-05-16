package tn.eesprit.gestionevenementback.Services;

import tn.eesprit.gestionevenementback.Entities.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> retrieveAllCategories();
    Category addOrUpdateCategory(Category Category);
    Category retrieveCategory(Integer id);
    void removeCategory(Integer id);
}
