package day03.polymorphism.ObjectOperations;


public class Main {
    public static void main(String[] args) {
        Object obj1 = new Object();
        Object obj2 = new Object();

        Object obj3 = obj1;

        System.out.println(obj1 == obj2);
        System.out.println(obj1 == obj3);
        System.out.println(obj1.equals(obj2));
        System.out.println(obj1.equals(obj3));
        System.out.println(obj1.hashCode());
        System.out.println(obj2.hashCode());
        System.out.println(obj3.hashCode());
        System.out.println(obj1.hashCode() == obj2.hashCode());
        System.out.println(obj1.hashCode() == obj3.hashCode());
        System.out.println(obj1.getClass().getSimpleName());
        System.out.println(obj1.toString());
        System.out.println(obj1);


        String str1 = new String("H");
        System.out.println(str1.hashCode());
        System.out.println(str1.getClass().getSimpleName());


        Person p1 = new Person("Kedarnath", "Nagaradone", 21);
        Person p2 = new Person("Kedarnath", "Nagaradone", 48);
        System.out.println(p1 == p2);
        System.out.println(p1.equals(p2));
        System.out.println(p1.hashCode());
        System.out.println(p2.hashCode());
        System.out.println(p1.getClass().getSimpleName());


        // overriding Object methods String toString, boolean equals(), int hashCode()
        System.out.println(p1.toString());
        System.out.println(p1.equals(p2));
        System.out.println(p1.hashCode() == p2.hashCode());

        Student stud1 = new Student("Peter", "Parker", 17, "Queens", "Science");
        Student stud2 = new Student("Harry", "Potter", 15, "Hogwarts", "Magic");
        System.out.println(stud1.equals(stud2));
        System.out.println(stud1.hashCode() == stud2.hashCode());
        System.out.println(stud1.toString());
        System.out.println(stud2.toString());


    }
}
