package tn.eesprit.gestionevenementback.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.eesprit.gestionevenementback.Entities.Goods;
import tn.eesprit.gestionevenementback.Repository.GoodsRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GoodsServiceImp implements IGoodsService {
    private final GoodsRepository GoodsRepo;
    @Override
    public List<Goods> retrieveAllGoods(){return GoodsRepo.findAll();}
    @Override
    public Goods addOrUpdateGoods(Goods goods){return GoodsRepo.save(goods);}
    @Override
    public Goods retrieveGoods(Integer id){return GoodsRepo.findById(id).orElse(null);}
    @Override
    public void removeGoods(Integer id){GoodsRepo.deleteById(id);}

}
