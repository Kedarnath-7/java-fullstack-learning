package weeklyAssignments.association;

public class LoanOfficer {
    private String officerName;

    public LoanOfficer(String officerName) {
        this.officerName = officerName;
    }
    public String getOfficerName() {
        return officerName;
    }
    public void setOfficerName(String officerName) {
        this.officerName = officerName;
    }
    @Override
    public String toString() {
        return "LoanOfficer{" + "officerName=" + officerName + '}';
    }
    public void reviewLoanApplication(Customer customer){
        System.out.println(officerName + " is reviewing loan application of " + customer.getCustomerName());
    }
}
