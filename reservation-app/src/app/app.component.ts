import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms'
import { HttpClient } from '@angular/common/http'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'reservation-app';

  constructor(private http:HttpClient) {}

  private baseUrl:string = 'http://locahost:8080';
  private reservationUrl:string = this.baseUrl + '/room/v1/reservation/';

  rooms:Room[];

  ngOnInit() {
    this.rooms = [
      new Room("1", "12", "233"),
      new Room("2", "18", "240"),
      new Room("3", "72", "290")
    ]
  }
}

export class Room {
  id: string;
  roomNumber: string;
  price: string;

  constructor(id: string, roomNumber: string, price: string) {
    this.id = id;
    this.roomNumber = roomNumber;
    this.price = price;
  }
}
