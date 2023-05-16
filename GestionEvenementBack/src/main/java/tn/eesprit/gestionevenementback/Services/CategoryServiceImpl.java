package tn.eesprit.gestionevenementback.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.eesprit.gestionevenementback.Entities.Category;
import tn.eesprit.gestionevenementback.Repository.CategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService{
    private final CategoryRepository CategoryRepo;
    @Override
    public List<Category> retrieveAllCategories(){return CategoryRepo.findAll();}
    @Override
    public Category addOrUpdateCategory(Category category){return CategoryRepo.save(category);}
    @Override
    public Category retrieveCategory(Integer id){return CategoryRepo.findById(id).orElse(null);}
    @Override
    public void removeCategory(Integer id){CategoryRepo.deleteById(id);}

}
