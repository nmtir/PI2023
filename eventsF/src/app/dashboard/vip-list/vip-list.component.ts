import {Component, ViewChild} from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';

import { ActivatedRoute, Router } from '@angular/router';
import { EventService } from 'src/app/services/event.service';
import {UserService} from "../../services/user.service";

@Component({
  selector: 'app-vip-list',
  templateUrl: './vip-list.component.html',
  styleUrls: ['./vip-list.component.css']
})
export class VipListComponent {

  displayedColumns = ['id', 'firstName', 'lastName', 'username'];
  dataSource: MatTableDataSource<any>;

  @ViewChild(MatSort) sort: MatSort;
  id:any
  constructor( private route:ActivatedRoute,private router: Router,
               private userService:UserService){}
  ngOnInit(): void {

    this.userService.getAll().subscribe(res=>{
      let vips=res.filter(el=>el.nbConnexion >2)
      console.log(vips)
      this.dataSource = new MatTableDataSource(vips);
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

  ngAfterViewInit() {

    this.dataSource.sort = this.sort;
  }

  applyFilter(filterValue: string) {
    filterValue = filterValue.trim(); // Remove whitespace
    filterValue = filterValue.toLowerCase(); // Datasource defaults to lowercase matches
    this.dataSource.filter = filterValue;
  }
}
