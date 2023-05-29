import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Forum} from "../_Models/Forum";
import {environment} from "../../envirenments/envirenment";

@Injectable({
  providedIn: 'root'
})
export class ForumService {

  constructor(private http: HttpClient) { }
  getById(id: string) {
    return this.http.get<Forum>(`${environment.apiUrl}/forum/get/${id}`);
  }
  getByEventId(id: number) {
    return this.http.get<Forum>(`${environment.apiUrl}/forum/get/event/${id}`);
  }

  add(forum: Forum) {
    console.log(forum);
    return this.http.post(`${environment.apiUrl}/forum/add`, forum);
  }

  getAll() {
    return this.http.get<Forum[]>(`${environment.apiUrl}/forum/all`);
  }
  update(forum: Forum) {
    console.log(forum);
    return this.http.put(`${environment.apiUrl}/forum/update`, forum);
  }
  delete(id: string) {
    return this.http.delete(`${environment.apiUrl}/forum/delete/${id}`);
  }

}
