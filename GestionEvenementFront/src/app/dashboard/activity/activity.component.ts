import { Component, ViewEncapsulation } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { ActivityService } from 'src/app/services/activity.service';

@Component({
  selector: 'app-activity',
  templateUrl: './activity.component.html',
  styleUrls: ['./activity.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class ActivityComponent {
  

  activityForm !:FormGroup;
  activity:any=[]
  id:any;
    constructor(private fb:FormBuilder,private route:ActivatedRoute,private activityService:ActivityService){
  
    }
    ngOnInit(): void {
      this.id = this.route.snapshot.paramMap.get('id');
      this.activityForm=this.fb.group({
        title:['',Validators.required],
      description:['',Validators.required],
      debut:['',Validators.required],
      fin:['',Validators.required],
      type:['',Validators.required],
      montant:['',Validators.required],
})
   this.getAll();
      const element1 = document.getElementById("header1");
      element1.setAttribute("hidden","true");
      const element2 = document.getElementById("ftco-footer");
      element2.setAttribute("hidden","true");
    }
  
    ngOnDestroy() {
      const element1 = document.getElementById("header1");
      element1.removeAttribute("hidden");
      const element2 = document.getElementById("ftco-footer");
      element2.removeAttribute("hidden");
    }
getAll(){
  this.activityService.getActivityByEvent(this.id).subscribe(res=>{
    this.activity=res
  })
}
    onSubmit(){
 let data={
  title:this.activityForm.value.title,
        description:this.activityForm.value.description,
        debut:this.activityForm.value.debut,
        fin:this.activityForm.value.fin,
        montant:this.activityForm.value.montant,
        type:this.activityForm.value.type
 }
 this.activityService.addEvents(this.id,data).subscribe(res=>{
  this.getAll()
 })
    }
  }