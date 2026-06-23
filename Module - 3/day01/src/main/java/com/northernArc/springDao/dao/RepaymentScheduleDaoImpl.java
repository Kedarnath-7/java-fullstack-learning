package com.northernArc.springDao.dao;

import com.northernArc.springDao.connection.DBManager;
import com.northernArc.springDao.entity.RepaymentSchedule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;


public class RepaymentScheduleDaoImpl implements RepaymentScheduleDao {
    @Override
    public int saveSchedule(RepaymentSchedule schedule) {
        try(Connection con = DBManager.getConnection()){
            String sql = "insert into repayment_schedule(loan_id, emi_amount, principal_amount, interest_amount, status) values(?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, schedule.getLoanId());
            stmt.setDouble(2, schedule.getEmiAmount());
            stmt.setDouble(3, schedule.getPrincipalAmount());
            stmt.setDouble(4, schedule.getInterestAmount());
            stmt.setString(5, schedule.getStatus());
            return stmt.executeUpdate();

        }catch (SQLException e){
            System.out.println("Issue in db connectivity....");
        }
        return 0;
    }

    @Override
    public RepaymentSchedule findScheduleById(int scheduleId) {
        try(Connection con = DBManager.getConnection()){
            String sql = "select * from repayment_schedule where loan_id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return mapToSchedule(rs);
            }
        }catch (SQLException e){
            System.out.println("Issue in db connectivity...." + e.getMessage());
        }
        return null;
    }

    @Override
    public Collection<RepaymentSchedule> findAllSchedules() {
        Collection<RepaymentSchedule> schedules = new LinkedList<>();
        try(Connection con = DBManager.getConnection()){
            String sql = "select * from repayment_schedule;";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                schedules.add(mapToSchedule(rs));
            }
        }catch (SQLException e){
            System.out.println("Issue in db connectivity... " + e.getMessage());
        }
        return schedules;
    }

    @Override
    public Collection<RepaymentSchedule> findByLoanId(int loanId) {
        Collection<RepaymentSchedule> schedules = new LinkedList<>();
        try(Connection con = DBManager.getConnection()){
            String sql = "select * from repayment_schedule where loan_id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, loanId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                schedules.add(mapToSchedule(rs));
            }
        }catch(SQLException e){
            System.out.println("Issue in db connectivity.. " + e.getMessage());
        }
        return schedules;
    }

    @Override
    public Collection<RepaymentSchedule> findByStatus(String status) {
        Collection<RepaymentSchedule> schedules = new LinkedList<>();
        try(Connection con = DBManager.getConnection()){
            String sql = "select * from repayment_schedule where status = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,status);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                schedules.add(mapToSchedule(rs));
            }
        }catch(SQLException e){
            System.out.println("Issue in db connectivity.. " + e.getMessage());
        }
        return schedules;
    }

    @Override
    public Collection<RepaymentSchedule> findPendingEmis() {
        Collection<RepaymentSchedule> schedules = new LinkedList<>();
        try(Connection con = DBManager.getConnection()){
            String sql = "select * from repayment_schedule where status = 'pending'";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                schedules.add(mapToSchedule(rs));
            }
        }catch(SQLException e){
            System.out.println("Issue in db connectivity.. " + e.getMessage());
        }
        return schedules;
    }

    @Override
    public Collection<RepaymentSchedule> findPaidEmis() {
        Collection<RepaymentSchedule> schedules = new LinkedList<>();
        try(Connection con = DBManager.getConnection()){
            String sql = "select * from repayment_schedule where status = 'paid'";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                schedules.add(mapToSchedule(rs));
            }
        }catch(SQLException e){
            System.out.println("Issue in db connectivity.. " + e.getMessage());
        }
        return schedules;
    }

    @Override
    public void deleteScheduleById(int scheduleId) {
        Collection<RepaymentSchedule> schedules = new LinkedList<>();
        try(Connection con = DBManager.getConnection()){
            String sql = "delete from repayment_schedule where loan_id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, scheduleId);
            stmt.executeUpdate();
            System.out.println("Deleted successfully....");
        }catch(SQLException e){
            System.out.println("Issue in db connectivity.. " + e.getMessage());
        }
    }

    @Override
    public void deleteAllSchedules() {
        Collection<RepaymentSchedule> schedules = new LinkedList<>();
        try(Connection con = DBManager.getConnection()){
            String sql = "delete from repayment_schedule;";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
        }catch(SQLException e){
            System.out.println("Issue in db connectivity.. " + e.getMessage());
        }
    }

    @Override
    public void updateScheduleById(int id, RepaymentSchedule schedule) {
        try(Connection con = DBManager.getConnection()){
            String sql = "update repayment_schedule set loan_id = ?, emi_amount=?, principal_amount=?, interest_amount=?, status=? where loan_id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, schedule.getLoanId());
            stmt.setDouble(2, schedule.getEmiAmount());
            stmt.setDouble(3, schedule.getPrincipalAmount());
            stmt.setDouble(4, schedule.getInterestAmount());
            stmt.setString(5, schedule.getStatus());
            stmt.setInt(6, schedule.getScheduleId());
            stmt.executeUpdate();

        }catch(SQLException e){
            System.out.println("Issue in db connectivity.. " + e.getMessage());
        }
    }

    @Override
    public Collection<RepaymentSchedule> sortByAmountAsc() {
        Collection<RepaymentSchedule> schedules = new LinkedList<>();
        try(Connection con = DBManager.getConnection()){
            String sql = "select * from repayment_schedule order by emi_amount asc";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                schedules.add(mapToSchedule(rs));
            }
        }catch(SQLException e){
            System.out.println("Issue in db connectivity.. " + e.getMessage());
        }
        return schedules;
    }

    @Override
    public Collection<RepaymentSchedule> sortByAmountDesc() {
        Collection<RepaymentSchedule> schedules = new LinkedList<>();
        try(Connection con = DBManager.getConnection()){
            String sql = "select * from repayment_schedule order by emi_amount desc";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                schedules.add(mapToSchedule(rs));
            }
        }catch(SQLException e){
            System.out.println("Issue in db connectivity.. " + e.getMessage());
        }
        return schedules;
    }

    @Override
    public RepaymentSchedule mapToSchedule(ResultSet rs) throws SQLException {
        return new RepaymentSchedule(rs.getInt("schedule_id"),
                rs.getInt("loan_id"),
                rs.getDouble("emi_amount"),
                rs.getDouble("principal_amount"),
                rs.getDouble("interest_amount"),
                rs.getString("status"));
    }
}
