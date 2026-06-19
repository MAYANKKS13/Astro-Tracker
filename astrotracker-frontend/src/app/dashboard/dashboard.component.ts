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


  constructor(
    private websocketService: WebsocketService,
    private eventService:EventService
  ){}


  ngOnInit(): void {

    this.eventService.getAllEvents()
    .subscribe(data=>{

        console.log("Existing events:",data);

        this.events=data;

    });

    this.websocketService.connect((event:AstronomicalEvent)=>{

      console.log("Event received:", event);

      if(!this.events.some(e=> e.id === event.id)) {
        this.events.push(event);
      }

    });

  }

}