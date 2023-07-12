import { Component } from '@angular/core';
import {HotToastService} from "@ngneat/hot-toast";
import {UserService} from "../services/user.service";

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent {

  token:any;
  newPassword:any

  constructor(private userService: UserService, private toast: HotToastService){}



  reset(){
    this.userService.resetPassword(this.token,this.newPassword).subscribe(res=>{
      this.toast.success("mot de passe rénesialiser")
    },(error=>{
      this.toast.error("Invalid token")
    }))
  }
}
