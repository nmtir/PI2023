import {ServiceClient} from "./ServiceClient";
import {TransportType} from "./TransportType";

export class Transport {
  transportId:string;
  fromAdress:string ;
  price:string ;
  capacity:string ;
  transportType:TransportType ;
  serviceClient:ServiceClient;
}
