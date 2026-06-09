package day04.demo;
import java.util.Scanner;

public class ManuallyThrow {
    public static void main(String[] args) {
        System.out.println("Welcome to the Avengers Tower...");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your name to check your invitation: ");
        String name = sc.nextLine();
        try{
            if(!name.equals("Kedarnath") && !name.equals("Spider Man") && !name.equals("Tony Stark")){
                throw new NotInvited("Invalid Name..");
            }
            System.out.println("Hello, Kedarnath...");
        }catch(NotInvited e){
            System.out.println("Something went wrong..." + e);
        }
    }
}
