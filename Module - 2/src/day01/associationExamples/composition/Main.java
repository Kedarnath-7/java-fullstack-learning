package day01.associationExamples.composition;

public class Main {
    public static void main(String[] args) {
        Car car = new Car("Rolls Royce", 790);
        car.startCar();
        car.stopCar();

        Car car2 = new Car("Mustang", 900);
        car2.startCar();
        car2.stopCar();

    }
}
