package weeklyTestPractice.Encapsulation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

// public class Branch implements Comparator<Employee>{
public class Branch {
    private String branchId;
    private String branchName;

    private ArrayList<Employee> employeeList;

    public Branch(String branchId, String branchName){
        this.branchId = branchId;
        this.branchName = branchName;
        this.employeeList = new ArrayList<>();
    }

    public void displayBrancDetails(){
        System.out.println("Branch: " + this.branchName);
        System.out.println("Employee: " + this.employeeList);
    }

    public double calculateTotalSalaryExpense(){
        double totalSalary = 0;
        for(Employee e : this.employeeList){
            totalSalary += e.getSalary();
        }
        return totalSalary;
    }

    public void findHighestPaidEmployee(){

    }

    public void addEmployee(Employee e){
        this.employeeList.add(e);
    }

//    @Override
//    public int compare(Employee o1, Employee o2) {
//        return Comparator.comparingDouble(o1.getSalary(), o2.getSalary());
//    }
}
