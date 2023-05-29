import {Component, OnInit} from '@angular/core';
import {LogistiqueService} from "../../_Services/logistique.service";
import {first} from "rxjs";
import {Logistique} from "../../_Models/Logistique";
import * as events from "events";

@Component({
  selector: 'app-logistique-details',
  templateUrl: './logistique-details.component.html',
  styleUrls: ['./logistique-details.component.css']
})
export class LogistiqueDetailsComponent implements OnInit {
  eventId:number;
  logistique:Logistique;
  goods:string[];
  constructor(
    private logistiqueService: LogistiqueService
  ){}
  ngOnInit() {
    this.eventId=1;
  this.loadLogistique()
  }
  private loadLogistique() {

    this.logistiqueService.getByEventId(this.eventId).pipe(first()).subscribe(res => {
      const newObj: any = res;
      console.log(newObj)
      this.logistique = newObj;
      this.goods=newObj.resource.goods;
    });

    return;

  }





}

