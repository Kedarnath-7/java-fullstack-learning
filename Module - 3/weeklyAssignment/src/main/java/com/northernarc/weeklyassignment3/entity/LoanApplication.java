package com.northernarc.weeklyassignment3.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class LoanApplication {
    @Id
    @GeneratedValue
    private String applicationId;
    private String customerName;
    private String lenderName;
    private String loanType;
    private double loanAmount;
    private int creditScore;


    public LoanApplication(){

    }

    public LoanApplication( String customerName, String lenderName, String loanType, double loanAmount, int creditScore) {
        this.customerName = customerName;
        this.lenderName = lenderName;
        this.loanType = loanType;
        this.loanAmount = loanAmount;
        this.creditScore = creditScore;
    }

    public LoanApplication(String applicationId, String customerName, String lenderName, String loanType, double loanAmount, int creditScore){
        this.applicationId = applicationId;
        this.customerName = customerName;
        this.lenderName = lenderName;
        this.loanType = loanType;
        this.loanAmount = loanAmount;
        this.creditScore = creditScore;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getLenderName() {
        return lenderName;
    }

    public void setLenderName(String lenderName) {
        this.lenderName = lenderName;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(int creditScore) {
        this.creditScore = creditScore;
    }
}