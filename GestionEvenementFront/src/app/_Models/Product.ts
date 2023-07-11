import {Resource} from "@angular/compiler-cli/src/ngtsc/metadata";
import {Ordre} from "./Ordre";

export class Product {
  productId: string;
  nameProduct:string;
  price:number ;
  stock:number;
  currentStock:number;
  ordres:Ordre[];
  quantity:number;
}
