import { Component, Input } from '@angular/core';
import { AstronomicalEvent } from '../model/astronomical-event';


@Component({
  selector: 'app-event-card',
  templateUrl: './event-card.component.html',
  styleUrls: ['./event-card.component.css']
})
export class EventCardComponent {


  @Input()
  event!: AstronomicalEvent;


}