import {Component, OnInit} from '@angular/core';
import {Role} from "../_Models/Role";
import {User} from "../_Models/User";
import {Router} from "@angular/router";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit{
role:Role;
currentUser:User;
constructor(private router: Router) {
}
  ngOnInit() {
  const role0 = localStorage.getItem('roles');
  const id = localStorage.getItem('id');
  // @ts-ignore
  this.role=role0;
  // @ts-ignore
  this.currentUser=id.toString();

}
signout(){
  localStorage.removeItem('roles');
  localStorage.removeItem('username');
  localStorage.removeItem('id');
  localStorage.removeItem('token');
  this.router.navigate(['/home']);
  setTimeout(() => {
    window.location.reload();
  }, 1500);

}

  protected readonly Role = Role;
}
