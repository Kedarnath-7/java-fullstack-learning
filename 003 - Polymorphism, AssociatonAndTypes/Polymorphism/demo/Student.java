package Polymorphism.demo;

public class Student extends Person{
    public Student(String Fname, String Lname){
        super(Fname, Lname);
    }

    /*
    =======
    Law of covariant - the overridden method can have return type of
    // either same as parent class or even the child class
    =======
    */

    //    public Person getDemo(){
    //        return new Person("Kedar", "N");
    //    }
    @Override
    public Student getDemo(){
        return new Student("Kedar", "N");
    }



}
