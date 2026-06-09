package day01.functional;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Greeting gm = new Greeting(){
            @Override
            public void greet() {
                System.out.println("Good Morning buddy...");
            }
        };
        gm.greet();

        Greeting gd = new Greeting(){
            @Override
            public void greet() {
                System.out.println("Good Night Pal...");
            }
        };
        gd.greet();

        System.out.println("Welcome to greeting system....");
        System.out.println("Please enter the current hour in 24-H format: ");
        Scanner sc = new Scanner(System.in);
        int hour =  sc.nextInt();
        sc.nextLine();
        if(hour >= 0 && hour <=6){
            Greeting gm2 = new Greeting(){
                @Override
                public void greet() {
                    System.out.println("Good Morning buddy...");
                }
            };
        }else if(hour >= 12 && hour <=16){
            Greeting ga = new Greeting(){
                @Override
                public void greet() {
                    System.out.println("Good Afternoon Pal...");
                }
            };
        }else if(hour >= 16 && hour <=21) {
            Greeting ge = new Greeting(){
                @Override
                public void greet() {
                    System.out.println("Good Evening buddy...");
                }
            };
        }else if(hour >= 21 && hour <=24){
            Greeting gn =  new Greeting(){
                @Override
                public void greet() {
                    System.out.println("Good night pal...");
                }
            };
        }else{
            System.out.println("Invalid hour....");
        }



        // lambda expression
        // from java 8, the team decided that since the functional interface will have
        // only one abstract method and the variable is created implements interface\
        // we no need to write all of new InterfaceName(){}, instead we can write ()->{}

        Greeting gm3 = ()->{
            System.out.println("Good Morning buddy...");
        };
        sc.close();
    }
}
