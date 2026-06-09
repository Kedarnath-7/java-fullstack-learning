package day03.polymorphism.ObjectOperations;

public class Student extends Person{
    protected String school;
    protected String course;
    public Student(String Fname, String Lname, int age, String school, String course) {
        super(Fname, Lname, age);
        this.school = school;
        this.course = course;
    }

    @Override
    public String toString() {
        return "Student Name " + Fname + " " + Lname + " " + age + " " + school + " " + course;
    }

    @Override
    public boolean equals(Object o) {
        Student p1 = (Student) o;
        return this.Fname.equalsIgnoreCase(p1.getFname()) && this.Lname.equalsIgnoreCase(p1.getLname());
    }

    @Override
    public int hashCode() {
        return Fname.hashCode() + Lname.hashCode();
    }

}
