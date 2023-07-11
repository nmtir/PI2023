package tn.eesprit.gestionevenementback.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.eesprit.gestionevenementback.Entities.Ordre;
import tn.eesprit.gestionevenementback.Entities.Product;
import tn.eesprit.gestionevenementback.Services.IOrderService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ordre")
public class OrderController {
    private final IOrderService orderService;
    @PostMapping("/add")
    Ordre addOrder(@RequestBody Ordre order)
    {
        return orderService.addOrUpdateOrder(order);
    }
    @PostMapping("/add/{qty}/{id}")
    Ordre addOrder(@RequestBody Product product,@PathVariable("qty") Integer qty,@PathVariable("id") Integer id)
    {
        return orderService.addOrderAndAssignProduct(product,qty,id);
    }
    @PutMapping("/update")
    Ordre updateOrder(@RequestBody Ordre order){
        return orderService.addOrUpdateOrder(order);
    }
    @PutMapping("/update/qty")
    Ordre updateOrderQty(@RequestBody Ordre order){
        return orderService.UpdateOrder(order);
    }
    @GetMapping("/get/{id}")
    Ordre getOrder(@PathVariable("id") Integer id){
        return orderService.retrieveOrder(id);
    }
    @GetMapping("/all")
    List<Ordre> getAllOrders(){return orderService.retrieveAllOrders();}
    @GetMapping("/all/logistique/{id}")
    List<Ordre> getProductOrderReservedForEvent(@PathVariable("id") Integer id){return orderService.retrieveProductOrdersReservedForEvent(id);}
    @DeleteMapping("/delete/{id}")
    void deleteOrder(@PathVariable("id") Integer id){
        orderService.removeOrder(id);
    }

}
