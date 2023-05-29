import {Goods} from "./Goods";
import {Logistique} from "./Logistique";

export class ResourceMateriel {
  resourceId:string ;
  dateLimitCommande:Date;
  logistique:Logistique;
  goods:Goods[] ;

}
