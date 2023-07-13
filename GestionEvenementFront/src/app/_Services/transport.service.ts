import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Transport} from "../_Models/Transport";
import {environment} from "../../envirenments/envirenment";
import {Reservation} from "../_Models/Reservation";

@Injectable({
  providedIn: 'root'
})
export class TransportService {

  constructor(private http: HttpClient) { }
  getById(id: string) {
    return this.http.get<Transport>(`${environment.apiUrl}/api/transport/get/${id}`);
  }

  add(transport: Transport,eventId:number) {
    console.log(transport);
    return this.http.post(`${environment.apiUrl}/api/transport/add/${eventId}`, transport);
  }

  getAll() {
    return this.http.get<Transport[]>(`${environment.apiUrl}/api/transport/all`);
  }
  getAllByStartingAddressAndEvent(adr:any,eventId:any) {
    return this.http.get<Transport[]>(`${environment.apiUrl}/api/transport/all/${adr}/${eventId}`);
  }
  update(transport: Transport) {
    console.log(transport);
    return this.http.put(`${environment.apiUrl}/api/transport/update`, transport);
  }
  delete(id: string) {
    return this.http.delete(`${environment.apiUrl}/api/transport/delete/${id}`);
  }

}
