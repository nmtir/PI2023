import {Ordre} from "./Ordre";
import {Payement} from "./Payement";
import {Event} from "./Event";

export class Logistique {
  logistiqueId:string;
  depenses:number;
  event:Event;
  payement:Payement ;
  ordres:Ordre[] ;
  totalDepenses:number;
  totalIncome:number;
}
