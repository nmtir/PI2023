package tn.eesprit.gestionevenementback.Services;

import tn.eesprit.gestionevenementback.Entities.Ordre;
import tn.eesprit.gestionevenementback.Entities.Product;

import java.util.List;

public interface IOrderService {
    List<Ordre> retrieveAllOrders();
    Ordre addOrUpdateOrder(Ordre order);
    Ordre UpdateOrder(Integer qty,Integer id);
    Ordre retrieveOrder(Integer id);
    void removeOrder(Integer id);
    List<Ordre> retrieveProductOrdersReservedForEvent(Integer id);
    Ordre addOrderAndAssignProduct(Product product,Integer quantity,Integer id);
}

