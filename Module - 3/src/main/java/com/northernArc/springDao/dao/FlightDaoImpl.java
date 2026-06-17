package com.northernArc.springDao.dao;
import com.northernArc.springDao.connection.DBManager;
import com.northernArc.springDao.entity.Flight;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;


@Component
public class FlightDaoImpl implements FlightDao{

    @Override
    public int addFlight(Flight flight) {
        try(Connection con = DBManager.getConnection()){
            String sql = "insert into flight(flightnumber, arrivaldatetime, departuredatetime, source, destination) values(?, ?, ?, ?, ?);";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, flight.getFlightNumber());
            stmt.setTimestamp(2, Timestamp.valueOf(flight.getArrivalDateTime()));
            stmt.setTimestamp(3, Timestamp.valueOf(flight.getDepartureDateTime()));
            stmt.setString(4, flight.getSource());
            stmt.setString(5, flight.getDestination());
            return stmt.executeUpdate();
        }catch(SQLException e){
            System.out.println("Issue in db connectivity..." + e.getMessage());
        }
        return 0;
    }

    @Override
    public void updateFlightById(int id, Flight flight) {
        try(Connection con = DBManager.getConnection()){
            String sql = "update flight set flightNumber=?, arrivalDate=?, departureDate=?, source=?, destination=? where id=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, flight.getFlightNumber());
            stmt.setTimestamp(2, Timestamp.valueOf(flight.getArrivalDateTime()));
            stmt.setTimestamp(3, Timestamp.valueOf(flight.getDepartureDateTime()));
            stmt.setString(4, flight.getSource());
            stmt.setString(5, flight.getDestination());
            stmt.setInt(6, id);
            stmt.executeUpdate();
        }catch(SQLException e){
            System.out.println("Issue in db connectivity..." + e.getMessage());
        }
    }

    @Override
    public void deleteFlightById(int id) {
        try(Connection con = DBManager.getConnection()){
            String sql = "delete from flight where id=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }catch(SQLException e){
            System.out.println("Issue in db connectivity..." + e.getMessage());
        }
    }

    @Override
    public Flight findFlightById(int id) {
        try(Connection con = DBManager.getConnection()){
            String sql = "select * from flight where id=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return mapToFlight(rs);
            }
        }catch(SQLException e){
            System.out.println("Issue in db connectivity..." + e.getMessage());
        }
        return null;
    }

    private Flight mapToFlight(ResultSet rs) throws SQLException {
        return new Flight(
                rs.getInt("id"),
                rs.getString("flightnumber"),
                rs.getTimestamp("arrivaldatetime").toLocalDateTime(),
                rs.getTimestamp("departuredatetime").toLocalDateTime(),
                rs.getString("source"),
                rs.getString("destination")
        );
    }

    @Override
    public Flight findFlightByFlightNumber(String flightNumber) {
        try(Connection con = DBManager.getConnection()){
            String sql = "select * from flight where flightnumber=?;";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, flightNumber);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return mapToFlight(rs);
            }
        }catch(SQLException e){
            System.out.println("Issue in db connectivity..." + e.getMessage());
        }
        return null;
    }

    @Override
    public Collection<Flight> findAllBySourDestArrDepDate(String source, String destination, LocalDateTime departureDateTime, LocalDateTime arrivalDateTime) {
        Collection<Flight> flights = new LinkedList<>();
        try(Connection con = DBManager.getConnection()){
            String sql = "select * from flight where source=? and destination=? and arrivaldatetime=? and departuredatetime=?;";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, source);
            stmt.setString(2, destination);
            stmt.setTimestamp(3, Timestamp.valueOf(arrivalDateTime));
            stmt.setTimestamp(4, Timestamp.valueOf(departureDateTime));
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                flights.add(mapToFlight(rs));
            }
        }catch(SQLException e){
            System.out.println("Issue in db connectivity..." + e.getMessage());
        }
        return flights;
    }

    @Override
    public Collection<Flight> findAllBySourceDestination(String source, String destination) {
        Collection<Flight> flights = new LinkedList<>();
        try(Connection con = DBManager.getConnection()){
            String sql = "select * from flight where source=? and destination=?;";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, source);
            stmt.setString(2, destination);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                flights.add(mapToFlight(rs));
            }
        }catch(SQLException e){
            System.out.println("Issue in db connectivity..." + e.getMessage());
        }
        return flights;
    }

    @Override
    public Collection<Flight> findAllBySourceDestinationSortByDepDateAsc(String source, String destination) {
        Collection<Flight> flights = new LinkedList<>();
        try(Connection con = DBManager.getConnection()){
            String sql = "select * from flight where source=? and destination=? order by departuredatetime asc;";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, source);
            stmt.setString(2, destination);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                flights.add(mapToFlight(rs));
            }
        }catch(SQLException e){
            System.out.println("Issue in db connectivity..." + e.getMessage());
        }
        return flights;
    }

    @Override
    public Collection<Flight> findAllBySourceDestinationSortByArrDateAsc(String source, String destination) {
        Collection<Flight> flights = new LinkedList<>();
        try(Connection con = DBManager.getConnection()){
            String sql = "select * from flight where source=? and destination=? order by arrivaldatetime asc;";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, source);
            stmt.setString(2, destination);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                flights.add(mapToFlight(rs));
            }
        }catch(SQLException e){
            System.out.println("Issue in db connectivity..." + e.getMessage());
        }
        return flights;
    }

    @Override
    public Collection<Flight> findAllBySourceDestinationSortByDepDateDesc(String source, String destination) {
        Collection<Flight> flights = new LinkedList<>();
        try(Connection con = DBManager.getConnection()){
            String sql = "select * from flight where source=? and destination=? order by departuredatetime desc;";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, source);
            stmt.setString(2, destination);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                flights.add(mapToFlight(rs));
            }
        }catch(SQLException e){
            System.out.println("Issue in db connectivity..." + e.getMessage());
        }
        return flights;
    }

    @Override
    public Collection<Flight> findAllBySourceDestinationSortByArrDateDesc(String source, String destination) {
        Collection<Flight> flights = new LinkedList<>();
        try(Connection con = DBManager.getConnection()){
            String sql = "select * from flight where source=? and destination=? order by arrivaldatetime desc;";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, source);
            stmt.setString(2, destination);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                flights.add(mapToFlight(rs));
            }
        }catch(SQLException e){
            System.out.println("Issue in db connectivity..." + e.getMessage());
        }
        return flights;
    }

    @Override
    public Collection<Flight> findAllflights() {
        Collection<Flight> flights = new LinkedList<>();
        try(Connection con = DBManager.getConnection()){
            String sql = "select * from flight;";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                flights.add(mapToFlight(rs));
            }
        }catch(SQLException e){
            System.out.println("Issue in db connectivity..." + e.getMessage());
        }
        return flights;
    }

    @Override
    public void deleteAllFlights() {
        try(Connection con = DBManager.getConnection()){
            String sql = "delete from flight;";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
        }catch(SQLException e){
            System.out.println("Issue in db connectivity..." + e.getMessage());
        }
    }

}
