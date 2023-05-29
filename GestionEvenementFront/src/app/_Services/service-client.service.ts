import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ServiceClient} from "../_Models/ServiceClient";
import {environment} from "../../envirenments/envirenment";

@Injectable({
  providedIn: 'root'
})
export class ServiceClientService {

  constructor(private http: HttpClient) { }
  getById(id: string) {
    return this.http.get<ServiceClient>(`${environment.apiUrl}/serviceclient/get/${id}`);
  }

  add(serviceClient: ServiceClient) {
    console.log(serviceClient);
    return this.http.post(`${environment.apiUrl}/service/add`, serviceClient);
  }

  getAll() {
    return this.http.get<ServiceClient[]>(`${environment.apiUrl}/service/all`);
  }
  update(serviceClient: ServiceClient) {
    console.log(serviceClient);
    return this.http.put(`${environment.apiUrl}/service/update`, serviceClient);
  }
  delete(id: string) {
    return this.http.delete(`${environment.apiUrl}/service/delete/${id}`);
  }
}
