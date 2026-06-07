package Inheritance;

class Employee extends Person{
    private String empId;
    private String department;

    public Employee(String Fname, String Lname, int age, String empId, String department) {
        super(Fname, Lname, age);
        this.empId = empId;
        this.department = department;
    }

    public String getEmpId(){
        return this.empId;
    }

    public String getDepartment(){
        return this.department;
    }

    public void getDetails(){
        super.getDetails();
        System.out.println("Employee ID: " + this.empId + " Department: " + this.department);
    }
}