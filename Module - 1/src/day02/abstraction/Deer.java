package day02.abstraction;

class Deer implements Animal{
    public void eat(){
        System.out.println("Deer is eating grass...");
    }
    public void talk(){
        System.out.println("Deer is talking...");
    }
    public void walk(){
        System.out.println("Deer is walking...");
    }
    public void shelter(){
        System.out.println("Deer is sheltering in the forest...");
    }
}