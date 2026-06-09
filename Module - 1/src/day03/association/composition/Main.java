package day03.association.composition;

public class Main {
    public static void main(String[] args) {
        Car car = new Car(
                new Engine(900), new MusicSystem("Boat"), new AirConditioner(0.5)
        );
        System.out.println(car.toString());
    }
}
