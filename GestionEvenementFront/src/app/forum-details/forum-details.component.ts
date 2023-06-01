import {Component, OnInit} from '@angular/core';
import {Logistique} from "../_Models/Logistique";
import {Forum} from "../_Models/Forum";
import {Post} from "../_Models/Post";
import {LogistiqueService} from "../_Services/logistique.service";
import {ForumService} from "../_Services/forum.service";
import {first} from "rxjs";

@Component({
  selector: 'app-forum-details',
  templateUrl: './forum-details.component.html',
  styleUrls: ['./forum-details.component.css']
})
export class ForumDetailsComponent implements OnInit{
  eventId:number;
  forum:Forum;
  constructor(
    private forumService: ForumService
  ){}
  ngOnInit() {
    this.eventId=1;
    this.loadForum()
  }
  private loadForum() {

    this.forumService.getByEventId(this.eventId).pipe(first()).subscribe(res => {
      const newObj: any = res;
      this.forum = newObj;
    });

    return;

  }

}
