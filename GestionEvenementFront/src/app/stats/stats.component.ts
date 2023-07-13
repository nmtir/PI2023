import { Component } from '@angular/core';
import {UserService} from "../services/user.service";
import {EventService} from "../services/event.service";
import {ActivatedRoute} from "@angular/router";
import {first} from "rxjs";
import {Event} from "../_Models/Event";

@Component({
  selector: 'app-stats',
  templateUrl: './stats.component.html',
  styleUrls: ['./stats.component.css']
})
export class StatsComponent {
  userId:number;
  events:Event[];
  tMoney:number=0;
  percentageDifference:number=0;
  todayMoney:number=0;
  yesterdayMoney:number=0;
  reservationsThisWeek:number=0;
  reservationsLastWeek:number=0;
  reservationspercentageDifference:number=0;
  constructor(private userService:UserService,private eventService:EventService,private route: ActivatedRoute) {
    this.userId=Number(this.route.snapshot.paramMap.get('id'));
    this.loadEvents();
  }
  loadEvents(){
    this.eventService.getAllByUser(this.userId).pipe(first()).subscribe(res=>{
      const newObj=res;
      this.events=newObj;
      console.log(this.events)
      this.tMoney=0;
      this.todaysVsYesterdayMoney();
      this.thisWeekVsLastWeekReservations();
    })

  }
  todaysVsYesterdayMoney() {
    const currentDate = new Date();
    let yesterdayDate = new Date();
    yesterdayDate.setDate(currentDate.getDate() - 1); // Subtract 1 day to get yesterday's date
    this.yesterdayMoney = 0;
    this.todayMoney = 0;

    for (let e of this.events) {
      for (let r of e.reservations) {
        const reservationDate = new Date(r.reservationDate);

        if (
          reservationDate.getDate() === yesterdayDate.getDate() &&
          reservationDate.getMonth() === yesterdayDate.getMonth() &&
          reservationDate.getFullYear() === yesterdayDate.getFullYear()
        ) {
          this.yesterdayMoney += r.paidPrice;
        }

        if (
          reservationDate.getDate() === currentDate.getDate() &&
          reservationDate.getMonth() === currentDate.getMonth() &&
          reservationDate.getFullYear() === currentDate.getFullYear()
        ) {
          this.todayMoney += r.paidPrice;
        }
      }
    }
    const difference = this.todayMoney - this.yesterdayMoney;
    this.percentageDifference = this.yesterdayMoney !== 0 ? (difference / this.yesterdayMoney) * 100 : 0;
  }
  thisWeekVsLastWeekReservations() {
    const currentDate = new Date();
    const currentDay = currentDate.getDay(); // 0 (Sunday) to 6 (Saturday)
    const weekStart = new Date(currentDate.getFullYear(), currentDate.getMonth(), currentDate.getDate() - currentDay);
    const weekEnd = new Date(currentDate.getFullYear(), currentDate.getMonth(), currentDate.getDate() + (6 - currentDay));
    const thisWeekReservations = [];
    const lastWeekReservations = [];

    for (let e of this.events) {
      for (let r of e.reservations) {
        const reservationDate = new Date(r.reservationDate);

        if (
          reservationDate >= weekStart &&
          reservationDate <= weekEnd
        ) {
          thisWeekReservations.push(r);
        } else if (
          reservationDate >= new Date(weekStart.getFullYear(), weekStart.getMonth(), weekStart.getDate() - 7) &&
          reservationDate <= new Date(weekEnd.getFullYear(), weekEnd.getMonth(), weekEnd.getDate() - 7)
        ) {
          lastWeekReservations.push(r);
        }
      }
    }

    const thisWeekCount = thisWeekReservations.length;
    const lastWeekCount = lastWeekReservations.length;
    const difference = thisWeekCount - lastWeekCount;
    this.reservationspercentageDifference = lastWeekCount !== 0 ? (difference / lastWeekCount) * 100 : 0;
  }
}
