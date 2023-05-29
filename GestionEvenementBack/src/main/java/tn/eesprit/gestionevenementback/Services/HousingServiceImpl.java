package tn.eesprit.gestionevenementback.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.eesprit.gestionevenementback.Entities.Housing;
import tn.eesprit.gestionevenementback.Repository.HousingRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HousingServiceImpl implements IHousingService{
    private final HousingRepository HousingRepo;
    @Override
    public List<Housing> retrieveAllHousings(){return  HousingRepo.findAll();}
    @Override
    public Housing addOrUpdateHousing(Housing housing){return HousingRepo.save(housing);}
    @Override
    public Housing retrieveHousing(Integer id){return HousingRepo.findById(id).orElse(null);}
    @Override
    public void removeHousing(Integer id){HousingRepo.deleteById(id);}

}
