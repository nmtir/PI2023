import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Logistique} from "../_Models/Logistique";
import {environment} from "../../envirenments/envirenment";
import {Reservation} from "../_Models/Reservation";


@Injectable({
  providedIn: 'root'
})
export class ReservationService {

private url="http://localhost:8089/api/reservation";
  constructor(private http:HttpClient) { }

 addReservation(userId:any,eventId:any){
    return this.http.post<any>(this.url+'/user/'+userId+'/reservation/'+eventId,{})
 }
 AddReservation(reservation:any,userId:any,eventId:any){
    return this.http.post<any>(this.url+'/user/'+userId+'/reservation/'+eventId,reservation)
 }


 getAllReservation (){
    return this.http.get<any>(this.url+'/users/reservations')
   }

   delete (id:any){
      return this.http.delete<any>(this.url+'/delete/'+id)
     }
  updateSeated(reservation: Reservation,id:any) {
    console.log(reservation);
    return this.http.put(this.url+`/update/seated/${id}`, reservation);
  }
  update(reservation: Reservation) {
    console.log(reservation);
    return this.http.put(this.url+`/update`, reservation);
  }
  updateStatus(reservation: Reservation) {
    console.log(reservation);
    return this.http.put(this.url+`/update/status`, reservation);
  }



}
