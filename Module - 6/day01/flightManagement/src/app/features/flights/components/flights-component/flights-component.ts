import { Component, inject } from '@angular/core';
import { FlightService } from '../../services/flight-service';
import { Flight } from '../flight/flight';
import { AddFlight } from '../add-flight/add-flight';
import { UpdateFlight } from '../update-flight/update-flight';

@Component({
  selector: 'app-flights-component',
  imports: [Flight, AddFlight, UpdateFlight],
  templateUrl: './flights-component.html',
  styleUrl: './flights-component.css',
})
export class FlightsComponent {
  flightService = inject(FlightService);
  
  
}
