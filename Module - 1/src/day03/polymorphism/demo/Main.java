package day03.polymorphism.demo;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        // byte addition
        byte byteA = 10;
        byte byteB = 20;
        System.out.println(calculator.add(byteA, byteB));

        // short addition
        short shortA = 10;
        short shortB = 20;
        System.out.println(calculator.add(shortA, shortB));

        //int addition
        System.out.println(calculator.add(10, 20));

        // long addition
        System.out.println(calculator.add(10L, 20L));

        // float addition
        System.out.println(calculator.add(10f, 20f));

        // double addition
        System.out.println(calculator.add(10d, 20d));

        // string addition
        System.out.println(calculator.add("10", "20"));

    }
}
