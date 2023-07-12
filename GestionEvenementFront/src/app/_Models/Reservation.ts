import {Payement} from "./Payement";
import {User} from "./User";
import {Transport} from "./Transport";

export class Reservation {

  reservationId:number;
  transportIncluded:boolean;
  transportStartingAdress:string;
  housingIncluded:boolean;
  price:number;
  payement:Payement;
  event:Event;
  user:User;
  seated:boolean;
  transport:Transport;
}
