package day04.wrapperClasses.interfaceDemo;

public class Rectangle implements Shape {
    private double length;
    private double breadth;
    public Rectangle(double length, double breadth) {
        this.length = length;
        this.breadth = breadth;
    }

    public double getLength() {
        return this.length;
    }
    public void setLength(int length) {
        this.length = length;
    }
    public double getBreadth() {
        return this.breadth;
    }
    public void setBreadth(int breadth) {
        this.breadth = breadth;
    }

    @Override
    public void calculateArea(){
        System.out.println("Rectangle Area: " + this.length * this.breadth);
    }
}
