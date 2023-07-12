import {TransportType} from "./TransportType";
import {Ordre} from "./Ordre";
import {Reservation} from "./Reservation";

export class Transport {
  transportId:string;
  fromAdress:string ;
  price:number ;
  capacity:number;
  transportType:TransportType ;
  event:Event;
  passangers:Reservation[];
}
