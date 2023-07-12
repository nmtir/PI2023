
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

import {User} from "../_Models/User";
import {Role} from "../_Models/Role";

@Component({
  selector: 'app-logistique-list',
  templateUrl: './resource-list.component.html',
  styleUrls: ['./resource-list.component.css'],
})
export class ResourceListComponent implements OnInit{
  id : string;
  currentUser:User=new User();
  stock:Product[];
  reserved:Ordre[];
  added:Product[];
  products:Product[];
  logistique:Logistique;
  Total:number;
  saveImageSrc:string="assets/images/save (3).png";

  constructor(
    private route: ActivatedRoute,
    private router:Router,
    private orderService: OrderService,
    private productService:ProductService,
    private logistiqueService:LogistiqueService,
  ){
  }
  ngOnInit() {
    const username = localStorage.getItem('username');

    const roles = localStorage.getItem('roles');
    // @ts-ignore
    this.currentUser.roles=roles;
    // @ts-ignore
    this.currentUser.username=username;

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
    this.orderService.getAllProductOrdersByEvent(this.id).pipe(first()).subscribe(res=>{
      const newObj:any=res;
      this.reserved=newObj;
      for(let i of this.reserved){
        i.initialQuantity=i.quantity;
        i.product.quantity=i.quantity;
        this.products.push(i.product);
      }



    });
  }
  private loadStock() {
    this.productService.getAll().pipe(first()).subscribe(res=>{
      this.stock=null;
      const newObj:any=res;
      this.stock=newObj;
      for (let i of this.stock){
        i.currentStock=i.stock;
      }
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

 calculateTotal():number{
    let total:number=0;
    for(let r of this.products){
      total=total+(r.price*r.quantity);
    }
    return total;
}


  public SaveOrders(img:number) {

      if(img==1&&(!this.saveImageSrc.endsWith(".gif"))){
        this.saveImageSrc="assets/images/save (4).png"
      }
      if(img==2&&(!this.saveImageSrc.endsWith(".gif"))){
        this.saveImageSrc="assets/images/save (3).png"
      }
      if(img==3){
        this.saveImageSrc="assets/images/icons8-sand-timer.gif";
        for(let o of this.added){
          for (let r of this.reserved)
          {
            if (o.nameProduct==r.product.nameProduct){
              r.quantity=o.quantity;
              const index = this.added.indexOf(o);
              if (index !== -1) {
                this.added.splice(index, 1);
              }
            }
          }}
        for(let o of this.added){
          for(let p of this.products){
            if(p.nameProduct==o.nameProduct)
            {
              this.orderService.addAndAssignProduct(p,p.quantity,this.id).pipe(first()).subscribe();
            }
          }
        }
        for(let o of this.reserved){
          for(let p of this.products){
            if(p.nameProduct==o.product.nameProduct)
            {
              o.quantity=p.quantity;
              this.orderService.update(o).pipe(first()).subscribe();
            }
          }
        }
        for(let o of this.reserved){
          let exists=false;
          for(let p of this.products){
            if(p.nameProduct==o.product.nameProduct)
            {
              exists=true;
            }
          }
          if (!exists){
            this.orderService.delete(o.orderId).pipe(first()).subscribe();
          }
        }
        setTimeout(() => {
          this.products=[];
          this.added=[];
          this.saveImageSrc="assets/images/icons8-ok (3).gif";
          setTimeout(() => {
            this.loadResource();
            this.logistiqueService.updateDepenses(this.logistique).pipe().subscribe();
            this.saveImageSrc="assets/images/save (3).png"
          }, 800);
        }, 4000);
      }

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
  removeIfZero(product: Product): void {
    if (product.quantity === 0) {
      const index0 = this.products.indexOf(product);
      console.log("products before===="+this.products);
      if (index0 !== -1) {
        this.products.splice(index0, 1);
      }

      console.log("products after===="+this.products);
      const index1 = this.added.indexOf(product);
      console.log("added before===="+this.added);
      if (index1 !== -1) {
        this.added.splice(index1, 1);
      }
      console.log("added after===="+this.added);
    }
  }
  isOutOfStock(p:Product){
    let inReserved:boolean=false;
    for(let r of this.reserved){
      if(p.nameProduct==r.product.nameProduct)
      {
        inReserved=true;
      }
    }
    if (inReserved)
    {for(let r of this.reserved){
      if(p.nameProduct==r.product.nameProduct)
      {
        for (let product of this.stock)
          if (p.nameProduct==product.nameProduct){
            if( product.stock+r.initialQuantity-p.quantity<1)
              return true;
          }
      }
    }}
    else {
      for (let product of this.stock)
        if (p.nameProduct==product.nameProduct){
          if(product.stock-p.quantity<1)
            return true;
        }

    }
    return false;
  }
  checkStock(product: any): number {
    let exists=false;
    let p:Product;
    for(let o of this.reserved){
      if(product.nameProduct==o.product.nameProduct)
        {
          for (let prod of this.products)
            if (product.nameProduct==prod.nameProduct){
                return product.stock+o.initialQuantity-prod.quantity;
            }
        }
      }
    for (let prod of this.products)
      if (product.nameProduct==prod.nameProduct){
        return product.stock-prod.quantity;
      }
    return  product.stock;
  }
  clear(product:Product){
    const index0 = this.products.indexOf(product);
    console.log("products before===="+this.products);
    if (index0 !== -1) {
      this.products.splice(index0, 1);
    }

    console.log("products after===="+this.products);
    const index1 = this.added.indexOf(product);
    console.log("added before===="+this.added);
    if (index1 !== -1) {
      this.added.splice(index1, 1);
    }
    console.log("added after===="+this.added);
  }

  protected readonly Role = Role;
}
