import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AstronomicalEvent } from '../model/astronomical-event';


@Injectable({
  providedIn:'root'
})
export class EventService {


private apiUrl = "http://localhost:8080/events";


constructor(
 private http:HttpClient
){}



getAllEvents():Observable<AstronomicalEvent[]>{

 return this.http.get<AstronomicalEvent[]>(this.apiUrl);

}


}