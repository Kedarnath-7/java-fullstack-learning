package day02.streams.ui;

import day02.streams.dao.LoanDaoImpl;
import day02.streams.entity.Loan;

public class LoanMain {
    public static void main(String[] args) {
        LoanDaoImpl  loanDao = new LoanDaoImpl();
        loanDao.saveLoan(new Loan(20, 2000000, 8.4, 48, "Approved", "Educational"));
        loanDao.saveLoan(new Loan(25, 100000, 14.5, 24, "Processing", "Personal"));
        loanDao.saveLoan(new Loan(30, 500000, 12.2, 36, "Rejected", "Gold"));
        loanDao.saveLoan(new Loan(40, 1200000, 13.23, 24, "Processing", "Vehicle"));
        loanDao.saveLoan(new Loan(60, 5000000, 9.3, 120, "Rejected", "Home"));
        loanDao.saveLoan(new Loan(70, 124000, 15.4, 10, "Approved", "Personal"));
        loanDao.saveLoan(new Loan(80, 90000, 9.1, 18, "Approved", "Gold"));

        System.out.println("Finding by id ");
        System.out.println(loanDao.findLoanById(25));
        System.out.println("===================================");
        System.out.println("Finding by status: ");
        System.out.println(loanDao.findLoanByStatus("Approved"));
        System.out.println("====================================");
        System.out.println("Finding by tenure: ");
        System.out.println(loanDao.findLoanByTenure(24));
        System.out.println("==============================");
        System.out.println("Finding by type: ");
        System.out.println(loanDao.findLoanByType("Gold"));

        System.out.println("=====================");
        System.out.println("Updating interest rate by type: ");
        loanDao.updateInterestByType("Gold", 9.4);

        System.out.println("=======================");
        System.out.println("Updating loan status from id: ");
        System.out.println(loanDao.updateStatusById("Approved", 25));

        System.out.println("=====================================");
        System.out.println("Loan deduction: ");
        System.out.println(loanDao.loanDeduction());

        System.out.println("=====================");
        System.out.println("Displaying all loans: ");
        loanDao.displayAllLoans();
    }
}
