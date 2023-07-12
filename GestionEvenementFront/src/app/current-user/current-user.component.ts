import {Component, OnInit} from '@angular/core';
import jwt_decode from "jwt-decode";
import {Role} from "../_Models/Role";
import {User} from "../_Models/User";

@Component({
  selector: 'app-current-user',
  templateUrl: './current-user.component.html',
  styleUrls: ['./current-user.component.css']
})
export class CurrentUserComponent  implements OnInit{
  user:User=new User();

  ngOnInit() {
    const username = localStorage.getItem('username');

    const role = localStorage.getItem('roles');
    // @ts-ignore
    this.user.role=role;
    // @ts-ignore
    this.user.username=username;
  }


}
