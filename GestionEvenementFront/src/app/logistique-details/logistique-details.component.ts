import {AfterViewInit, Component, OnInit} from '@angular/core';
import {LogistiqueService} from "../_Services/logistique.service";
import {first} from "rxjs";
import {Logistique} from "../_Models/Logistique";
@Component({
  selector: 'app-logistique-details',
  templateUrl: './logistique-details.component.html',
  styleUrls: ['./logistique-details.component.css']
})
export class LogistiqueDetailsComponent implements OnInit {
  eventId:number;
  logistique:Logistique;
  depenses:number;
  constructor(
    private logistiqueService: LogistiqueService
  ){

  }
  ngOnInit() {
    this.eventId=1;
    this.depenses=0;
    this.loadLogistique();

  }
  private loadLogistique() {

      this.logistiqueService.getByEventId(this.eventId).pipe(first()).subscribe(res=>{
        const newObj: any = res;
        this.logistique = newObj;
        this.depenses=this.logistique.depenses;
        console.log(newObj);
      });


  }





}

