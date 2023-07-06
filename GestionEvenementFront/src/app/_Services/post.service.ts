import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Post} from "../_Models/Post";
import {environment} from "../../envirenments/envirenment";
import {Ordre} from "../_Models/Ordre";
import {Message} from "../_Models/Message";

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private http: HttpClient) { }
  getById(id: string) {
    return this.http.get<Post>(`${environment.apiUrl}/api/post/get/${id}`);
  }
  addAndAssign(post: Post,id:string,user:string) {
    return this.http.post(`${environment.apiUrl}/api/post/add/${id}/${user}`, post);
  }

  add(post: Post) {
    console.log(post);
    return this.http.post(`${environment.apiUrl}/api/post/add`, post);
  }

  getAll() {
    return this.http.get<Post[]>(`${environment.apiUrl}/api/post/all`);
  }
  update(post: Post) {
    console.log(post);
    return this.http.put(`${environment.apiUrl}/api/post/update`, post);
  }
  checkViewThenUpdate(post: Post,id:string) {
    console.log(post);
    return this.http.put<Post>(`${environment.apiUrl}/api/post/views/update/${id}`, post);
  }
  checkLikesThenUpdate(post: Post,id:string) {
    console.log(post);
    return this.http.put<Post>(`${environment.apiUrl}/api/post/likes/update/${id}`, post);
  }
  removeLike(post: Post,id:string) {
    console.log(post);
    return this.http.put<Post>(`${environment.apiUrl}/api/post/likes/remove/${id}`, post);
  }
  delete(id: string) {
    return this.http.delete(`${environment.apiUrl}/api/post/delete/${id}`);
  }

}
