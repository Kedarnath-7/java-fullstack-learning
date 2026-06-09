package day03.polymorphism.demo;

public class Person {
    protected String Fname;
    protected String Lname;

    public Person(String Fname, String Lname){
        this.Fname = Lname;
        this.Lname = Lname;
    }

    public Person getDemo(){
        return new Person(Fname, Lname);
    }
}
