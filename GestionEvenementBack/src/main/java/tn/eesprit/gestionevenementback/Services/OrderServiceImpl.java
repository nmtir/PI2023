package tn.eesprit.gestionevenementback.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.eesprit.gestionevenementback.Entities.Logistique;
import tn.eesprit.gestionevenementback.Entities.Ordre;
import tn.eesprit.gestionevenementback.Entities.Product;
import tn.eesprit.gestionevenementback.Repository.LogistiqueRepository;
import tn.eesprit.gestionevenementback.Repository.OrdreRepository;
import tn.eesprit.gestionevenementback.Repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements IOrderService{
    private final OrdreRepository orderRepo;
    private final ProductRepository productRepo;
    private final LogistiqueRepository logistiqueRepo;
    @Override
    public List<Ordre> retrieveAllOrders(){return orderRepo.findAll();}
    @Override
    public Ordre addOrUpdateOrder(Ordre order){return orderRepo.save(order);}
    @Override
    public Ordre UpdateOrder(Integer qty,Integer id){

        Ordre o=orderRepo.findById(id).orElse(null);
        o.setQuantity(qty);
        return orderRepo.save(o);
    }
    @Override
    public Ordre addOrderAndAssignProduct(Product product,Integer quantity,Integer id)
    {
        Ordre ordre=new Ordre();
        Logistique logistique=logistiqueRepo.findById(id).orElse(null);
        ordre.setLogistique(logistique);
        ordre.setProduct(product);
        ordre.setQuantity(quantity);
        return orderRepo.save(ordre);
    }
    @Override
    public Ordre retrieveOrder(Integer id){return orderRepo.findById(id).orElse(null);}
    public List<Ordre> retrieveProductOrdersReservedForEvent(Integer id)
    {
        List<Ordre> ordres = orderRepo.findByLogistiqueLogistiqueId(id);
        List<Ordre> products=new ArrayList<>();
        for (Ordre o:ordres ){
            if (o.getProduct()!=null){
               products.add(o);
            }
        }
        return products;
    }
    @Override
    public void removeOrder(Integer id){orderRepo.deleteById(id);}

}
