import { Component, OnInit } from '@angular/core';
import { AstronomicalEvent } from '../model/astronomical-event';
import { EventService } from '../service/event.service';
import { WebsocketService } from '../service/websocket.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {


  events:AstronomicalEvent[]=[];
  loading: boolean = true;
  errorMessage: string = '';

  constructor(
    private websocketService: WebsocketService,
    private eventService:EventService
  ){}

  ngOnInit(): void {

    this.eventService.getLatestEvents()
      .subscribe({
        next: (data: AstronomicalEvent[]) => {
          console.log("Latest events loaded:", data);
          this.events = data;
          this.loading = false;
        },

        error: (error) => {
          console.error("Failed to load events", error);
          this.errorMessage = "Unable to load astronomical events";
          this.loading = false;
        }

      });

    this.websocketService.connect((event:AstronomicalEvent)=>{

      console.log("Event received:", event);
      
      if(!this.events.some(e=> e.id === event.id)) {
        this.events.unshift(event);
      }

    });

  }

}