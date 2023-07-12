import { Injectable } from '@angular/core';
import {Housing} from "../_Models/Housing";
import {environment} from "../../envirenments/envirenment";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class HousingService {

  constructor(private http: HttpClient) { }
  getById(id: string) {
    return this.http.get<Housing>(`${environment.apiUrl}/api/housing/get/${id}`);
  }

  add(housing: Housing) {
    console.log(housing);
    return this.http.post(`${environment.apiUrl}/api/housing/add`, housing);
  }

  getAll() {
    return this.http.get<Housing[]>(`${environment.apiUrl}/api/housing/all`);
  }
  update(housing: Housing) {
    console.log(housing);
    return this.http.put(`${environment.apiUrl}/api/housing/update`, housing);
  }
  delete(id: string) {
    return this.http.delete(`${environment.apiUrl}/api/housing/delete/${id}`);
  }
}
