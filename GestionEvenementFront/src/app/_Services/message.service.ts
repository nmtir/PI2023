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
    return this.http.get<Message>(`${environment.apiUrl}/message/get/${id}`);
  }
  addAndAssign(message: Message,id:string,idUser:string,Target:string) {
    console.log(message);
    return this.http.post(`${environment.apiUrl}/message/add/${id}/${idUser}/${Target}`, message);
  }

  add(message: Message) {
    console.log(message);
    return this.http.post(`${environment.apiUrl}/message/add`, message);
  }

  getAll() {
    return this.http.get<Message[]>(`${environment.apiUrl}/message/all`);
  }
  checkLikesThenUpdate(message: Message,id:string) {
    console.log(message);
    return this.http.put<Message>(`${environment.apiUrl}/message/likes/update/${id}`, message);
  }
  checkSginalsThenUpdate(message:Message,id:string){
    console.log(message);
    return this.http.put<Message>(`${environment.apiUrl}/message/signal/update/${id}`, message);
  }
  removeSignal(message:Message,id:string){
    console.log(message);
    return this.http.put<Message>(`${environment.apiUrl}/message/signal/remove/${id}`, message);

  }
  removeLike(message: Message,id:string) {
    console.log(message);
    return this.http.put<Message>(`${environment.apiUrl}/message/likes/remove/${id}`, message);
  }
  update(message: Message) {
    console.log(message);
    return this.http.put(`${environment.apiUrl}/message/update`, message);
  }
  updateSignal(message: Message,id:number) {
    return this.http.put(`${environment.apiUrl}/message/update/signal/${id}`, message);
  }
  delete(id: string) {
    return this.http.delete(`${environment.apiUrl}/message/delete/${id}`);
  }


}
