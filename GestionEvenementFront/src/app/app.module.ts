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
import {HttpClientModule} from "@angular/common/http";
import { ForumDetailsComponent } from './forum-details/forum-details.component';
import { PostListComponent } from './post-list/post-list.component';
import { MessageListComponent } from './message-list/message-list.component';
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
    MessageListComponent,
    PostAddComponent,
    MessageAddComponent,
    LogistiqueDetailsComponent,
    CurrentUserComponent,
    PostDetailsComponent,
    DateAgoPipe
  ],
    imports: [
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
        MatInputModule
    ],

  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
