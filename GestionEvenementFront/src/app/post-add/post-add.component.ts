import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {PostService} from "../_Services/post.service";
import {first} from "rxjs";
import {ForumService} from "../_Services/forum.service";
import {Forum} from "../_Models/Forum";
import {User} from "../_Models/User";

@Component({
  selector: 'app-post-add',
  templateUrl: './post-add.component.html',
  styleUrls: ['./post-add.component.css']
})
export class PostAddComponent implements OnInit{
  id:string;
  postForm:FormGroup;
  currentUser:string;

  forum:string;
  constructor(
    private formBuilder:FormBuilder,
    private route: ActivatedRoute,
    private router:Router,
    private postService: PostService,
    ) {
  }
  ngOnInit() {
    this.currentUser="1"
    this.getId();
    this.postForm=this.formBuilder.group({
      title:[''],
      content:['']
    })
  }

  public getId() {
  this.id  = this.route.snapshot.paramMap.get('data');

  }
  public SubmitForm() {
  this.postService.addAndAssign(this.postForm.value,this.id,this.currentUser).pipe(first()).subscribe(data=>{
    this.router.navigate(['/forum']);
  });
    console.log(this.postForm.value);
  }

}
