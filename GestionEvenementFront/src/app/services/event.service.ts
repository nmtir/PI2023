import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Event} from "../_Models/Event";


@Injectable({
  providedIn: 'root'
})
export class EventService {

private url="http://localhost:8089/api/event";
  constructor(private http:HttpClient) { }

 addEvent(resource:any){
    return this.http.post<any>(this.url+'/add-events',resource)
 }

  getAll()   {
    return this.http.get<any>(this.url+'/all-events')

      //.catch(this.handleError);
  }

  getSingle(id:any)   {
    return this.http.get<any>(this.url+'/getSingle/'+id)

      //.catch(this.handleError);
  }
  getById(id:any)  {
    return this.http.get<Event>(this.url+'/getSingle/'+id)
  }

  update(resource:any,id:any){
    return this.http.put<any>(this.url+'/updateUser/'+id,resource)
  }
  deleteEvent(id){
    return this.http.delete<any>(this.url+'/delete-event/'+id)
  }

  getEvent(id:any){
    return this.http.get<any>(this.url+'/get-event/'+id)
  }

  updateEvent(id:any,resource){
    return this.http.put<any>(this.url+'/update-event/'+id,resource)
  }
  updateEventPricing(id:any,TorH:string,resource){
    console.log(resource);
    return this.http.put<any>(this.url+`/update-event/pricing/${id}/${TorH}`,resource)
  }
  getEventByType(){
    return this.http.get<any>(this.url+'/event-by-type')
  }

  findAllByStartDateBetween(start:any,end:any){
    return this.http.get<any>(this.url+'/findAllByStartDateBetween/'+start+'/'+end)
  }

  findEventByTitleContainingOrDescriptionContaining(title:any,description:any){
    return this.http.get<any>(this.url+'/findEventByTitleContainingOrDescriptionContaining/'+title+'/'+description)
  }
}
