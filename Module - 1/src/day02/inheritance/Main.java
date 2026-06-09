package day02.inheritance;
import java.util.Scanner;
public class Main {
    public static void main(String[] args){
        Person person1 = new Person("Kedar", "Nagaradone", 22);

        person1.getDetails();
        person1.eat();
        person1.walk();
        person1.talk();

        Student stud1 = new Student("Kedar", "Nagaradone", 22, "SRMIST", "A", "B.Tech AI");
        stud1.setGrade("A");
        stud1.setSchool("SRMIST");

        stud1.getDetails();


        // upcasting
        // creating a object variable of child class and assigning it to parent class reference variable
        // but we cannot access the child class specific methods using parent class reference variable
        // we can only access the parent class methods using parent class reference variable

        Person person2 = new Student("Tony", "Stark", 48, "SRMIST", "A", "AI");
        // person2.setFname("Tony");
        // person2.setLname("Stark");
        // person2.setAge(48);

    
        // downcasting
        // to access the child class specific methods using parent class reference variable 
        // we need to downcast the parent class reference variable to child class reference variable
        // but we need to be careful while downcasting as it can lead to ClassCastException 
        // if the parent class reference variable is not actually referring to an object of the child class

        ((Student) person2).setSchool("SRMIST");
        ((Student)person2).setCourse("AI");
        ((Student) person2).setGrade("A");

        // if overriding is there in child class then the child class method will be called 
        // when we call the method using parent class reference variable
        person2.getDetails();


        // Upcasting and downcasting another example
        Animal lion = new Lion();
        ((Lion) lion).eat();
        lion.talk();

        Animal dog = new Dog();
        ((Dog) dog).eat();
        dog.talk();

        Animal deer = new Deer();
        ((Deer) deer).eat();
        deer.talk();

        Scanner sc = new Scanner(System.in);
        //Animal newAnimal;
        boolean flag = true;
        while(flag){
            System.out.println("Enter which animal you want: \n1. Lion\n2. Dog\n3. Deer\n4. Exit: ");
            
            int choice = sc.nextInt();
            Animal newAnimal;
            switch(choice) {
                case 1:
                    newAnimal = new Lion();;
                    break;
                case 2:
                    newAnimal = new Dog();
                    break;
                case 3:
                    newAnimal = new Deer();
                    break;
                case 4:
                    flag = false;
                default:
                    newAnimal = new Animal();
                    //return;
            }
            newAnimal.eat();
            newAnimal.talk();
            newAnimal.walk();
        }

        System.out.println("Would you like to send a message? 1. Yes\n2. No");
        int choice2 = sc.nextInt();
        if(choice2 == 2){
            sc.close();
            return;
        }
        System.out.println("Please enter the message you want to send: ");
        sc.nextLine();
        String message = sc.nextLine();
        System.out.println("Please select the message provider through which to send the message: 1. Whatsapp\n2. Email\n3. Text\n4. Exit");
        int choice3 = sc.nextInt();
        SimpleMessage messageObj;
        switch (choice3) {
            case 1:
                messageObj = new Whatsapp();
                
                break;
            case 2:
                messageObj = new Email();
                break;
            case 3:
                messageObj = new TextMessage();
                break;

            default:
                System.out.println("Invalid choice");
                sc.close();
                return;
        }
        messageObj.setMessage(message);
        messageObj.send();

        

        System.out.println("Would you like to send some money? 1. Yes\n2. No");
        int sendChoice = sc.nextInt();
        if(sendChoice != 1){
            sc.close();
            return;
        }
        System.out.println("Please enter the amount you wantto send: ");
        double amount = sc.nextDouble();
        System.out.println("Please select how you want to send: 1.Upi\n2. Debit\n3. Credit\nDefault Cash");
        int paymentChoice = sc.nextInt();
        Payment newPayment;
        switch(paymentChoice) {
            case 1:
                newPayment = new Upi(amount);
                break;
            case 2:
                newPayment = new Debit(amount);
                break;
            case 3:
                newPayment = new Credit(amount);
                break;
            default:
                newPayment = new Payment(amount);
                break;
        }
        newPayment.pay();

        sc.close();

    }    
}
