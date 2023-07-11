import { Component, OnInit } from '@angular/core';
import { EventService } from '../services/event.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import Swal from 'sweetalert2';
import { ReservationService } from '../services/reservation.service';
import { HotToastService } from '@ngneat/hot-toast';
import {Role} from "../_Models/Role";
import {UserService} from "../services/user.service";
import {first} from "rxjs";
import {User} from "../_Models/User";

@Component({
  selector: 'app-events',
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.css']
})
export class EventsComponent {
  id:string;
  currentUser:User;
  role:Role;
  events:any=[]
  searchForm!:FormGroup;
  searchBydateForm!:FormGroup;
constructor(private fb:FormBuilder,private eventService:EventService,
  private reservationService:ReservationService,
  private userService:UserService,
  private toast: HotToastService) {



}


ngOnInit(): void {
  const role0 = localStorage.getItem('roles');
  // @ts-ignore
  this.role=role0;
  const id0 = localStorage.getItem('id');
  // @ts-ignore
  this.id=id0;
  this.getUser();
  this.searchBydateForm=this.fb.group({
    start:['',Validators.required],
    end:['',Validators.required]

  })


  this.searchForm=this.fb.group({
    title:['',Validators.required],
    description:['',Validators.required]

  })
  this.eventService.getAll().subscribe(res=>{
    this.events=res
    console.log(this.events);

  })
}
getUser(){
  this.userService.getUser(this.id).pipe(first()).subscribe(res=>{
    const newObj: any = res;
    this.currentUser = newObj;
  });
}

serach(){

  this.eventService.findEventByTitleContainingOrDescriptionContaining(this.searchForm.value.title,this.searchForm.value.description).subscribe(res=>{
    this.events=res
  })

}

serachbyDate(){
  console.log(this.searchBydateForm.value);
  this.eventService.findAllByStartDateBetween(this.searchBydateForm.value.start,this.searchBydateForm.value.end).subscribe(res=>{
    this.events=res
  })

}

reserver(eventId:any){

  if(localStorage.getItem('id')==null){
    Swal.fire({
      icon: 'error',
      title: 'Oops...',
      text: 'You should connecte!',
      footer: '<a href="/login">Signin</a>'
    })
  }else{

    this.reservationService.addReservation(localStorage.getItem('id'),eventId).subscribe(res=>{
      this.toast.success('Reservation added with success')

    },(error=>{
      Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: 'Vous avez déja réserver dans cette événement!',

      })
    }))

  }

}

  protected readonly Role = Role;
}
