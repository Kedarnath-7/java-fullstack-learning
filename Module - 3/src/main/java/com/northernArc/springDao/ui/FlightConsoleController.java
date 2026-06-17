package com.northernArc.springDao.ui;

import com.northernArc.springDao.dao.FlightDaoImpl;
import com.northernArc.springDao.entity.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Scanner;

@Component
public class FlightConsoleController {

    private Scanner scanner;
    @Autowired
    private FlightDaoImpl flightDaoImpl;

    public FlightConsoleController(Scanner scanner, FlightDaoImpl flightDaoImpl){
        this.scanner = scanner;
        this.flightDaoImpl = flightDaoImpl;
    }

    public void showWelcomeMessage(){
        System.out.println("Welcome to the flight Console Application....");
        System.out.println("Explore wide range of operations you can perform...");
    }

    public void showMenu(){
        while(true) {
            System.out.println("1. Add flight");
            System.out.println("2. View all flights");
            System.out.println("3. Update flight");
            System.out.println("4. Delete flight");
            System.out.println("5. Find by id");
            System.out.println("6. Find by flight number");
            System.out.println("7. Find by source, destination, arrival date, departure date");
            System.out.println("8. Find by source, destination");
            System.out.println("9. Find by source, destination and sort by departure date asc");
            System.out.println("10. Find by source, destination and sort by arrival date asc");
            System.out.println("11. Find by source, destination and sort by departure date desc");
            System.out.println("12. Find by source, destination and sort by arrival date desc");
            System.out.println("13. Find all flights");
            System.out.println("14. Delete all flights");
            System.out.println("15. Exit");
            System.out.println("=======================================================================");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice >= 1 && choice < 15) {
                redirectChoice(choice);
            } else if (choice == 15) {
                break;
            } else {
                System.out.println("Invalid choice....");
            }
        }

    }

    private void redirectChoice(int choice){
        switch (choice){
            case 1:
                add();
                break;
            case 2:
                findAll();
                break;
            case 3:
                updateById();
                break;
            case 4:
                deleteById();
                break;
            case 5:
                findById();
                break;
            case 6:
                findByFlightNumber();
                break;
            case 7:
                findBySourDestArrDep();
                break;
            case 8:
                findAllBySourDest();
                break;
            case 9:
                findBySourDestSortDepAsc();
                break;
            case 10:
                findBySourDestSorArrAsc();
                break;
            case 11:
                findBySourDestSortDepDesc();
                break;
            case 12:
                findBySourDestSortArrDesc();
                break;
            case 13:
                flightDaoImpl.findAllflights().forEach(System.out::println);
                break;
            case 14:
                flightDaoImpl.deleteAllFlights();
                break;
            default:
                System.out.println("Invalid choice...");

        }
    }

    private void findBySourDestSortArrDesc() {
        System.out.println("Enter source: ");
        String source = scanner.nextLine();
        System.out.println("Enter destination: ");
        String destination = scanner.nextLine();
        flightDaoImpl.findAllBySourceDestinationSortByArrDateDesc(source, destination).forEach(System.out::println);
    }

    private void findBySourDestSortDepDesc() {
        System.out.println("Enter source: ");
        String source = scanner.nextLine();
        System.out.println("Enter destination: ");
        String destination = scanner.nextLine();
        flightDaoImpl.findAllBySourceDestinationSortByDepDateDesc(source, destination).forEach(System.out::println);
    }

    private void findBySourDestSorArrAsc() {
        System.out.println("Enter source: ");
        String source = scanner.nextLine();
        System.out.println("Enter destination: ");
        String destination = scanner.nextLine();
        flightDaoImpl.findAllBySourceDestinationSortByArrDateAsc(source, destination).forEach(System.out::println);
    }

    private void findBySourDestSortDepAsc() {
        System.out.println("Enter source: ");
        String source = scanner.nextLine();
        System.out.println("Enter destination: ");
        String destination = scanner.nextLine();
        flightDaoImpl.findAllBySourceDestinationSortByDepDateAsc(source, destination).forEach(System.out::println);
    }

    private void findByFlightNumber(){
        System.out.println("Enter flight number: ");
        String flightNumber = scanner.nextLine();
        System.out.println(flightDaoImpl.findFlightByFlightNumber(flightNumber));
    }
    private void findBySourDestArrDep() {
        System.out.println("Enter source: ");
        String source = scanner.nextLine();
        System.out.println("Enter destination: ");
        String destination = scanner.nextLine();
        System.out.print("Enter arrival date-time (yyyy-MM-ddTHH:mm:ss): ");
        LocalDateTime arrivalDate = LocalDateTime.parse(scanner.nextLine());
        System.out.print("Enter departure date-time (yyyy-MM-ddTHH:mm:ss): ");
        LocalDateTime departureDate = LocalDateTime.parse(scanner.nextLine());

        Collection<Flight> flights = flightDaoImpl.findAllBySourDestArrDepDate(source, destination, departureDate, arrivalDate);
        flights.forEach(System.out::println);
    }

    private void findAllBySourDest(){
        System.out.println("Enter source: ");
        String source = scanner.nextLine();
        System.out.println("Enter destination: ");
        String destination = scanner.nextLine();
        flightDaoImpl.findAllBySourceDestination(source, destination).forEach(System.out::println);
    }
    private void add(){
        System.out.println("Enter flight number: ");
        String flightNumber = scanner.nextLine();
        System.out.print("Enter arrival date-time (yyyy-MM-ddTHH:mm:ss): ");
        LocalDateTime arrivalDate = LocalDateTime.parse(scanner.nextLine());
        System.out.print("Enter departure date-time (yyyy-MM-ddTHH:mm:ss): ");
        LocalDateTime departureDate = LocalDateTime.parse(scanner.nextLine());
        System.out.println("Enter source: ");
        String source = scanner.nextLine();
        System.out.println("Enter destination: ");
        String destination = scanner.nextLine();
        flightDaoImpl.addFlight(new Flight(flightNumber, arrivalDate, departureDate, source, destination));
    }

    private void updateById(){
        System.out.println("Enter flight id: ");
        int id = scanner.nextInt();
        System.out.println("Enter flight number: ");
        String flightNumber = scanner.nextLine();
        System.out.println("Enter source: ");
        String source = scanner.nextLine();
        System.out.println("Enter destination: ");
        String destination = scanner.nextLine();
        System.out.print("Enter arrival date-time (yyyy-MM-ddTHH:mm:ss): ");
        LocalDateTime arrivalDate = LocalDateTime.parse(scanner.nextLine());
        System.out.print("Enter departure date-time (yyyy-MM-ddTHH:mm:ss): ");
        LocalDateTime departureDate = LocalDateTime.parse(scanner.nextLine());
        flightDaoImpl.updateFlightById(id, new Flight(flightNumber, arrivalDate, departureDate, source, destination));
    }

    private void deleteById(){
        System.out.println("Enter flight id: ");
        int id = scanner.nextInt();
        flightDaoImpl.deleteFlightById(id);
    }
    private void findById(){
        System.out.println("Enter flight id: ");
        int id = scanner.nextInt();
        System.out.println(flightDaoImpl.findFlightById(id));
    }
    private void findAll(){
        flightDaoImpl.findAllflights().forEach(System.out::println);
    }



}
