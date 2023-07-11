import { EventType } from './EventType';

export class Event {
  id: string;
  description:string;
  endDate:Date;
  lieu:string
  startDate:Date;
  title:string
  type: EventType;
}
