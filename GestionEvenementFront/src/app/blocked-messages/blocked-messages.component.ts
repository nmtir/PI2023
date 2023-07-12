import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {Message} from "../_Models/Message";
import {ActivatedRoute} from "@angular/router";
import {MessageService} from "../_Services/message.service";
import {FormBuilder, FormGroup} from "@angular/forms";
import {first} from "rxjs";
import {BadwordService} from "../_Services/badword..service";
import {Badword} from "../_Models/Badword";
import {animate, keyframes, state, style, transition, trigger} from "@angular/animations";
import {Role} from "../_Models/Role";

@Component({
  selector: 'app-blocked-messages',
  templateUrl: './blocked-messages.component.html',
  styleUrls: ['./blocked-messages.component.css'],
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
    ])]
})
export class BlockedMessagesComponent{
  role:Role;
  currentUser:string;
  data:Message[];
  badwordForm:FormGroup;
  badwordEditForm:FormGroup;
  allBws:Badword[];
  allBwsString:string="";
  selectedEditBadword: Badword = null;
  deleteImageSrc:string="assets/images/trash (2).png";
  updateImageSrc:string="assets/images/save (3).png";
  saveImageSrc:string="assets/images/save (3).png";




  constructor(
    private route: ActivatedRoute,
    private messageService:MessageService,
    private badwordService:BadwordService,
    private formBuilder:FormBuilder,
    private formBuilder1:FormBuilder,
    private cdr: ChangeDetectorRef
  )
  {
    const role0 = localStorage.getItem('roles');
    const id = localStorage.getItem('id');
    // @ts-ignore
    this.role=role0;
    // @ts-ignore
    this.currentUser=id.toString();
    this.loadBadwords();
    this.badwordForm=this.formBuilder.group({
      BadWords:['']
    });
    this.badwordEditForm=this.formBuilder1.group({

      word:['']
    });
  }

  loadBlockedMessages(){
    this.messageService.getAllBlocked().pipe(first()).subscribe(res=> {
      const newObj: any = res;
      console.log(newObj);
      this.data=newObj;
      this.cdr.detectChanges();
    });


  }
  selectEditWord(w: Badword) {
    if (this.selectedEditBadword === w) {
      // If the clicked message is the same as the currently selected message,
      // close the input box by setting the selectedMessageId to null
      this.selectedEditBadword = null;
    } else {
      this.badwordEditForm.get('word').patchValue(w.word);
      // If a different message is clicked, update the selectedMessageId
      this.selectedEditBadword = w;
    }


  }
  updateWord(w:Badword){
    w.word=this.badwordEditForm.value.word;
    this.badwordService.update(w).pipe(first()).subscribe();
    this.loadBadwords();
  }
  removeWord(w:Badword){
    this.badwordService.delete(w.badwordId).pipe(first()).subscribe();
    this.loadBadwords();
  }
  loadBadwords(){
    this.badwordService.getAll().pipe(first()).subscribe(res=> {
      const newObj: any = res;
      console.log(newObj);
      this.allBwsString="";
      this.allBws=newObj;
      for (let w of this.allBws){
        this.allBwsString=this.allBwsString+w.word+",";
      }

      this.loadBlockedMessages();
      this.cdr.detectChanges();
    });
    }
  submitForm(){
  this.badwordService.addbadWords(this.badwordForm.value.BadWords).pipe(first()).subscribe();
  this.loadBadwords();
  }
  changeDeleteImageSrc(img:number){
    if(img==1&&(!this.deleteImageSrc.endsWith(".gif"))){
      this.deleteImageSrc="assets/images/delete.png"
    }
    if(img==2&&(!this.deleteImageSrc.endsWith(".gif"))){
      this.deleteImageSrc="assets/images/trash (2).png"
    }
    if(img==3){
      this.deleteImageSrc="assets/images/icons8-sand-timer.gif";

      setTimeout(() => {
        this.removeWord(this.selectedEditBadword);
        this.deleteImageSrc="assets/images/icons8-ok (3).gif";
        setTimeout(() => {
          this.deleteImageSrc="assets/images/trash (2).png"
          this.selectEditWord(this.selectedEditBadword);
        }, 800);
        }, 4000);
      this.loadBadwords();

    }

  }
  changeSaveImageSrc(img:number){
    if(img==1&&(!this.saveImageSrc.endsWith(".gif"))){
      this.saveImageSrc="assets/images/save (4).png"
    }
    if(img==2&&(!this.saveImageSrc.endsWith(".gif"))){
      this.saveImageSrc="assets/images/save (3).png"
    }
    if(img==3&&!(this.badwordForm.value.BadWords=="")){
      this.saveImageSrc="assets/images/icons8-sand-timer.gif";
      setTimeout(() => {
        this.submitForm();
        this.badwordForm.get('BadWords').patchValue("");
        this.saveImageSrc="assets/images/icons8-ok (3).gif";
        setTimeout(() => {
          this.saveImageSrc="assets/images/save (3).png"
        }, 800);
        }, 4000);
      this.loadBadwords();

    }
    this.loadBadwords();

  }
  changeUpdateImageSrc(img:number){
    if(img==1&&(!this.updateImageSrc.endsWith(".gif"))){
      this.updateImageSrc="assets/images/save (4).png"
    }
    if(img==2&&(!this.updateImageSrc.endsWith(".gif"))){
      this.updateImageSrc="assets/images/save (3).png"
    }
    if(img==3){
      this.updateImageSrc="assets/images/icons8-sand-timer.gif";
      setTimeout(() => {
        this.updateImageSrc="assets/images/icons8-ok (3).gif";
        this.updateWord(this.selectedEditBadword);
        setTimeout(() => {
          this.updateImageSrc="assets/images/save (3).png"
          this.selectEditWord(this.selectedEditBadword);
        }, 800);
        }, 4000);
      this.loadBadwords();

    }

  }

  protected readonly Role = Role;
}
