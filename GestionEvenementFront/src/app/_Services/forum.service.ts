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
    return this.http.get<Forum>(`${environment.apiUrl}/api/forum/get/${id}`);
  }
  getByEventId(id: number,sortingType:string) {
    return this.http.get<Forum>(`${environment.apiUrl}/api/forum/get/event/${id}/${sortingType}`);
  }

  add(forum: Forum) {
    console.log(forum);
    return this.http.post(`${environment.apiUrl}/api/forum/add`, forum);
  }

  getAll() {
    return this.http.get<Forum[]>(`${environment.apiUrl}/api/forum/all`);
  }
  update(forum: Forum) {
    console.log(forum);
    return this.http.put(`${environment.apiUrl}/api/forum/update`, forum);
  }
  delete(id: string) {
    return this.http.delete(`${environment.apiUrl}/api/forum/delete/${id}`);
  }

}
