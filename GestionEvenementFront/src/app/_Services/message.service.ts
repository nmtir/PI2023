import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Message} from "../_Models/Message";
import {environment} from "../../envirenments/envirenment";
import {Post} from "../_Models/Post";
import {first} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  constructor(private http: HttpClient) { }
  getById(id: string) {
    return this.http.get<Message>(`${environment.apiUrl}/api/message/get/${id}`);
  }
  addAndAssign(message: Message,id:string,idUser:string,Target:string) {
    console.log(message);
    return this.http.post(`${environment.apiUrl}/api/message/add/${id}/${idUser}/${Target}`, message);
  }

  add(message: Message) {
    console.log(message);
    return this.http.post(`${environment.apiUrl}/api/message/add`, message);
  }

  getAll() {
    return this.http.get<Message[]>(`${environment.apiUrl}/api/message/all`);
  }
  getAllBlocked() {
    return this.http.get<Message[]>(`${environment.apiUrl}/api/message/all-blocked`);
  }
  checkLikesThenUpdate(message: Message,id:string) {
    console.log(message);
    return this.http.put<Message>(`${environment.apiUrl}/api/message/likes/update/${id}`, message);
  }
  checkSginalsThenUpdate(message:Message,id:string){
    console.log(message);
    return this.http.put<Message>(`${environment.apiUrl}/api/message/signal/update/${id}`, message);
  }
  removeSignal(message:Message,id:string){
    console.log(message);
    return this.http.put<Message>(`${environment.apiUrl}/api/message/signal/remove/${id}`, message);

  }
  removeLike(message: Message,id:string) {
    console.log(message);
    return this.http.put<Message>(`${environment.apiUrl}/api/message/likes/remove/${id}`, message);
  }
  update(message: Message) {
    console.log(message);
    return this.http.put(`${environment.apiUrl}/api/message/update`, message);
  }
  updateSignal(message: Message,id:number) {
    return this.http.put(`${environment.apiUrl}/api/message/update/signal/${id}`, message);
  }
  delete(id: string) {
    return this.http.delete(`${environment.apiUrl}/api/message/delete/${id}`);
  }


}
