package Polymorphism.ObjectOperations;

public class Person {
    protected String Fname;
    protected String Lname;
    protected int age;

    public Person(String Fname, String Lname, int age) {
        this.Fname = Fname;
        this.Lname = Lname;
        this.age = age;
    }
    public String getFname() {
        return Fname;
    }
    public void setFname(String Fname) {
        this.Fname = Fname;
    }
    public String getLname() {
        return Lname;
    }
    public void setLname(String Lname) {
        this.Lname = Lname;
    }

    @Override
    public String toString() {
        return "Person{" + "Fname=" + Fname + ", Lname=" + Lname + '}';
    }

    @Override
    public boolean equals(Object o) {
        Person p1 = (Person) o;
        return this.Fname.equalsIgnoreCase(p1.getFname()) && this.Lname.equalsIgnoreCase(p1.getLname());
    }

    @Override
    public int hashCode() {
        return Fname.hashCode() + Lname.hashCode();
    }
}
