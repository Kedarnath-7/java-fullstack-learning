import { Component, inject } from '@angular/core';
import { FlightService } from '../../services/flight-service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-add-flight',
  imports: [FormsModule],
  templateUrl: './add-flight.html',
  styleUrl: './add-flight.css',
})
export class AddFlight {

  flightService = inject(FlightService);

  newFlight = {
    id: 0,
    flightNumber: '',
    source: '',
    destination: '',
  };
  
  addFlight(): void {
    if(this.newFlight.flightNumber.trim() && this.newFlight.source.trim() && this.newFlight.destination.trim()) {
      this.flightService.addFlight(this.newFlight);
      this.newFlight = {
        id: 0,
        flightNumber: '',
        source: '',
        destination: '',
      };
    }
  }
}
