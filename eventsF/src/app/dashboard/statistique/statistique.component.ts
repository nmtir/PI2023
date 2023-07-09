import { Component } from '@angular/core';
import { ChartOptions } from 'chart.js';
import {UserService} from "../../services/user.service";
import { Router } from '@angular/router';
@Component({
  selector: 'app-statistique',
  templateUrl: './statistique.component.html',
  styleUrls: ['./statistique.component.css']
})
export class StatistiqueComponent {
  barChartOptions: ChartOptions = {
    responsive: true,
  };
  barChartLabels: any = [];
  barChartType: any = 'bar';
  barChartLegend = true;
  barChartPlugins = [];
  barChartData: any = [
    { data: [], label: 'L\'utilisateur le plus connecté' }
  ];
  //Injection de dependance :permet de créér un code faiblement couplé
  constructor(private userService:UserService,private router: Router){}
//cycle de vie de componenet angular
  ngOnInit(): void {
    this.userService.getStat().subscribe(res=>{
      let i=0
      for(let i=0;i<res.length;i++){
        let arr=res[i];
        this.barChartLabels.push(arr[0])
        this.barChartData[0].data.push(arr[1])


      }

    })



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

  logout(){
    localStorage.removeItem('id')
    localStorage.removeItem('token')
    this.router.navigate(['/login'])
  }
}
