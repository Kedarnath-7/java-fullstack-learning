package wrapperClasses.interfaceDemo;

public class Main {
    public static void main(String[] args) {
        Circle circle = new Circle(5.5);
        circle.calculateArea();

        //upcasting of interface is also allowed
        // Shape circle = new Circle(7.7);

        Rectangle rectangle = new Rectangle(10.34d, 20.54d);
        rectangle.calculateArea();
        //rectangle.showColor(); static methods of interfaces can only be called with interfaces.
        Shape.showColor();

        //Shape rectangle = new Rectangle(4.0, 6.0);

        Triangle triangle = new Triangle(5.3, 7.7);
        triangle.calculateArea();

        //Shape triangle = new Triangle(9.3, 4.3);


        // Animal Interface
        Lion lion = new Lion();
        lion.talk();
        lion.shelter();
        lion.eat();

        Dog dog = new Dog();
        dog.talk();
        dog.shelter();
        dog.eat();


        //Payment Interface
        UPI upi = new UPI();
        upi.sendMoney();

        Credit credit = new Credit();
        credit.sendMoney();

        Debit debit = new Debit();
        debit.sendMoney();


        // Message interface
        Whatsapp whatsapp = new Whatsapp();
        whatsapp.sendMessage();

        Email email = new Email();
        email.sendMessage();

        Text  text = new Text();
        text.sendMessage();

    }
}
