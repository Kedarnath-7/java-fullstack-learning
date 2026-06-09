package day01.associationExamples.BasicAssociation;

public class Main {
    public static void main(String[] args) {

        // basic association is has-a type relationship
        // like driver using a car

        // driver doesn't have car as a variable, it is just passed as parameter
        // so it is just using car, hence the basic association

        Car car = new Car("Mercedes");
        Driver driver = new Driver("Lewis Hamilton");
        driver.drive(car);

        Car car2 = new Car("Ferrari");
        driver.drive(car2);
    }
}
