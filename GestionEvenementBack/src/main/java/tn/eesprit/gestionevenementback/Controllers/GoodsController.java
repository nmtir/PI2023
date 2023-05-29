package tn.eesprit.gestionevenementback.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.eesprit.gestionevenementback.Entities.Goods;
import tn.eesprit.gestionevenementback.Services.IGoodsService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/goods")
public class GoodsController {
    private final IGoodsService goodsService;
    @PostMapping("/add")
    Goods addGoods(@RequestBody Goods goods)
    {
        return goodsService.addOrUpdateGoods(goods);
    }
    @PutMapping("/update")
    Goods updateGoods(@RequestBody Goods goods){
        return goodsService.addOrUpdateGoods(goods);
    }
    @GetMapping("/get/{id}")
    Goods getGoods(@PathVariable("id") Integer id){
        return goodsService.retrieveGoods(id);
    }
    @GetMapping("/all")
    List<Goods> getAllGoods(){return goodsService.retrieveAllGoods();}
    @DeleteMapping("/delete/{id}")
    void deleteGoods(@PathVariable("id") Integer id){
        goodsService.removeGoods(id);
    }

}
