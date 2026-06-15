package weeklyTestPractice.Encapsulation;

import java.util.Comparator;

public class Employee {
    private String employeeId;
    private String employeeName;
    private double salary;

    public Employee(String employeeId, String employeeName, double salary){
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        if(salary >= 0){
            this.salary = salary;
        }

    }

    public double getSalary(){
        return this.salary;
    }
    public String getEmployeeId(){
        return this.employeeId;
    }
    public String getEmployeeName(){
        return this.employeeName = employeeName;
    }

    public void increaseSalary(double percentage){
        this.salary += this.salary*percentage;
    }

    @Override
    public String toString(){
        return this.getEmployeeName() + " " + this.getSalary();
    }
}
