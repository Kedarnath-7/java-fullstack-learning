package day04.wrapperClasses.interfaceDemo;

public class Circle implements Shape {
    private double radius;
    public Circle(double radius) {
        this.radius = radius;
    }
    public double getRadius() {
        return this.radius;
    }
    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public void calculateArea() {
        System.out.println("Circle Area: " + Math.PI * radius * radius);
    }
}
