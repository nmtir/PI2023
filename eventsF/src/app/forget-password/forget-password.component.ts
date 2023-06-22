import { Component } from '@angular/core';
import {UserService} from "../services/user.service";
import {HotToastService} from "@ngneat/hot-toast";
import {Router} from "@angular/router";

@Component({
  selector: 'app-forget-password',
  templateUrl: './forget-password.component.html',
  styleUrls: ['./forget-password.component.css']
})
export class ForgetPasswordComponent {
  email:any;

  constructor(private userService:UserService, private toast: HotToastService,private router:Router){}

  reset(){
    if(this.email==''){
      this.toast.error("Entre votre email")
    }else{
      this.userService.forgetPassword(this.email).subscribe(res=>{
        this.toast.success("Email envoyé avec success")
        this.router.navigate(['reset-password'])
      },(error=>{
        this.toast.error("Email n'existe pas")
      }))
    }
  }
}
