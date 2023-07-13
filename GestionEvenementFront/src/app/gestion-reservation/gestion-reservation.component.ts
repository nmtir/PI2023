import {Component} from '@angular/core';
import {EventService} from "../services/event.service";
import {ActivatedRoute} from "@angular/router";
import {first} from "rxjs";
import {Event} from "../_Models/Event";
import {Reservation} from "../_Models/Reservation";
import {Transport} from "../_Models/Transport";
import {TransportType} from "../_Models/TransportType";
import {ReservationService} from "../services/reservation.service";
import {TransportService} from "../_Services/transport.service";
import {Status} from "../_Models/Status";
import {FormBuilder, FormGroup} from "@angular/forms";
import {UserService} from "../services/user.service";
import {User} from "../_Models/User";
import {LogistiqueService} from "../_Services/logistique.service";
import {Logistique} from "../_Models/Logistique";


@Component({
  selector: 'app-gestion-reservation',
  templateUrl: './gestion-reservation.component.html',
  styleUrls: ['./gestion-reservation.component.css']
})
export class GestionReservationComponent {
  U:User;
  usernameForm:FormGroup;
  public statusEnum = Object.values(Status);
  SortingMethod:string;
  ascending:boolean=true;
  miniBusCount:number;
  busCount:number;
  vanCount:number;
  eventId:number;
  event:Event;
  SuccessHousing=false;
  SuccessTicket=false;
  SuccessTransport=false;
  countHousing:number=0;
  depenses:number;
  countTransport:number=0;
  countTicket:number=0;
  saveImageSrc:string="assets/images/save (3).png";
  availableTransport:Transport[];
  logistique:Logistique;

