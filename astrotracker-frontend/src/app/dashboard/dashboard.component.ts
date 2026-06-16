import { Component, OnInit } from '@angular/core';
import { WebsocketService } from '../service/websocket.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {


  events:any[] = [];


  constructor(
    private websocketService: WebsocketService
  ){}


  ngOnInit(): void {

    this.websocketService.connect((event:any)=>{

      console.log("Event received:", event);

      this.events.push(event);

    });

  }

}