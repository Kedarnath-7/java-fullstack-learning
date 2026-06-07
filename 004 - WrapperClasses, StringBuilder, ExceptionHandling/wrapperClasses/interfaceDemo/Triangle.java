package wrapperClasses.interfaceDemo;

public class Triangle implements Shape{
    private double base;
    private double height;

    public Triangle(double base, double height){
        this.base = base;
        this.height = height;
    }

    public void setBase(double base){
        this.base = base;
    }
    public void setHeight(double height){
        this.height = height;
    }
    public double getBase(){
        return this.base;
    }
    public double getHeight(){
        return this.height;
    }

    @Override
    public void calculateArea(){
        System.out.println("Triangle area: " + 0.5 * this.base * this.height);
    }
}
