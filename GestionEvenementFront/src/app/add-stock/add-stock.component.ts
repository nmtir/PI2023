import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {PostService} from "../_Services/post.service";
import {first} from "rxjs";
import {ProductService} from "../_Services/product.service";
import {CdkDrag, CdkDragDrop, CdkDropList, copyArrayItem, moveItemInArray} from "@angular/cdk/drag-drop";
import {Product} from "../_Models/Product";
import {Ordre} from "../_Models/Ordre";
import {Logistique} from "../_Models/Logistique";
import {OrderService} from "../_Services/order.service";
import {LogistiqueService} from "../_Services/logistique.service";
import jwt_decode from "jwt-decode";
import {Role} from "../_Models/Role";

@Component({
  selector: 'app-add-stock',
  templateUrl: './add-stock.component.html',
  styleUrls: ['./add-stock.component.css']
})
export class AddStockComponent implements OnInit{
  id:string;
  productForm:FormGroup;
  currentUser:string;
  stock:Product[];
  products:Product[];
  saveImageSrc:string="assets/images/save (3).png";
  updateImageSrc:string="assets/images/save (3).png";
  Total:number;
  private DeleteWarning=false;
  selectedId=null;
  role:Role;
  constructor(
    private formBuilder:FormBuilder,
    private route: ActivatedRoute,
    private router:Router,
    private orderService: OrderService,
    private productService:ProductService,
    private logistiqueService:LogistiqueService,
  ) {
  }
  ngOnInit() {
    const role0 = localStorage.getItem('roles');
    const id = localStorage.getItem('id');
    // @ts-ignore
    this.role=role0;
    // @ts-ignore
    this.currentUser=id.toString();
    this.productForm=this.formBuilder.group({
      nameProduct:[''],
      price:[''],
      stock:['']
    });
    this.products=[];
    this.id="";
    this.stock =[];
    this.loadResource();

  }
  public SubmitForm() {
    this.productService.add(this.productForm.value).pipe(first()).subscribe(data=>{
      this.router.navigate(['/logistique']);
    });
  }


  private loadResource() {
    this.productService.getAll().pipe(first()).subscribe(res=>{
      const newObj:any=res;
      this.stock=newObj;
    });
  }



  SaveProducts(img:number) {

    if(img==1&&(!this.saveImageSrc.endsWith(".gif"))){
      this.saveImageSrc="assets/images/save (4).png"
    }
    if(img==2&&(!this.saveImageSrc.endsWith(".gif"))){
      this.saveImageSrc="assets/images/save (3).png"
    }
    if(img==3){
      this.saveImageSrc="assets/images/icons8-sand-timer.gif";
      for (let i of this.products){
        this.productService.add(i).pipe(first()).subscribe();
      }
      setTimeout(() => {
        this.products=[];
        this.saveImageSrc="assets/images/icons8-ok (3).gif";
        this.loadResource();
        setTimeout(() => {
          this.saveImageSrc="assets/images/save (3).png"
        }, 800);
      }, 4000);
    }


  }
  UpdateProducts(img:number) {

    if(img==1&&(!this.updateImageSrc.endsWith(".gif"))){
      this.updateImageSrc="assets/images/save (4).png"
    }
    if(img==2&&(!this.saveImageSrc.endsWith(".gif"))){
      this.updateImageSrc="assets/images/save (3).png"
    }
    if(img==3){
      this.updateImageSrc="assets/images/icons8-sand-timer.gif";
      for (let i of this.stock)
        {
          this.productService.update(i).pipe(first()).subscribe()
        }
      setTimeout(() => {

        this.updateImageSrc="assets/images/icons8-ok (3).gif";
        setTimeout(() => {
          this.loadResource();
          //
          this.updateImageSrc="assets/images/save (3).png"
        }, 800);
      }, 4000);
    }


  }
  removeIfZero(product: any): void {
    if (product.quantity === 0) {
      const index = this.products.indexOf(product);
      if (index !== -1) {
        this.products.splice(index, 1);
      }
    }
  }
  remove(product: any): void {
      const index = this.products.indexOf(product);
      if (index !== -1) {
        this.products.splice(index, 1);
      }
    }
  addNewForm(){
    let p:Product=new Product();
    this.products.push(p);
  }
  deleteFromStock(p:Product){
    this.productService.delete(p.productId).pipe(first()).subscribe();
    setTimeout(() => {
      this.stock=[];
      this.loadResource();
      this.selectDelete(p);
    }, 4000);

  }
  public selectDelete(p: Product): void {
    if (this.selectedId === p.productId) {
      // If the clicked message is the same as the currently selected message,
      // close the input box by setting the selectedMessageId to null
      this.DeleteWarning=false
      this.selectedId = null;
    } else {
      this.DeleteWarning=true;
      // If a different message is clicked, update the selectedMessageId
      this.selectedId = p.productId;
    }
  }

  protected readonly Role = Role;
}
