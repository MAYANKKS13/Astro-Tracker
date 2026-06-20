import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatChipsModule } from '@angular/material/chips';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { EventCardComponent } from './event-card/event-card.component';


@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    EventCardComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    MatCardModule,
    MatToolbarModule,
    MatIconModule,
    MatChipsModule,
    MatButtonModule
  ],
  providers: [],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule { }