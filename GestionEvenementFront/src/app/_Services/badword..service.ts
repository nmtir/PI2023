import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Badword} from "../_Models/Badword";
import {environment} from "../../envirenments/envirenment";

@Injectable({
  providedIn: 'root'
})
export class BadwordService {

  constructor(private http: HttpClient) { }
  getById(id: string) {
    return this.http.get<Badword>(`${environment.apiUrl}/api/badwords/get/${id}`);
  }

  add(badword: Badword) {
    console.log(badword);
    return this.http.post(`${environment.apiUrl}/api/badwords/add`, badword);
  }
  addbadWords(badwords: string) {
    console.log(badwords);
    return this.http.post(`${environment.apiUrl}/api/badword/add/badwords`, badwords);
  }

  getAll() {
    return this.http.get<Badword[]>(`${environment.apiUrl}/api/badword/all`);
  }
  update(badword: Badword) {
    console.log(badword);
    return this.http.put(`${environment.apiUrl}/api/badwords/update`, badword);
  }
  delete(id: string) {
    return this.http.delete(`${environment.apiUrl}/api/badwords/delete/${id}`);
  }
}
