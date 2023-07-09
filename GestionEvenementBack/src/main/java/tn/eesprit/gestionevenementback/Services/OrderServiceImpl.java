package tn.eesprit.gestionevenementback.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.eesprit.gestionevenementback.Entities.Logistique;
import tn.eesprit.gestionevenementback.Entities.Ordre;
import tn.eesprit.gestionevenementback.Entities.Product;
import tn.eesprit.gestionevenementback.Repository.LogistiqueRepository;
import tn.eesprit.gestionevenementback.Repository.OrdreRepository;
import tn.eesprit.gestionevenementback.Repository.ProductRepository;

import javax.persistence.criteria.CriteriaBuilder;
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
    public Ordre UpdateOrder(Integer qty,Ordre ordre){

        Ordre o=orderRepo.findById(ordre.getOrderId()).orElse(null);
        Product p =productRepo.findById(o.getProduct().getProductId()).orElse(null);

        if ((p.getStock()+o.getQuantity())>qty)
        {
            Integer oldStock=p.getStock();
            Integer newStock=(oldStock+o.getQuantity())-qty;
            p.setStock(newStock);
            o.setQuantity(qty);
            productRepo.save(p);

        }
        return orderRepo.save(o);

    }
    @Override
    public Ordre addOrderAndAssignProduct(Product product,Integer quantity,Integer id)
    {
        Product p=productRepo.findById(product.getProductId()).orElse(null);
        List<Ordre> ordres = orderRepo.findByLogistiqueLogistiqueId(id);
        Boolean checkexistance=false;
        for (Ordre ordre:ordres){
        if (ordre.getProduct().getProductId()==p.getProductId()){
            checkexistance=true;
        }
        }
        Ordre ordre=new Ordre();
        Logistique logistique=logistiqueRepo.findById(id).orElse(null);
        if (p.getStock()>quantity && checkexistance==false )
            {
                Integer oldStock=product.getStock();
                Integer newStock=oldStock-quantity;
                p.setStock(newStock);
                productRepo.save(p);
                ordre.setLogistique(logistique);
                ordre.setProduct(p);
                ordre.setQuantity(quantity);
                return orderRepo.save(ordre);

            }
        else return ordre;

    }
    @Override
    public Ordre retrieveOrder(Integer id){return orderRepo.findById(id).orElse(null);}
    @Override
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
    public void removeOrder(Integer id){
        Ordre o=orderRepo.findById(id).orElse(null);
        Product p =productRepo.findById(o.getProduct().getProductId()).orElse(null);
        p.setStock(p.getStock()+o.getQuantity());
        orderRepo.delete(o);
    }

}
