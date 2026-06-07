import java.util.Scanner;


class FlowControl {
    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int a = sc.nextInt();
        System.out.print("Enter another number: ");
        int b = sc.nextInt();
        greatestOfTwoNumbers(a, b);

        // largest of three numbers
        System.out.print("Enter three numbers to find greatest among of those: ");
        int x = sc.nextInt();
        int y = sc.nextInt();
        int z = sc.nextInt();
        largestOfThree(x, y, z);
        

        // positive or negative
        System.out.print("Enter a number to check if it's positive or negative: ");
        int num = sc.nextInt();
        postiveORNegative(num);

        // odd or even
        System.out.print("Enter number to check Even or ODD: ");
        int num2 = sc.nextInt();
        evenOrOdd(num2);

        // vote eligibility
        System.out.print("Enter your age to check vote eligibility: ");
        int age = sc.nextInt();
        voteElgibility(age);

        // leap year
        System.out.print("Enter a year: ");
        int year = sc.nextInt();
        leapYear(year);

        // student grade
        System.out.print("Enter marks to check grade: ");
        int marks = sc.nextInt();
        grader(marks);

        // june calendar day
        System.out.print("Enter a date in June to find the day of the week: ");
        int date = sc.nextInt();
        juneCalendarDay(date);
    
        sc.close();

    }

    private static void greatestOfTwoNumbers(int a, int b){
        if (a > b) {
            System.out.println("a is greater than b");
        } else if (b > a) {
            System.out.println("b is greater than a");
        } else {
            System.out.println("a and b are equal");
        }
    }

    private static void largestOfThree(int x, int y, int z) {
        if (x > y && x > z) {
            System.out.println("x is the greatest number");
        } else if (y > x && y > z) {
            System.out.println("y is the greatest number");
        } else if (z > x && z > y) {
            System.out.println("z is the greatest number");
        } else {
            System.out.println("All numbers are equal");
        }
    }

    private static void postiveORNegative(int num) {
        if (num > 0) {
            System.out.println("The number is positive");
        } else if(num < 0) {
            System.out.println("The number is negative");
        } else {
            System.out.println("Smart boy uh!, Zero is actually either postive or negative.");
        }
    }

    private static void evenOrOdd(int num) {
        if (num % 2 == 0) {
            System.out.println("The number is even");
        } else {
            System.out.println("The number is odd");
        }
    }

    private static void voteElgibility(int age) {
        if (age >= 18) {
            System.out.println("You are eligible to vote, go ahead and enjoy buddy!");
        } else {
            System.out.println("Oops! buddy you have to wait for some time.");
        }
    }

    private static void leapYear(int year) {
        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
            System.out.println(year + " is a leap year");
        } else {
            System.out.println(year + " is not a leap year");
        }
    }

    private static void grader(int marks) {
        if (marks >= 90) {
            System.out.println("Grade: O");
        } else if (marks >= 80) {
            System.out.println("Grade: A");
        } else if (marks >= 70) {
            System.out.println("Grade: B");
        } else if (marks >= 60) {
            System.out.println("Grade: C");
        }else if(marks >= 50) {
            System.out.println("Grade: D");
        } else if(marks < 50) {
            System.out.println("Grade: F");
        } else {
            System.out.println("Invalid marks");
        }
    }

    private static void juneCalendarDay(int day) {
        if(day < 1 || day > 30) {
            System.out.println("Invalid date");
            return;
        }
        switch(day % 7) {
            case 0:
                System.out.println("The day of the week is Sunday");
                break;
            case 1:
                System.out.println("The day of the week is Monday");
                break;
            case 2:
                System.out.println("The day of the week is Tuesday");
                break;
            case 3:
                System.out.println("The day of the week is Wednesday");
                break;
            case 4:
                System.out.println("The day of the week is Thursday");
                break;
            case 5:
                System.out.println("The day of the week is Friday");
                break;
            case 6:
                System.out.println("The day of the week is Saturday");
                break;
            default:
                System.out.println("Invalid date");
        }
    }

}