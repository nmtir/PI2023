
import {Component, EventEmitter, OnInit, Output} from '@angular/core';

import {Logistique} from "../_Models/Logistique";
import {LogistiqueService} from "../_Services/logistique.service";
import {first} from "rxjs";
import {CdkDrag, CdkDragDrop,copyArrayItem, moveItemInArray, CdkDropList} from '@angular/cdk/drag-drop';
import {OrderService} from "../_Services/order.service";
import {Ordre} from "../_Models/Ordre";
import {ActivatedRoute} from "@angular/router";
import {ProductService} from "../_Services/product.service";
import {Product} from "../_Models/Product";

@Component({
  selector: 'app-logistique-list',
  templateUrl: './resource-list.component.html',
  styleUrls: ['./resource-list.component.css'],
})
export class ResourceListComponent implements OnInit{
  id : string;
  stock:Product[];
  reserved:Ordre[];
  added:Product[];
  products:Product[];
  logistique:Logistique;
  constructor(
    private route: ActivatedRoute,
    private orderService: OrderService,
    private productService:ProductService,
    private logistiqueService:LogistiqueService,
  ){}
  ngOnInit() {
    this.products=[];
    this.id="";
    this.reserved=[];
    this.added=[];
    this.stock =[];
    this.logistique=null;
    this.loadResource();
  }



  private loadResource() {

    this.id = this.route.snapshot.paramMap.get('data');
    this.logistiqueService.getById(this.id).pipe(first()).subscribe(res=>{
      const newObj:any=res;
      this.logistique=newObj;
    });
    this.orderService.getAllProductOrdersByEvent(this.id).pipe(first()).subscribe(res=>{
      const newObj:any=res;
      this.reserved=newObj;
      console.log(this.reserved);
      for(let i of this.reserved){
        i.product.quantity=i.quantity;
        this.products.push(i.product);
        console.log(this.products);
      }

    });
    this.productService.getAll().pipe(first()).subscribe(res=>{
      const newObj:any=res;
      this.stock=newObj;
    });
    return;

  }




  public SaveOrders() {
    for(let o of this.reserved){
      for(let p of this.products){
        if(p.productId==o.product.productId)
        {
          o.quantity=p.quantity;
          this.orderService.update(o).pipe(first()).subscribe();
        }
      }
    }
    for(let o of this.added){
      for(let p of this.products){
        if(p.productId==o.productId)
        {
          this.orderService.addAndAssignProduct(p,p.quantity,this.id).pipe(first()).subscribe();
        }
      }
    }
  this.added=[];

    this.logistiqueService.updateDepenses(this.logistique).pipe().subscribe();

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
