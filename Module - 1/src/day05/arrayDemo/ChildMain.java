package day05.arrayDemo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
public class ChildMain {
    public static void main(String[] args) {
        System.out.println("Welcome to sorting by DOB program....");
        System.out.println("Enter the size of the array: ");
        Scanner sc= new Scanner(System.in);
        int arrSize = sc.nextInt();
        sc.nextLine();

        Child[] childArr = new  Child[arrSize];
        System.out.println("Enter the " + arrSize + " details as prompted....");
        for (int i = 0; i < arrSize; i++) {
            System.out.println("Enter the details of " + (int)(i+1) + " element as prompted...");
            System.out.println("Enter First name: ");
            String Fname = sc.nextLine();
            System.out.println("Enter Last name: ");
            String Lname = sc.nextLine();
            System.out.println("Enter DOB: ");
            String DOB = sc.nextLine();
            childArr[i] = new Child(Fname, Lname, DOB);
            setDOB(childArr[i]);
        }

        boolean flag = true;
        while(flag){
            System.out.println("Choose how you would like to sort:\n1. DOB Asc\n 2. DOB Desc\n3. First Name Asc\n4. First Name Dsc\n5. Last Name Asc\n6. Last Name Dsc\n7. Exit");
            int choice = sc.nextInt();
            sc.nextLine();
            System.out.println("Array after sorting....");
            switch (choice){
                case 1:
                    Arrays.sort(childArr, new Comparator<Child>() {
                        @Override
                        public int compare(Child o1, Child o2) {
                            if(o1.getYear() != o2.getYear()){
                                return o1.getYear() - o2.getYear();
                            }
                            if(o1.getMonth() != o2.getMonth()){
                                return o1.getMonth() - o2.getMonth();
                            }
                            return o1.getDay() -  o2.getDay();
                        }
                    });
                    break;
                case 2:
                    Arrays.sort(childArr, new Comparator<Child>() {
                        @Override
                        public int compare(Child o1, Child o2) {
                            if(o1.getYear() != o2.getYear()){
                                return o2.getYear() - o1.getYear();
                            }
                            if(o1.getMonth() != o2.getMonth()){
                                return o2.getMonth() - o1.getMonth();
                            }
                            return o2.getDay() -  o1.getDay();
                        }
                    });
                    break;
                case 3:
                    Arrays.sort(childArr, new Comparator<Child>() {
                        @Override
                        public int compare(Child o1, Child o2) {
                            return o1.getFname().compareTo(o2.getFname());
                        }
                    });
                    break;
                case 4:
                    Arrays.sort(childArr, new Comparator<Child>() {
                        @Override
                        public int compare(Child o1, Child o2) {
                            return o2.getFname().compareTo(o1.getFname());
                        }
                    });
                    break;
                case 5:
                    Arrays.sort(childArr, new Comparator<Child>() {
                        @Override
                        public int compare(Child o1, Child o2) {
                            return o1.getLname().compareTo(o2.getLname());
                        }
                    });
                    break;
                case 6:
                    Arrays.sort(childArr, new Comparator<Child>() {
                        @Override
                        public int compare(Child o1, Child o2) {
                            return o2.getLname().compareTo(o1.getLname());
                        }
                    });
                    break;
                case 7:
                    System.out.println("Thanks for using our program...exiting...");
                    flag = false;
                    break;
                default:
                    System.out.println("Invalid choice... try again");
                    break;
            }
            System.out.println(Arrays.toString(childArr));
        }

        sc.close();


    }

    public static void setDOB(Child child){
        String[] parts = child.getDOB().split("-");
        int day =  Integer.parseInt(parts[0]);
        int month =  Integer.parseInt(parts[1]);
        int year =  Integer.parseInt(parts[2]);
        child.setDay(day);
        child.setMonth(month);
        child.setYear(year);
    }
}
