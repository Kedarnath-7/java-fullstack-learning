package Polymorphism.demo;

public class Calculator {

    // int addition
    public int add(int a, int b){
        System.out.println("Int addition");
        return a + b;
    }

    //byte addition
    public byte add(byte a, byte b){
        System.out.println("Byte addition");
        return (byte)(a + b);
    }

    //String addition
    public String add(String a, String b) {
        System.out.println("String addition");
        return a + b;
    }

    // long addition
    public long add(long a, long b){
        System.out.println("Long addition");
        return a + b;
    }

    // float addition
    public float add(float a, float b) {
        System.out.println("Float addition");
        return a + b;
    }

    // double addition
    public double add(double a, double b) {
        System.out.println("Double addition");
        return a + b;
    }

    // short addition
    public short add(short a, short b) {
        System.out.println("Short addition");
        return (short) (a + b);
    }


}
