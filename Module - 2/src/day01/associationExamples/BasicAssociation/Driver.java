package day01.associationExamples.BasicAssociation;

public class Driver {
    private String name;

    public Driver(String name) {
        this.name = name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    public void drive(Car car){
        System.out.println("Driver " + this.name + " is driving the " + car.getModel() + " car...");
    }
}
