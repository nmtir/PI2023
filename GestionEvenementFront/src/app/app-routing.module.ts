import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CommonModule} from "@angular/common";
import {HomeComponent} from "./home/home.component";
import {AboutComponent} from "./about/about.component";
import {BlogComponent} from "./blog/blog.component";
import {BlogSingleComponent} from "./blog-single/blog-single.component";
import {ContactComponent} from "./contact/contact.component";
import {ScheduleComponent} from "./schedule/schedule.component";
import {SpeakersComponent} from "./speakers/speakers.component";
import {SignIn1Component} from "./sign-in1/sign-in1.component";
import {SignUp1Component} from "./sign-up1/sign-up1.component";
import {ProfileComponent} from "./profile/profile.component";
import {AdminDashboardComponent} from "./admin-dashboard/admin-dashboard.component";
import {AdminTablesComponent} from "./admin-tables/admin-tables.component";
import {LogistiqueDetailsComponent} from "./logistique/logistique-details/logistique-details.component";
import { ResourceListComponent } from './logistique/Resource-list/resource-list.component';
import { ForumDetailsComponent } from './forum-details/forum-details.component';
import { PostAddComponent } from './post-add/post-add.component';

const routes: Routes = [
  {path: "home", component: HomeComponent},
  {path: "", component: HomeComponent},
  {path: "about", component: AboutComponent},
  {path: "blog", component: BlogComponent},
  {path: "blog1", component: BlogSingleComponent},
  {path: "contact", component: ContactComponent},
  {path: "schedule", component: ScheduleComponent},
  {path: "speakers", component: SpeakersComponent},
  {path: "signin", component: SignIn1Component},
  {path: "signup", component: SignUp1Component},
  {path: "profile", component: ProfileComponent},
  {path: "dashboard", component: AdminDashboardComponent},
  {path: "tables", component: AdminTablesComponent},
  {path: "logistique", component: LogistiqueDetailsComponent},
  {path: "resource", component: ResourceListComponent},
  {path: "forum", component: ForumDetailsComponent},
  {path: "addpost/:id", component: PostAddComponent},






];



export const AppRoutingModule = RouterModule.forRoot(routes, { scrollPositionRestoration: 'enabled' });
