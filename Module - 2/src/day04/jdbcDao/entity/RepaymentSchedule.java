package day04.jdbcDao.entity;

public class RepaymentSchedule {
    private int scheduleId;
    private int loanId;
    private double emiAmount;
    private double principalAmount;
    private double interestAmount;
    private String status;

    public RepaymentSchedule() {

    }
    public RepaymentSchedule(int loanId, double emiAmount, double principalAmount, double interestAmount, String status) {
        this.loanId = loanId;
        this.emiAmount = emiAmount;
        this.principalAmount = principalAmount;
        this.interestAmount = interestAmount;
        this.status = status;
    }

    public RepaymentSchedule(int scheduleId, int loanId, double emiAmount, double principalAmount, double interestAmount, String status) {
        this.scheduleId = scheduleId;
        this.loanId = loanId;
        this.emiAmount = emiAmount;
        this.principalAmount = principalAmount;
        this.interestAmount = interestAmount;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getInterestAmount() {
        return interestAmount;
    }

    public void setInterestAmount(double interestAmount) {
        this.interestAmount = interestAmount;
    }

    public double getPrincipalAmount() {
        return principalAmount;
    }

    public void setPrincipalAmount(double principalAmount) {
        this.principalAmount = principalAmount;
    }

    public double getEmiAmount() {
        return emiAmount;
    }

    public void setEmiAmount(double emiAmount) {
        this.emiAmount = emiAmount;
    }

    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String toString() {
        return "{Schedule Id: " + this.scheduleId + ", Loan Id: " + this.loanId + ", emiAmount: " + this.emiAmount + ", Status: " + this.status + "}";
    }
}
