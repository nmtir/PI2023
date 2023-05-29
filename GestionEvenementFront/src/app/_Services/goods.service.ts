import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Goods} from "../_Models/Goods";
import {environment} from "../../envirenments/envirenment";

@Injectable({
  providedIn: 'root'
})
export class GoodsService {

  constructor(private http: HttpClient) { }
  getById(id: string) {
    return this.http.get<Goods>(`${environment.apiUrl}/goods/get/${id}`);
  }

  add(goods: Goods) {
    console.log(goods);
    return this.http.post(`${environment.apiUrl}/goods/add`, goods);
  }

  getAll() {
    return this.http.get<Goods[]>(`${environment.apiUrl}/goods/all`);
  }
  update(goods: Goods) {
    console.log(goods);
    return this.http.put(`${environment.apiUrl}/goods/update`, goods);
  }
  delete(id: string) {
    return this.http.delete(`${environment.apiUrl}/goods/delete/${id}`);
  }
}
