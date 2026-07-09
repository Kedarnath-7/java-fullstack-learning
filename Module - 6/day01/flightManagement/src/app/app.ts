import { Component, signal } from '@angular/core';
import { FlightsComponent } from './features/flights/components/flights-component/flights-component';


@Component({
  selector: 'app-root',
  imports: [FlightsComponent],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {

}
