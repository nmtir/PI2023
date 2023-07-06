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
import {User} from "../_Models/User";



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
    ]),
    trigger('enterExitCenter', [
      transition(':enter', [
        style({ opacity: 0, transform: 'scale(0.02)', transformOrigin: 'top left' }),
        animate('500ms ease-in', style({ opacity: 1, transform: 'scale(1)' , transformOrigin: 'top left'}))
      ]),
      transition(':leave', [
        animate('500ms ease-in', style({ opacity: 0, transform: 'scale(0.02)', transformOrigin: 'top left' }))
      ])
    ])
  ]

})
export class PostDetailsComponent {
  panelOpenState = false;
  visible = false;
  currentUser:string;
  userId:string;
  data: any;
  post:Post;
  messageFormPost:FormGroup;
  messageFormReply:FormGroup;
  messageFormEdit:FormGroup;
  public selectedMessageId: string = null;



  public selectedEditMessageId: string = null;
  constructor(
    private route: ActivatedRoute,
    private postService:PostService,
    private messageService:MessageService,
    private formBuilder1:FormBuilder,
    private formBuilder2:FormBuilder,
    private formBuilder3:FormBuilder

   ) {
    this.userId="1";
    this.currentUser="2";
    this.data = this.route.snapshot.paramMap.get('data');
    this.loadPost();
    this.messageFormEdit=this.formBuilder1.group({

      contenu:['']
    });
    this.messageFormPost=this.formBuilder2.group({

      contenu:['']
    });
    this.messageFormReply=this.formBuilder3.group({

      contenu:['']
    });
  }

  private loadPost(){
    this.postService.getById(this.data).pipe(first()).subscribe(res=>{
      const newObj: any = res;
      this.postService.checkViewThenUpdate(newObj,this.currentUser).pipe(first()).subscribe(res=>{
        const newObj: any = res;
        this.post = newObj;
        console.log(this.post.user);
        this.post.messages.sort((a, b) => {
          const dateA = new Date(a.datePub);
          const dateB = new Date(b.datePub);
          return dateA.getTime()  - dateB.getTime();
        });
      });
    })
  }
  public SubmitForm( parentId:any) {
    if (parentId!=null){
      this.messageService.addAndAssign(this.messageFormReply.value,parentId,this.currentUser,"1").pipe(first()).subscribe();
      this.messageFormReply.get('contenu').patchValue("");
      this.loadPost();
    }
      else {
    this.messageService.addAndAssign(this.messageFormPost.value,this.data,this.currentUser,"2").pipe(first()).subscribe();
    this.messageFormPost.get('contenu').patchValue("");
    this.loadPost();
      }

  }

  public updateMessage(message:Message) {
    message.contenu=this.messageFormEdit.value.contenu;
    this.messageService.update(message).pipe(first()).subscribe();
    this.messageFormEdit.get('contenu').patchValue("");
    this.loadPost();
  }
  public addMessageSignal(message:Message){
    this.messageService.checkSginalsThenUpdate(message,this.currentUser).pipe(first()).subscribe();
    this.loadPost();
  }
  public removeMessageSignal(message:Message){
    this.messageService.removeSignal(message,this.currentUser).pipe(first()).subscribe();
    this.loadPost();
  }
  toggleCollapse(id: number): void {
    this.visible[id] = !this.visible[id];
  }
  public selectMessage(messageId: string): void {
    if (this.selectedMessageId === messageId) {
      // If the clicked message is the same as the currently selected message,
      // close the input box by setting the selectedMessageId to null
      this.selectedMessageId = null;
    } else {
      // If a different message is clicked, update the selectedMessageId
      this.selectedMessageId = messageId;
    }
  }


  addMessageLike(m: Message) {
    this.messageService.checkLikesThenUpdate(m,this.currentUser).pipe(first()).subscribe();
    this.loadPost();
  }

  checkMessageLike(m: Message):boolean {
    for (let u of m.likes){
      if (u.userId==this.currentUser)
      {
        return false;
      }

    }
    return true;
  }
  checkMessageSignal(m: Message):boolean {
    for (let u of m.signalUsers){
      if (u.userId==this.currentUser)
      {
        return false;
      }

    }
    return true;
  }

  removeMessageLike(m: Message) {
    this.messageService.removeLike(m,this.currentUser).pipe(first()).subscribe();
    this.loadPost();

  }

  addPostLike(post: Post) {
    this.postService.checkLikesThenUpdate(post,this.currentUser).pipe(first()).subscribe(
      res=>{
        const newObj: any = res;
        this.post = newObj;
      }
    );

  }

  checkPostLike(post: Post):boolean {

    for (let u of post.likes){
      if (u.userId==this.currentUser)
      {
        return false;
      }

    }
    return true;

  }



  removePostLike(post: Post) {
    this.postService.removeLike(post,this.currentUser).pipe(first()).subscribe(
      res=>{
        const newObj: any = res;
        this.post = newObj;
      }
    );

  }

  selectEditMessage(m: Message) {
    if (this.selectedEditMessageId === m.messageId) {
      // If the clicked message is the same as the currently selected message,
      // close the input box by setting the selectedMessageId to null
      this.selectedEditMessageId = null;
    } else {
      this.messageFormEdit.get('contenu').patchValue(m.contenu);
      // If a different message is clicked, update the selectedMessageId
      this.selectedEditMessageId = m.messageId;
    }

  }

  checkEdit(m: Message):boolean {
    if (m.user.userId==this.currentUser)

      return true;
    else return false

  }

  deleteMessage(m: Message) {
    this.messageService.delete(m.messageId).pipe(first()).subscribe();
    this.loadPost();
  }
}

