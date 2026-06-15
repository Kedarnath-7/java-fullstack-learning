package day04.jdbcDao.dao;

import day04.jdbcDao.entity.RepaymentSchedule;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

interface RepaymentScheduleDao {
    public int saveSchedule(RepaymentSchedule schedule);
    public RepaymentSchedule findScheduleById(int scheduleId);
    public Collection<RepaymentSchedule> findAllSchedules();
    public Collection<RepaymentSchedule> findByLoanId(int loanId);
    public Collection<RepaymentSchedule> findByStatus(String status);
    public Collection<RepaymentSchedule> findPendingEmis();
    public Collection<RepaymentSchedule> findPaidEmis();
    public void deleteScheduleById(int scheduleId);
    public void deleteAllSchedules();
    public void updateScheduleById(int id, RepaymentSchedule schedule);
    public Collection<RepaymentSchedule> sortByAmountAsc();
    public Collection<RepaymentSchedule> sortByAmountDesc();
    RepaymentSchedule mapToSchedule(ResultSet rs) throws SQLException;

}
