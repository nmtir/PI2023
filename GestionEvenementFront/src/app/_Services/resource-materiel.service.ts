import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ResourceMateriel} from "../_Models/ResourceMateriel";
import {environment} from "../../envirenments/envirenment";

@Injectable({
  providedIn: 'root'
})
export class ResourceMaterielService {

  constructor(private http: HttpClient) { }
  getById(id: string) {
    return this.http.get<ResourceMateriel>(`${environment.apiUrl}/resource/get/${id}`);
  }

  add(r: ResourceMateriel) {
    console.log(r);
    return this.http.post(`${environment.apiUrl}/resource/add`, r);
  }

  getAll() {
    return this.http.get<ResourceMateriel[]>(`${environment.apiUrl}/resource/all`);
  }
  update(r: ResourceMateriel) {
    console.log(r);
    return this.http.put(`${environment.apiUrl}/resource/update`, r);
  }
  delete(id: string) {
    return this.http.delete(`${environment.apiUrl}/resource/delete/${id}`);
  }
}
