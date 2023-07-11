import {AfterViewInit, Component, OnInit} from '@angular/core';
import {LogistiqueService} from "../_Services/logistique.service";
import {first} from "rxjs";
import {Logistique} from "../_Models/Logistique";
import {Role} from "../_Models/Role";
import {User} from "../_Models/User";
import {ActivatedRoute} from "@angular/router";
import {EventService} from "../services/event.service";
import {Event} from "../_Models/Event";
import {formatDate} from '@angular/common';
@Component({
  selector: 'app-logistique-details',
  templateUrl: './logistique-details.component.html',
  styleUrls: ['./logistique-details.component.css']
})
export class LogistiqueDetailsComponent implements OnInit {
  eventId:number;
  logistique:Logistique;
  depenses:number;
  role:Role;
  currentUser:User;
  event:Event;
  constructor(
    private route: ActivatedRoute,
    private logistiqueService: LogistiqueService,
    private eventService: EventService
  ){

  }
  ngOnInit() {
    const role0 = localStorage.getItem('roles');
    const id = localStorage.getItem('id');
    // @ts-ignore
    this.role=role0;
    // @ts-ignore
    this.currentUser=id.toString();

    this.eventId=Number(this.route.snapshot.paramMap.get('id'));
    this.depenses=0;
    this.loadLogistique();
  }
  private loadLogistique() {
      this.eventService.getEvent(this.eventId).pipe(first()).subscribe(res=>{
        const newObj: any = res;
        this.event = newObj;
        console.log(this.event.id);
      })

      this.logistiqueService.getByEventId(this.eventId).pipe(first()).subscribe(res=>{
        const newObj: any = res;
        this.logistique = newObj;
        this.depenses=this.logistique.depenses;
        console.log(newObj);
      });


  }


  protected readonly Role = Role;
}

