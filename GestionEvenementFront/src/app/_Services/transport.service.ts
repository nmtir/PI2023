import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Transport} from "../_Models/Transport";
import {environment} from "../../envirenments/envirenment";

@Injectable({
  providedIn: 'root'
})
export class TransportService {

  constructor(private http: HttpClient) { }
  getById(id: string) {
    return this.http.get<Transport>(`${environment.apiUrl}/transport/get/${id}`);
  }

  add(transport: Transport) {
    console.log(transport);
    return this.http.post(`${environment.apiUrl}/transport/add`, transport);
  }

  getAll() {
    return this.http.get<Transport[]>(`${environment.apiUrl}/transport/all`);
  }
  update(transport: Transport) {
    console.log(transport);
    return this.http.put(`${environment.apiUrl}/transport/update`, transport);
  }
  delete(id: string) {
    return this.http.delete(`${environment.apiUrl}/transport/delete/${id}`);
  }

}
