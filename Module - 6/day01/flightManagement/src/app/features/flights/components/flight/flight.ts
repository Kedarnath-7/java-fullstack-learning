import { Component, inject, Input } from '@angular/core';
import FlightDTO from '../../../../types/FlightDTO';
import { FlightService } from '../../services/flight-service';

@Component({
  selector: 'app-flight',
  imports: [],
  templateUrl: './flight.html',
  styleUrl: './flight.css',
})
export class Flight {
  @Input()
  flight!: FlightDTO;

  flightService = inject(FlightService);
}
