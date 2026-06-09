package day02.inheritance;

class Manager extends Employee {

    private String team;

    public Manager(String Fname, String Lname, int age, String empId, String department, String team) {
        super(Fname, Lname, age, empId, department);
        this.team = team;
    }

    public String getTeam() {
        return this.team;
    }

    public void setTeam(String team){
        this.team = team;
    }

    public void manage(){
        System.out.println(this.Fname + " is managing in the team...");
    }
}