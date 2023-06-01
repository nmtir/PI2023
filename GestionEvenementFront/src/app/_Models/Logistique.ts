import {Ordre} from "./Ordre";
import {Payement} from "./Payement";
import {Event} from "./Event";

export class Logistique {
  logistiqueId:string;
  depenses:number;
  payement:Payement ;
  ordres:Ordre[] ;
}
