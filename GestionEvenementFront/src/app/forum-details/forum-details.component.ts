import {Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import {Logistique} from "../_Models/Logistique";
import {Forum} from "../_Models/Forum";
import {Post} from "../_Models/Post";
import {LogistiqueService} from "../_Services/logistique.service";
import {ForumService} from "../_Services/forum.service";
import {first} from "rxjs";
import {DataService} from "../data.service";
import {animate, style, transition, trigger} from "@angular/animations";

@Component({
  selector: 'app-forum-details',
  templateUrl: './forum-details.component.html',
  styleUrls: ['./forum-details.component.css'],
  animations: [
    trigger('enterExitDown', [
      transition(':enter', [
        style({ opacity: 0, transform: 'translateY(100px)' }),
        animate(
          '500ms ease-in',
          style({ opacity: 1, transform: 'translateY(0)' })
        ),
      ]),
      transition(':leave', [
        animate(
          '500ms ease-in',
          style({ opacity: 0, transform: 'translateY(100px)' })
        ),
      ]),
    ])
  ]
})
export class ForumDetailsComponent implements OnInit{
  @ViewChild('myDiv', { static: false }) myDiv: ElementRef;
  eventId:number;
  forum:Forum;
  selected = 'mostRecent';
  page = [];
  months = ["Jan", "Feb", "Mar",
    "Apr", "May", "Jun", "Jul", "Aug", "Sep",
    "Oct", "Nov", "Dec"];
  constructor(
    private forumService: ForumService,private dataService: DataService
  ){}
  ngOnInit() {
    this.eventId=1;
    this.loadForum(this.selected);
  }
    public loadForum(sortingType:string) {
    this.forumService.getByEventId(this.eventId,sortingType).pipe(first()).subscribe(res => {
      const newObj: any = res;
      this.forum = newObj;
      console.log(newObj);
      this.updatePostPages();
      console.log(this.page);
      console.log(this.selected);
    });

    return;

  }
  private updatePostPages() {
    this.page = [];
    let row: any[] = [];
    let currentIndex = 0;

    for (let p of this.forum.posts) {
      row.push(p);
      currentIndex++;

      if (currentIndex === 3) {
        this.page.push(row);
        row = [];
        currentIndex = 0;
      }
    }

    if (row.length > 0) {
      this.page.push(row);
    }

    console.log(this.page);

  }
  sendData() {
    const data =this.forum;
    this.dataService.setData(data);
  }
  getPostDay(p:Post):string{
    const date = new Date(p.datePub);
    if (date instanceof Date) {
    return  String(date.getDate());
    } else {
      return ""
    }
    }
  getPostFullYear(p:Post){
    const date = new Date(p.datePub);
    if (date instanceof Date) {
      return String(date.getFullYear());
      // Rest of your code using the date variable
    } else {
      return ""
    }

  }
  getPostMonth(p:Post){
    const date = new Date(p.datePub);

    if (date instanceof Date) {

      return this.months[date.getMonth()]
    } else {
      return ""
    }
    }
  reloadDiv() {
    const divElement: HTMLElement = this.myDiv.nativeElement;
    // Perform any necessary operations or data updates
    // ...
    // Force a reload of the div's content
    divElement.innerHTML = divElement.innerHTML;

  }
}
