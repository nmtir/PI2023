import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Post} from "../_Models/Post";
import {environment} from "../../envirenments/envirenment";

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private http: HttpClient) { }
  getById(id: string) {
    return this.http.get<Post>(`${environment.apiUrl}/post/get/${id}`);
  }
  addAndAssign(post: Post,id:string) {
    return this.http.post(`${environment.apiUrl}/post/add/${id}`, post);
  }

  add(post: Post) {
    console.log(post);
    return this.http.post(`${environment.apiUrl}/post/add`, post);
  }

  getAll() {
    return this.http.get<Post[]>(`${environment.apiUrl}/post/all`);
  }
  update(post: Post) {
    console.log(post);
    return this.http.put(`${environment.apiUrl}/post/update`, post);
  }
  delete(id: string) {
    return this.http.delete(`${environment.apiUrl}/post/delete/${id}`);
  }

}
