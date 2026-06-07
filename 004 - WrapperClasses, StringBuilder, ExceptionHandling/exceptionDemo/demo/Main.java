package exceptionDemo.demo;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Enter number a: ");
            int n = sc.nextInt();
            System.out.println("Enter number b: ");
            int m = sc.nextInt();
            double res = n / m;
            System.out.println("The result is: " + res);
            String str = null;
            str.length();
        }catch(ArithmeticException e){
            System.out.println("Division by zero is not allowed..");
        }catch(InputMismatchException e){
            System.out.println("Input mismatch..Please enter valid number");
        } catch (Exception e) {
            System.out.println("Something went wrong..." + e);
        }finally{ //finally block gets executed whether exception or not
            System.out.println("This is the finally block..");
            sc.close();
        }

        System.out.println("Hello, Kedarnath...");
    }
}
