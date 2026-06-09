package day03.polymorphism.demo;

public class Employee extends Person{
    String dept;
    public Employee(String dept, String Fname, String Lname){
        super(Fname, Lname);
        this.dept=dept;
    }

}
