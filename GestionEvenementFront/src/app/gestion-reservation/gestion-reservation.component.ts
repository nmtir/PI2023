import {Component} from '@angular/core';
import {EventService} from "../services/event.service";
import {ActivatedRoute} from "@angular/router";
import {first} from "rxjs";
import {Event} from "../_Models/Event";
import {Reservation} from "../_Models/Reservation";
import {Transport} from "../_Models/Transport";
import {TransportType} from "../_Models/TransportType";
import {Ordre} from "../_Models/Ordre";
import {ReservationService} from "../services/reservation.service";
import {TransportService} from "../_Services/transport.service";


@Component({
  selector: 'app-gestion-reservation',
  templateUrl: './gestion-reservation.component.html',
  styleUrls: ['./gestion-reservation.component.css']
})
export class GestionReservationComponent {
  eventId:number;
  event:Event;
  SuccessHousing=false;
  SuccessTicket=false;
  reservationsByStartingPoint: Record<string, Reservation[]> = {};
  constructor(private eventService:EventService,private reservationService:ReservationService,private transportService:TransportService,private route: ActivatedRoute) {
    this.eventId=Number(this.route.snapshot.paramMap.get('eventid'));
    this.loadEvent();

  }
  loadEvent(){
    this.eventService.getEvent(this.eventId).pipe(first()).subscribe(res=>{
      const newObj=res;
      this.event=newObj;
      this.event.duration=this.getDurationInDays(this.event.startDate,this.event.endDate);
      this.groupReservationsByStartingPoint(this.event.reservations);
    });
  }
  getDurationInDays(startDate: Date, endDate: Date): number {
    let date1=new Date(startDate);
    let date2=new Date(endDate);

    // Calculate the difference in milliseconds
    const timeDiff = date2.getTime() - date1.getTime();

    // Convert milliseconds to days (1 day = 24 hours = 60 minutes = 60 seconds = 1000 milliseconds)
    const days = Math.floor(timeDiff / (1000 * 60 * 60 * 24));

    return days;
  }
  updateTicketPrice(){
    this.eventService.updateEventPricing(this.eventId,"t",this.event).pipe(first()).subscribe();
    this.SuccessTicket=true;
    setTimeout(() => {
      this.SuccessTicket=false;
    }, 800);
  }
  updateHousingPrice(){
    this.eventService.updateEventPricing(this.eventId,"h",this.event).pipe(first()).subscribe();
    this.SuccessHousing=true;
    setTimeout(() => {
      this.SuccessHousing=false;
    }, 800);
  }
  groupReservationsByStartingPoint(reservations:Reservation[]){
    for (const reservation of reservations) {
      const startingPoint = reservation.transportStartingAdress;
      if (startingPoint !== null) {
      if (!this.reservationsByStartingPoint[startingPoint]) {
        this.reservationsByStartingPoint[startingPoint] = [];
      }

      this.reservationsByStartingPoint[startingPoint].push(reservation);
    }
    }


  }
  showAddButton(reservations: Reservation[]): boolean {
    return reservations.some(reservation => !reservation.seated);
  }

  addTransport(startingPoint: string,reservations: Reservation[]): void {
    const nonSeatedCount = reservations.filter(reservation => !reservation.seated).length;
  console.log(nonSeatedCount);
    if (nonSeatedCount < 8) {
      console.log("if sucess");
      let transport: Transport=new Transport();
      transport.transportType= TransportType.MiniVan;
      transport.capacity= 7 - nonSeatedCount;
      transport.fromAdress= startingPoint;
      transport.price=50;
      this.transportService.add(transport,this.eventId).pipe(first()).subscribe();
      for (let r of reservations.filter(reservation => !reservation.seated).slice(0, 7)){
        r.seated=true;
        this.reservationService.updateSeated(r).pipe(first()).subscribe();
      }
      }

    else if (nonSeatedCount < 31) {
      let transport: Transport=new Transport();
      transport.transportType= TransportType.MiniBus;
      transport.capacity= 30 - nonSeatedCount;
      transport.fromAdress= startingPoint;
      transport.price=180;
      this.transportService.add(transport,this.eventId).pipe(first()).subscribe();
      for (let r of reservations.filter(reservation => !reservation.seated).slice(0, 30)){
        r.seated=true;
        this.reservationService.updateSeated(r).pipe(first()).subscribe();
      }

      }


    else if (nonSeatedCount < 51) {
      let transport: Transport=new Transport();
      transport.transportType= TransportType.Bus;
      transport.capacity= 50 - nonSeatedCount;
      transport.fromAdress= startingPoint;
      transport.price=240;
      this.transportService.add(transport,this.eventId).pipe(first()).subscribe();
      for (let r of reservations.filter(reservation => !reservation.seated).slice(0, 50)){
        r.seated=true;
        this.reservationService.updateSeated(r).pipe(first()).subscribe();
      }
      }
  }


}
