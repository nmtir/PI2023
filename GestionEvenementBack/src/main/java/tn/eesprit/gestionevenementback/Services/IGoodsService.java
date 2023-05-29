package tn.eesprit.gestionevenementback.Services;

import tn.eesprit.gestionevenementback.Entities.Goods;

import java.util.List;

public interface IGoodsService {
    List<Goods> retrieveAllGoods();
    Goods addOrUpdateGoods(Goods Goods);
    Goods retrieveGoods(Integer id);
    void removeGoods(Integer id);
}
