import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AdminComponent} from './admin/admin.component'
import {CommonModule} from "@angular/common";
import {HomeComponent} from "./home/home.component";
import {AboutComponent} from "./about/about.component";
import {BlogComponent} from "./blog/blog.component";
import {BlogSingleComponent} from "./blog-single/blog-single.component";
import {ContactComponent} from "./contact/contact.component";
import {ScheduleComponent} from "./schedule/schedule.component";
import {SpeakersComponent} from "./speakers/speakers.component";

const routes: Routes = [
  {path: "home", component: HomeComponent},
  {path: "", component: HomeComponent},
  {path: "admin", component: AdminComponent},
  {path: "about", component: AboutComponent},
  {path: "blog", component: BlogComponent},
  {path: "blog1", component: BlogSingleComponent},
  {path: "contact", component: ContactComponent},
  {path: "schedule", component: ScheduleComponent},
  {path: "speakers", component: SpeakersComponent},

];



export const AppRoutingModule = RouterModule.forRoot(routes, { scrollPositionRestoration: 'enabled' });
