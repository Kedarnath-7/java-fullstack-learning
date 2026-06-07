package Inheritance;

public class Main2{
    public static void main(String[] args){

        Person manager = new Manager("Kedarnath", "Nagaradone", 22, "A123", "Technology", "Team - 7");
        manager.getDetails();
        manager.eat();
        manager.talk();
        manager.walk();
        manager.getDetails();
        
    }
}