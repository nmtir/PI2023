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
    return this.http.get<Logistique>(`${environment.apiUrl}/api/logistique/get/${id}`);
  }
  getByEventId(id: number) {
    return this.http.get<Logistique>(`${environment.apiUrl}/api/logistique/get/event/${id}`);
  }

  add(logistique: Logistique) {
    console.log(logistique);
    return this.http.post(`${environment.apiUrl}/api/logistique/add`, logistique);
  }

  getAll() {
    return this.http.get<Logistique[]>(`${environment.apiUrl}/api/logistique/all`);
  }
  update(logistique: Logistique) {
    console.log(logistique);
    return this.http.put(`${environment.apiUrl}/api/logistique/update`, logistique);
  }
  updateDepenses(logistique: Logistique) {
    console.log(logistique);
    return this.http.put(`${environment.apiUrl}/api/logistique/update/depenses`, logistique);
  }
  delete(id: string) {
    return this.http.delete(`${environment.apiUrl}/api/logistique/delete/${id}`);
  }

}
