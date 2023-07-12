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
    return this.http.get<Product>(`${environment.apiUrl}/api/products/get/${id}`);
  }

  add(product: Product) {
    return this.http.post(`${environment.apiUrl}/api/products/add`, product);
  }

  getAll() {
    return this.http.get<Product[]>(`${environment.apiUrl}/api/products/all`);
  }
  getAllFromStock() {
    return this.http.get<Product[]>(`${environment.apiUrl}/api/products/stock`);
  }
  update(product: Product) {
    return this.http.put(`${environment.apiUrl}/api/products/update`, product);
  }
  delete(id: string) {
    return this.http.delete(`${environment.apiUrl}/api/products/delete/${id}`);
  }
}