  reservationsByStartingPoint: Record<string, Reservation[]> = {};
  constructor(private userService:UserService,private logistiqueService:LogistiqueService,private formBuilder:FormBuilder,private eventService:EventService,private reservationService:ReservationService,private transportService:TransportService,private route: ActivatedRoute) {
    this.eventId=Number(this.route.snapshot.paramMap.get('eventid'));
    this.loadEvent();
    this.usernameForm=this.formBuilder.group({
      username:[''],
    });

  }
  loadEvent(){
    this.eventService.getEvent(this.eventId).pipe(first()).subscribe(res=>{
      const newObj=res;
      this.event=newObj;
      this.event.duration=this.getDurationInDays(this.event.startDate,this.event.endDate);
      this.groupReservationsByStartingPoint(this.event.reservations);
      this.calcVehiculeNumbers(this.event.reservations);
      this.ascending=!this.ascending;
      this.sortBy(this.SortingMethod);
      this.countTicket=0;
      this.countHousing=0;
      this.countTransport=0;
      this.countHousingreservations();
      this.getDepenses();
      setTimeout(() => {
        this.CalculFinal();
      }, 800);
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
  updateTransportPrice(){
    this.eventService.updateEventPricing(this.eventId,"x",this.event).pipe(first()).subscribe();
    this.SuccessTransport=true;
    setTimeout(() => {
      this.SuccessTransport=false;
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
  countHousingreservations(){
    for (let i of this.event.reservations) {
     this.countTicket+=1;
     if (i.housingIncluded){
       this.countHousing+=1;
     }

      if(i.transportIncluded){
        this.countTransport+=1;
      }

    }
  }

  addTransport(startingPoint: string,reservations: Reservation[]): void {
    this.availableTransport=[];
    this.transportService.getAllByStartingAddressAndEvent(startingPoint,this.eventId).pipe(first()).subscribe(res=>{
      const newObj=res;
      let transports:Transport[]=newObj;
      for (let t of transports){
        if (t.capacity>0){
          this.availableTransport.push(t);
        }
      }
    });
    setTimeout(() => {
      const nonSeatedCount = reservations.filter(reservation => !reservation.seated).length;
      let emptyplacesCount:number=0;
      for (let t of this.availableTransport)
      {
        emptyplacesCount=emptyplacesCount+t.capacity;
      }
      if (nonSeatedCount<=emptyplacesCount)
      {
        for (let transport of this.availableTransport) {
          const availableCapacity = transport.capacity;
          const reservationsToSeat = reservations.filter(reservation => !reservation.seated && reservation.transport === null);

          if (reservationsToSeat.length > 0 && availableCapacity > 0) {
            const seatsToFill = Math.min(reservationsToSeat.length, availableCapacity);

            for (let i = 0; i < seatsToFill; i++) {
              const reservationToSeat = reservationsToSeat[i];
              reservationToSeat.seated = true;
              reservationToSeat.transport = transport;

              this.reservationService.updateSeated(reservationToSeat, transport.transportId).pipe(first()).subscribe();
            }
            transport.capacity -= seatsToFill;
            emptyplacesCount -= seatsToFill;
          }
        }

      }

      else if (nonSeatedCount < 8) {
        console.log("if sucess");
        let transport: Transport=new Transport();
        transport.transportType= TransportType.MiniVan;
        transport.capacity= 7 - nonSeatedCount;
        transport.fromAdress= startingPoint;
        transport.price=50;
        this.transportService.add(transport, this.eventId).pipe(first()).subscribe((response: any) => {
          const transportId = response.transportId;
          transport.transportId = transportId;
          for (let r of reservations.filter(reservation => !reservation.seated).slice(0, 7)) {
            r.seated = true;
            this.reservationService.updateSeated(r,transport.transportId).pipe(first()).subscribe();
          }
        });

      }

      else if (nonSeatedCount < 31) {
        let transport: Transport=new Transport();
        transport.transportType= TransportType.MiniBus;
        transport.capacity= 30 - nonSeatedCount;
        transport.fromAdress= startingPoint;
        transport.price=180;
        this.transportService.add(transport, this.eventId).pipe(first()).subscribe((response: any) => {
          const transportId = response.transportId;
          transport.transportId = transportId;
          for (let r of reservations.filter(reservation => !reservation.seated).slice(0, 30)) {
            r.seated = true;
            this.reservationService.updateSeated(r,transport.transportId).pipe(first()).subscribe();
          }
        });

      }


      else if (nonSeatedCount < 51) {
        let transport: Transport=new Transport();
        transport.transportType= TransportType.Bus;
        transport.capacity= 50 - nonSeatedCount;
        transport.fromAdress= startingPoint;
        transport.price=240;
        this.transportService.add(transport, this.eventId).pipe(first()).subscribe((response: any) => {
          const transportId = response.transportId;
          transport.transportId = transportId;
          for (let r of reservations.filter(reservation => !reservation.seated).slice(0, 50)) {
            r.seated = true;
            this.reservationService.updateSeated(r,transport.transportId).pipe(first()).subscribe();
          }
        });

      }

      setTimeout(() => {
      this.loadEvent();
      }, 800);
    }, 800);



  }
  calcVehiculeNumbers(reservations: Reservation[]): void {
    const vans: Set<string> = new Set<string>();
    const minBuses: Set<string> = new Set<string>();
    const buses: Set<string> = new Set<string>();

    for (const reservation of reservations) {
      if (reservation.transport) {
        if (reservation.transport.transportType == TransportType.Bus&&!buses.has(reservation.transport.transportId)) {
          buses.add(reservation.transport.transportId);
        }
        if (reservation.transport.transportType == TransportType.MiniBus&&!minBuses.has(reservation.transport.transportId)) {
          minBuses.add(reservation.transport.transportId);
        }
        if (reservation.transport.transportType == TransportType.MiniVan&&!vans.has(reservation.transport.transportId)) {
          vans.add(reservation.transport.transportId);
        }
      }
    }
    console.log(vans);
    this.miniBusCount = minBuses.size;
    this.vanCount = vans.size;
    this.busCount = buses.size;
  }
  updateReservation(r:Reservation){
    console.log(r);
    this.reservationService.updateStatus(r).pipe(first()).subscribe();
    setTimeout(() => {
      this.loadEvent();
    }, 800);


  }
  sortBy(method:String){

 if (method=="Username"){
    if (this.ascending){
      this.event.reservations.sort((a, b) => {
        const usernameA = a.user.username.toLowerCase();
        const usernameB = b.user.username.toLowerCase();

        if (usernameA < usernameB) {
          return -1;
        }
        if (usernameA > usernameB) {
          return 1;
        }
        return 0;
      });
    }
    else   {this.event.reservations.sort((a, b) => {
      const usernameA = a.user.username.toLowerCase();
      const usernameB = b.user.username.toLowerCase();
      if (usernameA > usernameB) {
        return -1;
      }
      if (usernameA < usernameB) {
        return 1;
      }
      return 0;
    });
    }
 }
 if (method=="Housing"){
    if (this.ascending){
      this.event.reservations.sort((a, b) => {
        const usernameA = a.housingIncluded;
        const usernameB = b.housingIncluded;

        if (usernameA < usernameB) {
          return -1;
        }
        if (usernameA > usernameB) {
          return 1;
        }
        return 0;
      });
    }
    else   {this.event.reservations.sort((a, b) => {
      const usernameA = a.housingIncluded;
      const usernameB = b.housingIncluded;
      if (usernameA > usernameB) {
        return -1;
      }
      if (usernameA < usernameB) {
        return 1;
      }
      return 0;
    });
    }
 }
 if (method=="Transport"){
    if (this.ascending){
      this.event.reservations.sort((a, b) => {
        const usernameA = a.transportIncluded;
        const usernameB = b.transportIncluded;

        if (usernameA < usernameB) {
          return -1;
        }
        if (usernameA > usernameB) {
          return 1;
        }
        return 0;
      });
    }
    else   {this.event.reservations.sort((a, b) => {
      const usernameA = a.transportIncluded;
      const usernameB = b.transportIncluded;
      if (usernameA > usernameB) {
        return -1;
      }
      if (usernameA < usernameB) {
        return 1;
      }
      return 0;
    });
    }
 }
 if (method=="Seated"){
    if (this.ascending){
      this.event.reservations.sort((a, b) => {
        const usernameA = a.seated;
        const usernameB = b.seated;

        if (usernameA < usernameB) {
          return -1;
        }
        if (usernameA > usernameB) {
          return 1;
        }
        return 0;
      });
    }
    else   {this.event.reservations.sort((a, b) => {
      const usernameA = a.seated;
      const usernameB = b.seated;
      if (usernameA > usernameB) {
        return -1;
      }
      if (usernameA < usernameB) {
        return 1;
      }
      return 0;
    });
    }
 }
 if (method=="Price"){
    if (this.ascending){
      this.event.reservations.sort((a, b) => {
        const usernameA = a.paidPrice;
        const usernameB = b.paidPrice;

        if (usernameA < usernameB) {
          return -1;
        }
        if (usernameA > usernameB) {
          return 1;
        }
        return 0;
      });
    }
    else   {this.event.reservations.sort((a, b) => {
      const usernameA = a.paidPrice;
      const usernameB = b.paidPrice;
      if (usernameA > usernameB) {
        return -1;
      }
      if (usernameA < usernameB) {
        return 1;
      }
      return 0;
    });
    }
 }
 if (method=="Status"){
    if (this.ascending){
      this.event.reservations.sort((a, b) => {
        const usernameA = a.status;
        const usernameB = b.status;

        if (usernameA < usernameB) {
          return -1;
        }
        if (usernameA > usernameB) {
          return 1;
        }
        return 0;
      });
    }
    else   {this.event.reservations.sort((a, b) => {
      const usernameA = a.status;
      const usernameB = b.status;
      if (usernameA > usernameB) {
        return -1;
      }
      if (usernameA < usernameB) {
        return 1;
      }
      return 0;
    });
    }
 }
 if (method=="Address"){
    if (this.ascending){
      this.event.reservations.sort((a, b) => {
        const usernameA = a.transportStartingAdress;
        const usernameB = b.transportStartingAdress;

        if (usernameA < usernameB) {
          return -1;
        }
        if (usernameA > usernameB) {
          return 1;
        }
        return 0;
      });
    }
    else   {this.event.reservations.sort((a, b) => {
      const usernameA = a.transportStartingAdress;
      const usernameB = b.transportStartingAdress;
      if (usernameA > usernameB) {
        return -1;
      }
      if (usernameA < usernameB) {
        return 1;
      }
      return 0;
    });
    }
 }



    this.ascending=!this.ascending;

  }
  remove(r:Reservation){
    this.reservationService.delete(r.reservationId).pipe(first()).subscribe();
  }
  getDepenses(){
    this.logistiqueService.getByEventId(this.eventId).pipe(first()).subscribe(res=>{
      const newObj: any = res;
      this.logistique = newObj;
      this.depenses=this.logistique.depenses;
      console.log(newObj);
    });
  }
  addNewForm(){
    let r:Reservation=new Reservation();
    r.status=Status.Going;
    r.paidPrice=0;
    r.seated=false;
    r.transportIncluded=false;
    r.housingIncluded=false;
    r.edit=true;
    this.event.reservations.push(r);
  }
  AddReservation(r:Reservation){
        this.userService.getUserByUsername(this.usernameForm.value.username).pipe(first()).subscribe(res=>{
          const newObj=res;
          this.U=newObj;
          r.user=this.U;
          console.log(this.U);
          this.reservationService.AddReservation(r,this.U.userId,this.eventId).pipe(first()).subscribe();
        });

  }
  CalculFinal(){
    let totalIncome=(this.event.housingPrice*this.countHousing)+(this.event.transportPrice*this.countTransport)+(this.event.ticketPrice*this.countTicket)
    let totlaDepenses=(98*this.countHousing*this.event.duration)+((this.vanCount*50)+(this.miniBusCount*180)+(this.busCount*240))+this.depenses;
    this.logistique.totalDepenses=totlaDepenses;
    this.logistique.totalIncome=totalIncome;
    this.logistiqueService.updateDepensesFinal(this.logistique).pipe(first()).subscribe();
  }

  protected readonly Status = Status;
}
