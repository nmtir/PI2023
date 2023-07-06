import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Ordre} from "../_Models/Ordre";
import {environment} from "../../envirenments/envirenment";
import {Product} from "../_Models/Product";

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http: HttpClient) { }
  getById(id: string) {
    return this.http.get<Ordre>(`${environment.apiUrl}/api/ordre/get/${id}`);
  }

  add(r: Ordre) {
    console.log(r);
    return this.http.post(`${environment.apiUrl}/api/ordre/add`, r);
  }
  addAndAssignProduct(p:Product,qty:number,id:string) {
    console.log(p);
    return this.http.post(`${environment.apiUrl}/api/ordre/add/${qty}/${id}`, p);
  }

  getAll() {
    return this.http.get<Ordre[]>(`${environment.apiUrl}/api/ordre/all`);
  }
  getAllProductOrdersByEvent(id:string) {
    return this.http.get<Ordre[]>(`${environment.apiUrl}/api/ordre/all/logistique/${id}`);
  }
  update(o: Ordre) {
    console.log(o);
    return this.http.put<Ordre>(`${environment.apiUrl}/api/ordre/update`, o);
  }
  updateQuantity(qty: string,id:string,o:Ordre) {
    return this.http.put(`${environment.apiUrl}/api/ordre/update/${id}/${qty}`,o);
  }
  delete(id: string) {
    return this.http.delete(`${environment.apiUrl}/api/ordre/delete/${id}`);
  }
}
