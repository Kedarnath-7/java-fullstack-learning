package day04.jdbcDao.ui;

import day04.jdbcDao.dao.RepaymentScheduleDaoImpl;

public class RepaymentScheduleMain {
    public static void main(String[] args) {
        System.out.println("Welcome to Repayment Schedule Service....");
        System.out.println("Please choose the service you want:");
        System.out.println("1. ");

        RepaymentScheduleDaoImpl repaymentScheduleDao = new RepaymentScheduleDaoImpl();

        repaymentScheduleDao.saveSchedule();
        repaymentScheduleDao.findAllSchedules();

    }
}
