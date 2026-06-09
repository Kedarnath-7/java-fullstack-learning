package weeklyAssignments.encapsulation;

public class Branch {
    private String branchName;
    private Customer[] customers;

    public Branch(String branchName, Customer[] customers) {
        this.branchName = branchName;
        this.customers = customers;

    }
    public String getBranchName() {
        return branchName;
    }
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
    public Customer[] getCustomers() {
        return customers;
    }
    public void setCustomers(Customer[] customers) {
        this.customers = customers;
    }

    public void showCustomers() {
        System.out.println("Customers in branch " + this.branchName + ":");
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }
}
