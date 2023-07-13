import {Payement} from "./Payement";
import {User} from "./User";
import {Transport} from "./Transport";
import {Status} from "./Status";
import {Event} from "./Event";

export class Reservation {
  reservationId:number;
  transportIncluded:boolean;
  transportStartingAdress:string;
  housingIncluded:boolean;
  paidPrice:number;
  payement:Payement;
  event:Event ;
  user:User;
  seated:boolean;
  transport:Transport;
  status:Status;
  edit:boolean
  reservationDate:Date;
}
