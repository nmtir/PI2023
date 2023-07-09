
import {Component, EventEmitter, OnInit, Output} from '@angular/core';

import {Logistique} from "../_Models/Logistique";
import {LogistiqueService} from "../_Services/logistique.service";
import {first} from "rxjs";
import {CdkDrag, CdkDragDrop,copyArrayItem, moveItemInArray, CdkDropList} from '@angular/cdk/drag-drop';
import {OrderService} from "../_Services/order.service";
import {Ordre} from "../_Models/Ordre";
import {ActivatedRoute, Router,NavigationEnd } from "@angular/router";
import {ProductService} from "../_Services/product.service";
import {Product} from "../_Models/Product";
import { forkJoin } from 'rxjs';

@Component({
  selector: 'app-logistique-list',
  templateUrl: './resource-list.component.html',
  styleUrls: ['./resource-list.component.css'],
})
export class ResourceListComponent implements OnInit{
  id : string;
  product:Product;
  stock:Product[];
  reserved:Ordre[];
  added:Product[];
  products:Product[];
  logistique:Logistique;
  currentRoute: string;
  constructor(
    private route: ActivatedRoute,
    private router:Router,
    private orderService: OrderService,
    private productService:ProductService,
    private logistiqueService:LogistiqueService,
  ){
  }
  ngOnInit() {
    this.products=[];
    this.id="";
    this.reserved=[];
    this.added=[];
    this.stock =[];
    this.logistique=null;
    this.loadLogistique();
    this.loadOrders();
    this.loadStock();
  }



  private async loadLogistique() {

    this.id = this.route.snapshot.paramMap.get('data');
    await this.logistiqueService.getById(this.id).pipe(first()).subscribe(res => {
      this.logistique=null;
      const newObj: any = res;
      this.logistique = newObj;
      console.log(this.logistique);
    });
  }
  private loadOrders() {
    this.orderService.getAllProductOrdersByEvent(this.id).pipe(first()).subscribe(res => {
      const newObj: any = res;
      this.products=[];
      this.reserved = newObj;
      for (let i of this.reserved) {
        i.product.quantity =i.quantity;
        this.products.push(i.product);
      }



    });
  }
  private loadStock() {
    this.productService.getAll().pipe(first()).subscribe(res=>{
      this.stock=null;
      const newObj:any=res;
      this.stock=newObj;
    });
    return;

  }
  public async remove(p:Product){
    for (let o of this.reserved) {
        if(p.productId==o.product.productId)
        {
            console.log("o.orderId===");
            console.log(o.orderId);
            await this.orderService.delete(o.orderId).pipe(first()).subscribe();
            this.products = this.products.filter(item => item !== p);
            console.log(this.products);

        }
        else {
          this.products = this.products.filter(item => item !== p);
          console.log(this.products);

        }

        }
    this.SaveOrders();

  }





  public async SaveOrders() {
    for (let o of this.reserved) {
      for (let p of this.products) {
        if(p.productId==o.product.productId)
        {
          if (p.quantity==0){
            console.log("o.orderId===");
            console.log(o.orderId);
            await this.orderService.delete(o.orderId).pipe(first()).subscribe();

          }
          else
            await this.orderService.update(o,p.quantity).pipe(first()).subscribe();

        }
      }
    }
    for(let o of this.added){
      for(let p of this.products){
        if(p.productId==o.productId)
        {
          await this.orderService.addAndAssignProduct(p,p.quantity,this.id).pipe(first()).subscribe();
        }
      }
    }
    this.added=[];
    await this.logistiqueService.updateDepenses(this.logistique).pipe(first()).subscribe();
    await this.logistiqueService.updateDepenses(this.logistique).pipe(first()).subscribe();
    await this.logistiqueService.updateDepenses(this.logistique).pipe(first()).subscribe();
    await this.logistiqueService.updateDepenses(this.logistique).pipe(first()).subscribe();
    await this.loadLogistique();
    await this.loadLogistique();
    await this.loadLogistique();
    await this.loadLogistique();
    await this.loadOrders();
    await this.loadOrders();
    await this.loadOrders();
    await this.loadOrders();
    await this.loadStock();
    await this.loadStock();
    await this.loadStock();
    await this.loadStock();
  }
  drop(event: CdkDragDrop<Product[]>) {
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else {
      event.item.data.quantity=1;
      this.added.push(event.item.data);
      copyArrayItem(
        event.previousContainer.data,
        event.container.data,
        event.previousIndex,
        event.currentIndex,
      );



    }

  }

  /** Predicate function that only allows even numbers to be dropped into a list. */
  alreadyPredicate(a: CdkDrag,b:CdkDropList) {
    for (const x of b.data) {
      if (a.data.nameProduct==x.nameProduct)
        return false;
    }
    return true;
  }
  noReturnPredicate() {
    return false;
  }


}
