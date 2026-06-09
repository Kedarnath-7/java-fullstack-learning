package day04.demo;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        System.out.println("Welcome to the Grand Hotel....");
        System.out.println("Please enter your credentials to proceed....");
        System.out.println("Enter your username: ");
        Scanner sc = new Scanner(System.in);
        String username = sc.nextLine();
        System.out.println("Enter your password: ");
        String password = sc.nextLine();

        try{
            if(!username.equals("Tony Stark") && !password.equals("iamironman")){
                throw new InvalidCredientialsException("not on the invited list..");
            }
            System.out.println("Welcome to the party, " +  username);
        }catch(InvalidCredientialsException e){
            System.out.println("Sorry, your name is " + e.getMessage());
        }

    }
}
