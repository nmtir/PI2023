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
import {LogistiqueDetailsComponent} from "./logistique-details/logistique-details.component";
import { ResourceListComponent } from './Resource-list/resource-list.component';
import { ForumDetailsComponent } from './forum-details/forum-details.component';
import { PostAddComponent } from './post-add/post-add.component';
import { PostDetailsComponent } from './post-details/post-details.component';
import {BlockedMessagesComponent} from './blocked-messages/blocked-messages.component';
import { NewEventComponent } from './dashboard/new-event/new-event.component';
import { ListEventComponent } from './dashboard/list-event/list-event.component';
import { EventsComponent } from './events/events.component';
import { ProfilComponent } from './dashboard/profil/profil.component';
import { ListMembreComponent } from './dashboard/list-membre/list-membre.component';
import { FactureComponent } from './dashboard/facture/facture.component';
import { NewUserComponent } from './dashboard/new-user/new-user.component';
import { UpdateUserComponent } from './dashboard/update-user/update-user.component';
import { ReclamationComponent } from './reclamation/reclamation.component';
import { ListeReclamationComponent } from './dashboard/liste-reclamation/liste-reclamation.component';
import { ActivityComponent } from './dashboard/activity/activity.component';
import {ForgetPasswordComponent} from "./forget-password/forget-password.component";
import {ResetPasswordComponent} from "./reset-password/reset-password.component";
import {AddStockComponent} from "./add-stock/add-stock.component";
import {GestionReservationComponent} from "./gestion-reservation/gestion-reservation.component";

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
  {path: "post", component: PostDetailsComponent},
  {path: "addpost", component: PostAddComponent},
  {path: "msg", component:BlockedMessagesComponent},
  {path: "home", component: HomeComponent},
  {path: "signaled-messages", component: BlockedMessagesComponent},
  {path: "", component: HomeComponent},
  {path: "about", component: AboutComponent},
  {path: "blog", component: BlogComponent},
  {path: "blog1", component: BlogSingleComponent},
  {path: "contact", component: ContactComponent},
  {path: "schedule", component: ScheduleComponent},
  {path: "speakers", component: SpeakersComponent},
  {path: "login", component: SignIn1Component},
  {path: "register", component: SignUp1Component},
  {path: "profile", component: ProfileComponent},
  {path: "dashboard", component: AdminDashboardComponent},
  {path: "tables", component: AdminTablesComponent},
  {path: "tables", component: AdminTablesComponent},
  {path: "new-event", component: NewEventComponent},
  {path: "list-event", component: ListEventComponent},
  {path: "events", component: EventsComponent},
  {path: "profil", component: ProfilComponent},
  {path: "list-membre", component: ListMembreComponent},
  {path: "list-membre", component: ListMembreComponent},
  {path: "facture", component: FactureComponent},
  {path: "new-user", component: NewUserComponent},
  {path: "update-user/:id", component: UpdateUserComponent},
  {path: "reclamation", component: ReclamationComponent},
  {path: "reclamations/:id", component: ListeReclamationComponent},
  {path: "activity/:id", component: ActivityComponent},
  { path: "forget-password", component: ForgetPasswordComponent },
  { path: "reset-password", component: ResetPasswordComponent },
  { path: "addstock", component: AddStockComponent },
  { path: "gestionreservations", component: GestionReservationComponent }










];



export const AppRoutingModule = RouterModule.forRoot(routes, { scrollPositionRestoration: 'enabled' });
