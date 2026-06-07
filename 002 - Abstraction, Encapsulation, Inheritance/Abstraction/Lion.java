package Abstraction;

class Lion implements Animal {
    public void eat() {
        System.out.println("Lion is eating it's food...");
    }
    public void talk() {
        System.out.println("Lion is roaring in the zoo at the visitors...");
    }
    public void walk() {
        System.out.println("Lion is running towards it's prey...");
    }
    public void shelter() {
        System.out.println("Lion is taking shelter in the zoo..");
    }
}