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


  events: AstronomicalEvent[] = [];

  filteredEvents: AstronomicalEvent[] = [];


  loading = true;

  errorMessage = '';



  totalEvents = 0;

  totalEventTypes = 0;

  sources: string[] = [];



  selectedFilter = 'ALL';



  filters = [
    'ALL',
    'ASTEROID_CLOSE_APPROACH',
    'METEOR_SHOWER',
    'SOLAR_FLARE'
  ];



  constructor(
    private eventService: EventService,
    private websocketService: WebsocketService
  ) {}



  ngOnInit(): void {


    this.eventService.getLatestEvents()
      .subscribe({

        next: (data: AstronomicalEvent[]) => {


          this.events = data;


          this.applyFilter();


          this.calculateStatistics();


          this.loading = false;

        },


        error: () => {

          this.errorMessage =
          "Unable to load events";

          this.loading = false;

        }

      });



    this.websocketService.connect(
      (event: AstronomicalEvent) => {


        if(!this.events.some(
          existing => existing.id === event.id
        )){


          this.events.unshift(event);


          this.applyFilter();


          this.calculateStatistics();

        }

      }
    );


  }





  applyFilter(): void {


    if(this.selectedFilter === 'ALL'){

      this.filteredEvents = this.events;

    }
    else {


      this.filteredEvents =
      this.events.filter(
        event =>
        event.eventType === this.selectedFilter
      );

    }


  }





  changeFilter(filter:string):void {


    this.selectedFilter = filter;


    this.applyFilter();

  }






  private calculateStatistics():void {


    this.totalEvents =
      this.events.length;



    this.totalEventTypes =
      new Set(
        this.events.map(
          event => event.eventType
        )
      ).size;



    this.sources =
      Array.from(
        new Set(
          this.events.map(
            event => event.source
          )
        )
      );


  }


}