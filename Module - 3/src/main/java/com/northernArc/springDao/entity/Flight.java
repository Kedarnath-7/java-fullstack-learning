package com.northernArc.springDao.entity;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Flight {
    private int id;
    private String flightNumber;
    private LocalDateTime arrivalDateTime;
    private LocalDateTime departureDateTime;
    private String source;
    private String destination;

    public Flight(){

    }
    public Flight(String flightNumber, LocalDateTime arrivalDateTime, LocalDateTime departureDateTime, String source, String destination) {
        this.flightNumber = flightNumber;
        this.arrivalDateTime = arrivalDateTime;
        this.departureDateTime = departureDateTime;
        this.source = source;
        this.destination = destination;
    }


    public Flight(int id, String flightNumber, LocalDateTime arrivalDateTime, LocalDateTime departureDateTime, String source, String destination) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.arrivalDateTime = arrivalDateTime;
        this.departureDateTime = departureDateTime;
        this.source = source;
        this.destination = destination;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public LocalDateTime getArrivalDateTime() {
        return this.arrivalDateTime;
    }

    public void setArrivalDateTime(LocalDateTime arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    public LocalDateTime getDepartureDateTime() {
        return this.departureDateTime;
    }

    public void setDepartureDateTime(LocalDateTime departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }


    @Override
    public String toString(){
        return "Flight - [id: " + this.id + ", number: " + this.flightNumber + "]";
    }
}
