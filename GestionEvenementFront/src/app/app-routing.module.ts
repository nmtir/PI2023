import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AdminComponent} from './admin/admin.component'
import {CommonModule} from "@angular/common";
import {HomeComponent} from "./home/home.component";

const routes: Routes = [
  {path: "", component: HomeComponent},
  {path: "admin", component: AdminComponent}

];



export const AppRoutingModule = RouterModule.forRoot(routes, { scrollPositionRestoration: 'enabled' });
