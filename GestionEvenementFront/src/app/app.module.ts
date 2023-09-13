import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import {NgOptimizedImage} from "@angular/common";
import { FooterComponent } from './footer/footer.component';
import { HeaderComponent } from './header/header.component';
import { AboutComponent } from './about/about.component';
import { BlogComponent } from './blog/blog.component';
import { BlogSingleComponent } from './blog-single/blog-single.component';
import { ContactComponent } from './contact/contact.component';
import { ScheduleComponent } from './schedule/schedule.component';
import { SpeakersComponent } from './speakers/speakers.component';
import { SignIn1Component } from './sign-in1/sign-in1.component';
import { SignUp1Component } from './sign-up1/sign-up1.component';
import { ProfileComponent } from './profile/profile.component';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { AdminTablesComponent } from './admin-tables/admin-tables.component';
import { ResourceListComponent } from './Resource-list/resource-list.component';
import { LogistiqueDetailsComponent } from './logistique-details/logistique-details.component';
import { ForumDetailsComponent } from './forum-details/forum-details.component';
import { PostListComponent } from './post-list/post-list.component';
import { PostAddComponent } from './post-add/post-add.component';
import { MessageAddComponent } from './message-add/message-add.component';
import {CommonModule} from "@angular/common";
import {DragDropModule,CdkDrag,CdkDropList,CdkDragDrop, moveItemInArray} from '@angular/cdk/drag-drop';
import { CurrentUserComponent } from './current-user/current-user.component';
import { PostDetailsComponent } from './post-details/post-details.component';
import {MatIconModule} from '@angular/material/icon';
import {MatDividerModule} from '@angular/material/divider';
import {MatButtonModule} from '@angular/material/button';
import {MatExpansionModule} from "@angular/material/expansion";
import { DateAgoPipe } from './_pipes/date-ago.pipe';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatInputModule} from "@angular/material/input";
import {MatSelectModule} from '@angular/material/select';
import {MatFormFieldModule} from '@angular/material/form-field';
import { NewEventComponent } from './dashboard/new-event/new-event.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { ListEventComponent } from './dashboard/list-event/list-event.component';
import { ListMembreComponent } from './dashboard/list-membre/list-membre.component';
import { HttpClientModule } from '@angular/common/http';
import { HotToastModule } from '@ngneat/hot-toast';
import { EventsComponent } from './events/events.component';
import { ProfilComponent } from './dashboard/profil/profil.component';
import { FactureComponent } from './dashboard/facture/facture.component';
import { FactureService } from './services/facture.service';
import { NewUserComponent } from './dashboard/new-user/new-user.component';
import { UpdateUserComponent } from './dashboard/update-user/update-user.component';
import { ReclamationService } from './services/reclamation.service';
import { ListeReclamationComponent } from './dashboard/liste-reclamation/liste-reclamation.component';
import { ReclamationComponent } from './reclamation/reclamation.component';
import { ActivityComponent } from './dashboard/activity/activity.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { ForgetPasswordComponent } from './forget-password/forget-password.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { BlockedMessagesComponent } from './blocked-messages/blocked-messages.component';
import { AddStockComponent } from './add-stock/add-stock.component';
import { HousingComponent } from './housing/housing.component';
import { TransportComponent } from './transport/transport.component';
import { GestionReservationComponent } from './gestion-reservation/gestion-reservation.component';
import { StatsComponent } from './stats/stats.component';
import { ChartsComponent } from './charts/charts.component';
import {NgApexchartsModule} from "ng-apexcharts";
import { LoadingPageComponent } from './loading-page/loading-page.component';
@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    FooterComponent,
    HeaderComponent,
    AboutComponent,
    BlogComponent,
    BlogSingleComponent,
    ContactComponent,
    ScheduleComponent,
    SpeakersComponent,
    SignIn1Component,
    SignUp1Component,
    ProfileComponent,
    AdminDashboardComponent,
    AdminTablesComponent,
    ResourceListComponent,
    LogistiqueDetailsComponent,
    ForumDetailsComponent,
    PostListComponent,
    PostAddComponent,
    MessageAddComponent,
    LogistiqueDetailsComponent,
    CurrentUserComponent,
    PostDetailsComponent,
    DateAgoPipe,
    AppComponent,
    HomeComponent,
    FooterComponent,
    HeaderComponent,
    AboutComponent,
    BlogComponent,
    BlogSingleComponent,
    ContactComponent,
    ScheduleComponent,
    SpeakersComponent,
    SignIn1Component,
    SignUp1Component,
    ProfileComponent,
    AdminDashboardComponent,
    AdminTablesComponent,
    NewEventComponent,
    SidebarComponent,
    ListEventComponent,
    ListMembreComponent,
    EventsComponent,
    ProfilComponent,
    FactureComponent,
    NewUserComponent,
    UpdateUserComponent,
    ReclamationComponent,
    ListeReclamationComponent,
    ActivityComponent,
    ForgetPasswordComponent,
    ResetPasswordComponent,
    BlockedMessagesComponent,
    AddStockComponent,
    HousingComponent,
    TransportComponent,
    GestionReservationComponent,
    StatsComponent,
    ChartsComponent,
    LoadingPageComponent
  ],
    imports: [
        NgApexchartsModule,
        FormsModule,
        ReactiveFormsModule,
        CommonModule,
        DragDropModule,
        BrowserModule,
        HttpClientModule,
        FormsModule,
        AppRoutingModule,
        NgOptimizedImage,
        MatIconModule,
        MatDividerModule,
        MatButtonModule,
        MatExpansionModule,
        BrowserAnimationsModule,
        MatInputModule,
        MatFormFieldModule,
        MatSelectModule,
      CommonModule,
      BrowserModule,
      ReactiveFormsModule,
      FormsModule,
      AppRoutingModule,
      NgOptimizedImage,
      HttpClientModule,
      HotToastModule.forRoot(),
      NgxPaginationModule
    ],

  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
