package day02.streams.dao;

import day02.streams.entity.Loan;

import java.util.*;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class LoanDaoImpl implements LoanDao{
    private final Set<Loan> loans = new LinkedHashSet<>();

    @Override
    public void saveLoan(Loan loan) {
        this.loans.add(loan);
    }

    @Override
    public void updateInterestByType(String type, double interestRate) {
        List<Loan> newLoans = this.loans.stream().filter((Loan loan)-> loan.getLoanType().equalsIgnoreCase(type)).map((Loan loan)->{
            loan.setInterestRate(interestRate);
            return loan;
        }).toList();
        System.out.println("Updated loans: "+ newLoans);

    }

    @Override
    public List<Loan> updateStatusById(String status, int id){

          //inner class
//        class myOperator implements UnaryOperator<Loan>{
//            @Override
//            public Loan apply(Loan loan) {
//                loan.setStatus(status);
//                return loan;
//            }
//        }
//        UnaryOperator<Loan> myOp = new myOperator();
//
//        return this.loans.stream().filter(loan->loan.getLoadId()==id).map(myOp).toList();


        // local class
//        UnaryOperator<Loan> myop2 = new UnaryOperator<Loan>() {
//            @Override
//            public Loan apply(Loan loan) {
//                loan.setStatus(status);
//                return loan;
//            }
//        };
//        return this.loans.stream().filter(loan->loan.getLoadId() == id).map(myop2).toList();


        // anonymous class
//
//        return this.loans.stream().filter(loan->loan.getLoadId()==id).map(
//                new UnaryOperator<Loan>(){
//                    @Override
//                    public Loan apply(Loan loan) {
//                        loan.setStatus(status);
//                        return loan;
//                    }
//                }
//        ).toList();


        //the lambda way
        return this.loans.stream().filter(loan->loan.getLoadId()==id).map(
                loan -> {
                    loan.setStatus(status);
                    return loan;
                }
        ).toList();

    }

    @Override
    public void deleteLoan(Loan loan) {

    }

    @Override
    public Iterable<Loan> findAll() {
        return this.loans;
    }

    @Override
    public Optional<Loan> findLoanById(int id) {
        return this.loans.stream().filter(loan->loan.getLoadId() == id).findFirst();
    }

    @Override
    public List<Loan> findLoanByStatus(String status) {
        return this.loans.stream().filter(loan->loan.getStatus().equals(status)).toList();
    }

    @Override
    public List<Loan> findLoanByType(String type) {
        return this.loans.stream().filter(loan->loan.getLoanType().equals(type)).toList();
    }

    @Override
    public List<Loan> findLoanByTenure(double tenture) {

        // local class
//        Predicate<Loan> myPredicate = new Predicate<>() {
//            @Override
//            public boolean test(Loan o) {
//                return o.getTenure() == tenture;
//            }
//
//        };
//        return this.loans.stream().filter(myPredicate).toList();


        // anonymous class
//        return this.loans.stream().filter(new Predicate<>() {
//            @Override
//            public boolean test(Loan o) {
//                return o.getTenure() == tenture;
//            }
//
//        }).toList();

        // lambda power
        return this.loans.stream().filter(loan -> loan.getTenure() == tenture).toList();


    }

    @Override
    public void deleteAll() {
        this.loans.clear();
    }

    @Override
    public double loanDeduction(){
        return this.loans.stream().mapToDouble(Loan::getLoadAmount).reduce(0, (l, current)-> l+current);
    }

    @Override
    public void maxLoanAmount(){
        System.out.println(this.loans.stream().max(Comparator.comparingDouble(Loan::getLoadAmount)));
    }
    @Override
    public void minLoanAmount(){
        System.out.println(this.loans.stream().min(Comparator.comparingDouble(Loan::getLoadAmount)));
    }

    @Override
    public void displayAllLoans() {
        //this.loans.stream().forEach(loan ->  System.out.println(loan));

        this.loans.stream().forEach(System.out::println);
    }

}
