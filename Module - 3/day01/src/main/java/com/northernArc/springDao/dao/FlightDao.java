package com.northernArc.springDao.dao;

import com.northernArc.springDao.entity.Flight;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;

public interface FlightDao {
    public int addFlight(Flight flight);
    public void updateFlightById(int id, Flight flight);
    public void deleteFlightById(int id);
    public Flight findFlightById(int id);
    public Flight findFlightByFlightNumber(String flightNumber);
    public Collection<Flight> findAllBySourDestArrDepDate(String source, String destination, LocalDateTime departureDateTime, LocalDateTime arrivalDateTime);
    public Collection<Flight> findAllBySourceDestination(String source, String destination);
    public Collection<Flight> findAllBySourceDestinationSortByDepDateAsc(String source, String destination);
    public Collection<Flight> findAllBySourceDestinationSortByArrDateAsc(String source, String destination);
    public Collection<Flight> findAllBySourceDestinationSortByDepDateDesc(String source, String destination);
    public Collection<Flight> findAllBySourceDestinationSortByArrDateDesc(String source, String destination);
    public Collection<Flight> findAllflights();
    public void deleteAllFlights();
}
