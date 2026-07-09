import { Injectable } from '@angular/core';
import FlightDTO from '../../../types/FlightDTO';

@Injectable({
  providedIn: 'root',
})
export class FlightService {
    private flights: FlightDTO[] = [
        { id: 1, flightNumber: 'AI-101', source: 'New York', destination: 'London' },
        { id: 2, flightNumber: 'BA-202', source: 'London', destination: 'Paris' },
        { id: 3, flightNumber: 'DL-303', source: 'Paris', destination: 'Berlin' },
    ];

    getFlights(): FlightDTO[] {
        return this.flights;
    }

    addFlight(flight: FlightDTO): void {
        this.flights.push(flight);
    }

    removeFlight(flightId: number): void {
        this.flights = this.flights.filter(flight => flight.id !== flightId);
    }

    updateFlight(updatedFlight: FlightDTO): void {
        const index = this.flights.findIndex(flight => flight.id === updatedFlight.id);
        if (index !== -1) {
            this.flights[index] = updatedFlight;
        }
    }


    
}
