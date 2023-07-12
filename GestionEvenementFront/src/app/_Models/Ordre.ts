import {Product} from "./Product";
import {Logistique} from "./Logistique";
import {Transport} from "./Transport";
import {Housing} from "./Housing";

export class Ordre {
  orderId:string ;
  dateLimitCommande:Date;
  quantity:number;
  initialQuantity:number;
  logistique:Logistique;
  product:Product ;
  transport:Transport ;
  housing:Housing ;

}
