import {Component, OnInit} from '@angular/core';
import {Forum} from "../_Models/Forum";
import {ActivatedRoute} from "@angular/router";
import {PostService} from "../_Services/post.service";
import {first} from "rxjs";
import {Post} from "../_Models/Post";
import {FormBuilder, FormGroup} from "@angular/forms";
import {MessageService} from "../_Services/message.service";
import {Message} from "../_Models/Message";
import {MatExpansionModule} from '@angular/material/expansion';
import {animate, animateChild, group, query, stagger, style, transition, trigger} from "@angular/animations";



@Component({
  selector: 'app-post-details',
  templateUrl: './post-details.component.html',
  styleUrls: ['./post-details.component.css'],
  animations: [
    trigger('enterExitUp', [
      transition(':enter', [
        style({ opacity: 0, transform: 'translateY(-10px)' }),
        animate(
          '500ms ease-in',
          style({ opacity: 1, transform: 'translateY(0)' })
        ),
      ]),
      transition(':leave', [
        animate(
          '500ms ease-in',
          style({ opacity: 0, transform: 'translateY(-10px)' })
        ),
      ]),
    ])
  ]

})
export class PostDetailsComponent implements OnInit{
  panelOpenState = false;
  visible = false;
  currentUser:number;
  userId:string;
  data: any;
  post:Post;
  messageForm:FormGroup;
  public selectedMessageId: string = null;
  constructor(private route: ActivatedRoute,private postService:PostService,private messageService:MessageService,private formBuilder:FormBuilder) {
  }

  ngOnInit() {

    this.userId="1";
    this.currentUser=1;
    this.data = this.route.snapshot.paramMap.get('data');
    console.log(this.data);
    this.loadPost();
    this.messageForm=this.formBuilder.group({

      contenu:['']
    })
  }

  private loadPost(){
    this.postService.getById(this.data).pipe(first()).subscribe(res=>{
      const newObj: any = res;
      this.post = newObj;
      console.log(newObj);
    })
  }
  public SubmitForm() {
    this.messageService.addAndAssign(this.messageForm.value,this.data,this.userId).pipe(first()).subscribe(data=>{
      window.location.reload()
    });
    console.log(this.messageForm.value);
  }
  public SubmitSignal(message:Message,id:number){
    this.messageService.updateSignal(message,id).pipe(first()).subscribe();
  }
  toggleCollapse(id: number): void {
    this.visible[id] = !this.visible[id];
  }
  public selectMessage(messageId: string): void {
    console.log(this.selectedMessageId);
    this.selectedMessageId = messageId;
  }
}

