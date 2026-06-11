package day02.streams.dao;

import day02.streams.entity.Loan;

import java.util.List;
import java.util.Optional;

public interface LoanDao {
    public void saveLoan(Loan loan);
    public void updateInterestByType(String type, double interestRate);
    public void deleteLoan(Loan loan);
    public Iterable<Loan> findAll();
    public Optional<Loan> findLoanById(int id);
    public List<Loan> findLoanByStatus(String status);
    public List<Loan> findLoanByType(String type);
    public List<Loan> findLoanByTenure(double tenture);
    public void deleteAll();
    public List<Loan> updateStatusById(String status, int id);
    public double loanDeduction();
    public void maxLoanAmount();
    public void minLoanAmount();
    public void displayAllLoans();
}
