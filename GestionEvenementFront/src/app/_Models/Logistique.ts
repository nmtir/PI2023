import {ServiceClient} from "./ServiceClient";
import {ResourceMateriel} from "./ResourceMateriel";
import {Payement} from "./Payement";
import {Event} from "./Event";

export class Logistique {
  logistiqueId:string;
  payement:Payement ;
  resource:ResourceMateriel ;
  serviceClient:ServiceClient ;
}
