package day03.association.aggregation;

public class Main {
    public static void main(String[] args) {
        Address addr = new Address("Avengers Tower", "Avengers Street", "New york", "NY", "000001");
        Person person = new Person("Kedarnath", "Nagaradone", 21, addr);
        Person person2 = new Person("Tony", "Stark", 48, addr);
        System.out.println(person.toString());
    }
}
