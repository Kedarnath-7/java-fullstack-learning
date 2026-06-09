package day04.wrapperClasses.interfaceDemo;

public class Dog implements Animal {

    public void eat(){
        System.out.println("Dog is eating pedigree...");
    }
    public void talk() {
        System.out.println("Dog is barking at a stranger..");
    }
    public void shelter() {
        System.out.println("Dog is sleeping in it's home..");
    }
}
