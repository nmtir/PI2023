import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Message} from "../_Models/Message";
import {environment} from "../../envirenments/envirenment";

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  constructor(private http: HttpClient) { }
  getById(id: string) {
    return this.http.get<Message>(`${environment.apiUrl}/message/get/${id}`);
  }

  add(message: Message) {
    console.log(message);
    return this.http.post(`${environment.apiUrl}/message/add`, message);
  }

  getAll() {
    return this.http.get<Message[]>(`${environment.apiUrl}/message/all`);
  }
  update(message: Message) {
    console.log(message);
    return this.http.put(`${environment.apiUrl}/message/update`, message);
  }
  delete(id: string) {
    return this.http.delete(`${environment.apiUrl}/message/delete/${id}`);
  }

}
