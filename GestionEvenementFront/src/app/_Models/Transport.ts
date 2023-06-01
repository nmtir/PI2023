import {TransportType} from "./TransportType";
import {Ordre} from "./Ordre";

export class Transport {
  transportId:string;
  fromAdress:string ;
  price:string ;
  capacity:string ;
  transportType:TransportType ;
  Ordres:Ordre[];
}
