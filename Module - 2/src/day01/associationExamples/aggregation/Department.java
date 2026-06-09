package day01.associationExamples.aggregation;

import java.util.ArrayList;
import java.util.List;

public class Department {
    private String name;
    private String hqLocation;
    private List<Employee> employees;
    public Department(String name, String hqLocation) {
        this.name = name;
        this.hqLocation = hqLocation;
        employees = new ArrayList<>();
    }

    public String getName() {
        return name;
    }
    public String getHQLocation() {
        return hqLocation;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setHQLocation(String hqLocation) {
        this.hqLocation = hqLocation;
    }
    public void addEmployee(Employee employee) {
        this.employees.add(employee);
    }
    public List<Employee> getEmployees() {
        return this.employees;
    }

    public void removeEmployee(Employee employee) {
        for(Employee e: this.employees) {
            if (e.getName().equals(employee.getName())) {
                System.out.println("Removing employee " + employee.getName());
                this.employees.remove(e);
                return;
            }
        }
        System.out.println("Employee " + employee.getName() + " not found");
    }

}
