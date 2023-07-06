import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {PostService} from "../_Services/post.service";
import {MessageService} from "../_Services/message.service";
import {FormBuilder} from "@angular/forms";
import {Message} from "../_Models/Message";
import {first} from "rxjs";

@Component({
  selector: 'app-message-list',
  templateUrl: './message-list.component.html',
  styleUrls: ['./message-list.component.css']
})
export class MessageListComponent {
  userId:string;
  currentUser:string;
  data:Message[];


  constructor(
    private route: ActivatedRoute,
    private messageService:MessageService,
    private formBuilder:FormBuilder
  )
  {
    this.userId="1";
    this.currentUser="2";
    }
    loadBlockedMessages(){
    this.messageService.getAllBlocked().pipe(first()).subscribe(res=> {
      const newObj: any = res;
      this.data=newObj;
    });


      }
}
