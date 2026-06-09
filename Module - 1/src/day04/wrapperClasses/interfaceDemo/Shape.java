package day04.wrapperClasses.interfaceDemo;


interface Shape {
    void calculateArea();

    // static methods cannot be overriden
    String color = "Yellow";
    public static void showColor(){
        System.out.println("Color: " + color);
    }
}
