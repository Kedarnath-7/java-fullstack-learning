package day03.polymorphism.demo;

public class Main2 {
    public static void main(String[] args) {

        // the display method is private in parent
        // and there is other display method in the child
        // and now by upcasting, the method is not accessible
        // because the display method is private and cannot be accessed outside the class

        // Parent parent = new Child();
        // parent.display();

        Greeting g =  new Greeting();
        Person p = new Person("John", "Doe");
        Student s = new Student("Jane", "Doe");
        g.greet(p);

        g.greet(null); // ambigious method call as the compiler is confused
        // which child to call between Student or Employee
        // but if they are no child classes,then automatically the parent class is called

    }
}
