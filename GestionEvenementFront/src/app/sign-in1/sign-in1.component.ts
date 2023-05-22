import {Component, OnInit, ViewEncapsulation} from '@angular/core';

@Component({
  selector: 'app-sign-in1',
  templateUrl: './sign-in1.component.html',
  styleUrls: ['./sign-in1.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class SignIn1Component implements OnInit{
  ngOnInit() {
    const element1 = document.getElementById("header1");
    element1.removeAttribute("hidden");
    const element2 = document.getElementById("ftco-footer");
    element2.removeAttribute("hidden");
  }


}
