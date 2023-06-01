import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {PostService} from "../_Services/post.service";
import {first} from "rxjs";
import {ForumService} from "../_Services/forum.service";
import {Forum} from "../_Models/Forum";

@Component({
  selector: 'app-post-add',
  templateUrl: './post-add.component.html',
  styleUrls: ['./post-add.component.css']
})
export class PostAddComponent implements OnInit{
  id:string;
  postForm:FormGroup;

  forum:string;
  constructor(
    private formBuilder:FormBuilder,
    private route: ActivatedRoute,
    private router:Router,
    private postService: PostService,
    ) {
  }
  ngOnInit() {
    this.getId();
    this.postForm=this.formBuilder.group({
      title:[''],
      content:['']
    })
  }

  public getId() {
  this.route.paramMap.subscribe(params => {
  this.id = params.get('id');
});
  }
  public SubmitForm() {
  this.postService.addAndAssign(this.postForm.value,this.id).pipe(first()).subscribe(data=>{
    this.router.navigate(['forum']);
  })
    console.log(this.postForm.value);
  }

}
