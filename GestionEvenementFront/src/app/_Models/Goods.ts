import {Resource} from "@angular/compiler-cli/src/ngtsc/metadata";
import {ResourceMateriel} from "./ResourceMateriel";

export class Goods {
  goodsId: string;
  NameGoods:string;
  Quantity:string ;
  UnitPrice:string ;
  resources:ResourceMateriel[];
  stock:string;
}
