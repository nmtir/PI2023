import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../envirenments/envirenment";
import {Product} from "../_Models/Product";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) { }
  getById(id: string) {
    return this.http.get<Product>(`${environment.apiUrl}/products/get/${id}`);
  }

  add(product: Product) {
    console.log(product);
    return this.http.post(`${environment.apiUrl}/products/add`, product);
  }

  getAll() {
    return this.http.get<Product[]>(`${environment.apiUrl}/products/all`);
  }
  getAllFromStock() {
    return this.http.get<Product[]>(`${environment.apiUrl}/products/stock`);
  }
  update(product: Product) {
    console.log(product);
    return this.http.put(`${environment.apiUrl}/products/update`, product);
  }
  delete(id: string) {
    return this.http.delete(`${environment.apiUrl}/products/delete/${id}`);
  }
}
