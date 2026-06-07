package Polymorphism.strings;

public class Main {
    public static void main(String[] args) {
        String s1 = "Hello";
        String s2 = "Hello";

        System.out.println(s1 == s2);

        s2 = s2 + "World";
        System.out.println(s1 == s2);
        System.out.println("string 1: " + s1);
        System.out.println("string 2: " + s2);

        String s3 = "Kedar";
        String s4 = new String("Kedar");
        System.out.println(s3 == s4);
        System.out.println(s3 == s4.intern());
        System.out.println(s3.equals(s4));
    }
}
