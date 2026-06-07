package Inheritance;

public class Student extends Person {
    
    private String school;
    private String grade;
    private String course;

    public Student(String Fname, String Lname, int age, String school, String grade, String course){
        super(Fname, Lname, age);
        this.school = school;
        this.grade = grade;
        this.course = course;
    }

    public void setSchool(String school){
        this.school = school;
    }

    public void setGrade(String grade){
        this.grade = grade;
    }

    public void setCourse(String course){
        this.course = course;
    }

    public String getSchool(){
        return this.school;
    }

    public String getGrade(){
        return this.grade;
    }

    public String getCourse(){
        return this.course;
    }

    public void getDetails(){
        super.getDetails();
        System.out.println("Studying " + this.course + " at " + this.school + " with grade " + this.grade);
    }


}
