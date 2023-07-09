import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Logistique} from "../_Models/Logistique";
import {environment} from "../../envirenments/envirenment";

@Injectable({
  providedIn: 'root'
})
export class LogistiqueService {

  constructor(private http: HttpClient) { }
  getById(id: string) {
    return this.http.get<Logistique>(`${environment.apiUrl}/logistique/get/${id}`);
  }
  getByEventId(id: number) {
    return this.http.get<Logistique>(`${environment.apiUrl}/logistique/get/event/${id}`);
  }

  add(logistique: Logistique) {
    console.log(logistique);
    return this.http.post(`${environment.apiUrl}/logistique/add`, logistique);
  }

  getAll() {
    return this.http.get<Logistique[]>(`${environment.apiUrl}/logistique/all`);
  }
  update(logistique: Logistique) {
    console.log(logistique);
    return this.http.put(`${environment.apiUrl}/logistique/update`, logistique);
  }
  updateDepenses(logistique: Logistique) {
    console.log(logistique);
    return this.http.put(`${environment.apiUrl}/logistique/update/depenses`, logistique);
  }
  delete(id: string) {
    return this.http.delete(`${environment.apiUrl}/logistique/delete/${id}`);
  }

}
