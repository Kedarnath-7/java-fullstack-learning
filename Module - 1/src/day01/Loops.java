package day01;

import java.util.Scanner;

class Loops{
    public static void main(String[] args) {

        System.out.println("For loop");
        for(int i = 1; i <= 5; i++) {
            System.out.println(i);
        }

        System.out.println("While loop");
        int w = 1; 
        while(w <= 5) {
            System.out.println(w);
            w++;
        }

        System.out.println("Do-while loop");
        int k = 1;  
        do {
            System.out.println(k);
            k++;
        } while(k <= 5);


        // print even numbers from given range(user input)
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the starting number: ");
        int start = sc.nextInt();
        System.out.print("Enter the ending number: ");
        int end = sc.nextInt();

        evenNumbers(start, end);

        // print odd numbers from given range(user input)
        System.out.println("Odd numbers from " + start + " to " + end + ":");
        oddNumbers(start, end);


        // print multiplication table of a number(user input)
        System.out.print("Enter a number to find its multiplication table: ");
        int numForTable = sc.nextInt();
        multiplicationTable(numForTable);

        // print factors of a number(user input)
        System.out.println("Enter the number to find the factors for:");
        int numForFactors = sc.nextInt();
        factors(numForFactors);

        // count the factors of a number
        System.out.println("Enter the number to count the factors: ");
        int numCountFactors = sc.nextInt();
        countFactors(numCountFactors);

        // sum of factors of a number
        System.out.println("Enter the number to find sum of its factors: ");
        int numSumFactors = sc.nextInt();
        sumOfFactors(numSumFactors);

        /*  
        
        * * *
        * *
        * 
        
        */


        System.out.println("***\n**\n*");
        System.out.println("Enter number of lines for the pattern like above: ");
        int numLines = sc.nextInt();
        for(int i = numLines; i > 0; i--){
            for(int j = 0; j < i; j++){
                System.out.print("*");
            }
            System.out.println("");
        }

        
        
        /* 

            *
           * *
          * * *
    
        */
        System.out.println("  *\n * *\n* * *");
        System.out.println("Enter number of lines for the pattern like above: ");
        int numLines1 = sc.nextInt();
        for(int i = 0; i < numLines1; i++){
            for(int j = numLines1 - i - 1; j > 0; j--){
                System.out.print(" ");
            }
            for(int m = 0; m < 2 * i + 1; m++){
                System.out.print("*");
            }
            System.out.println("");
        }


        /* 
        
        *****
        *   *
        *   *
        *   *
        *****
        
        */  
        System.out.println("*****\n*   *\n*   *\n*   *\n*****");
        System.out.println("Enter the size of the square pattern like above: ");
        int size = sc.nextInt();
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(i == 0 || i == size - 1 || j == 0 || j == size - 1){
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println("");
        }


        // factorial of a number (user input)
        System.out.print("Enter a number to find its factorial: ");
        int num = sc.nextInt();
        factorial(num);

        // fibonacci series up to n terms (user input)
        System.out.print("Enter the number of terms for Fibonacci series: ");
        int n = sc.nextInt();
        fibonacci(n);

        // prime or not (user input)
        System.out.print("Enter a number to check if it's prime or not: ");
        int numPrime = sc.nextInt();
        primeOrNot(numPrime);
        
        sc.close();
    }

    private static void evenNumbers(int start, int end){
        System.out.println("Even numbers from " + start + " to " + end + ":");
        for(int i = start; i <= end; i++) {
            if(i % 2 == 0) {
                System.out.println(i);
            }
        }
    }

    private static void oddNumbers(int start, int end){
        System.out.println("Odd numbers from " + start + " to " + end + ":");
        for(int i = start; i <= end; i++){
            if(i % 2 != 0){
                System.out.println(i);
            }
        }
    }

    private static void multiplicationTable(int num) {
        System.out.println("Multiplication Table of " + num + ":");
        for(int i = 1; i <= 10; i++){
            System.out.println(num + " x " + i + " = " + num * i);
        }
    }

    private static void factors(int num){
        System.out.println("Factor of the number " + num + ":");
        for(int i = 1; i <= num; i++){
            if(num % i == 0){
                System.out.println(i);
            }
        }
    }

    private static void countFactors(int num) {
        int count = 0;
        for(int i = 1; i <= num; i++){
            if(num % i == 0){
                count += 1;
            }
        }
        System.out.println("Number of factors of " + num + " is: " + count);
    }

    private static void sumOfFactors(int num) {
        int sum = 0;
        for(int i = 1; i <= num; i++){
            if(num % i == 0){
                sum += i;
            }
        }
        System.out.println("Sum of factors of the number is: " + sum);
    }

    private static void factorial(int num) {
        int fact = 1;
        for(int i = 1; i <= num; i++) {
            fact *= i;
        }
        System.out.println("Factorial of " + num + " is: " + fact);
    }

    private static void fibonacci(int n) {
        int a = 0, b = 1;
        System.out.println("Fibonacci series up to " + n + " terms:");
        for(int i = 1; i <= n; i++){
            System.out.print(a + " ");
            int next = a + b;
            a = b;
            b = next;
        }
        System.out.println("");
    }

    // prime or not
    private static void primeOrNot(int num) {
        if(num <= 1){
            System.out.println(num + " is not a prime number.");
            return;
        }
        for(int i = 2; i <= Math.sqrt(num); i++){
            if(num % i == 0){
                System.out.println(num + " is not a prime number.");
                return;
            }
        }
        System.out.println(num + " is a prime number.");
    }



}