import { Injectable } from '@angular/core';
import * as SockJS from 'sockjs-client';
import { Client } from '@stomp/stompjs';


@Injectable({
  providedIn: 'root'
})
export class WebsocketService {


private client!: Client;


connect(callback:any){


const socket = new SockJS(
  'http://localhost:8080/ws'
);


this.client = new Client({

  webSocketFactory:()=>socket,

  reconnectDelay:5000

});


this.client.onConnect = ()=>{


console.log("Angular websocket connected");


this.client.subscribe(
  '/topic/events',
  message=>{

      callback(JSON.parse(message.body));

  }
);


};


this.client.activate();


}


}