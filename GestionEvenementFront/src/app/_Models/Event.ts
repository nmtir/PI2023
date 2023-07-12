import { EventType } from './EventType';
import {Reservation} from "./Reservation";
import {Transport} from "./Transport";

export class Event {
  id: string;
  description:string;
  endDate:Date;
  housingAvailable:boolean;
  housingPrice:number;
  ticketPrice:number;
  transportAvailable:boolean;
  lieu:string
  startDate:Date;
  title:string
  type: EventType;
  reservations:Reservation[];
  duration:number;
  transports:Transport[];
}
