package day05.arrayDemo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
public class PersonMain {
    public static void main(String[] args) {
        System.out.println("Welcome to sorting of person objects....");
        System.out.println("Enter the size of the array: ");
        Scanner sc = new Scanner(System.in);
        int arrSize = sc.nextInt();
        sc.nextLine();
        Person[] personArr = new Person[arrSize];
        System.out.println("Enter " + arrSize + " people first name and last name... ");
        for(int i = 0; i < arrSize; i++){
            System.out.println("Enter first name of " + (int)(i+1) + " person: ");
            String firstName = sc.nextLine();
            System.out.println("Enter last name of " + (int)(i+1) + " person: ");
            String lastName = sc.nextLine();
            System.out.println("Enter age of " + (int)(i+1) + " person: ");
            int age = sc.nextInt();
            sc.nextLine();
            personArr[i] = new Person(firstName, lastName, age);
        }

        System.out.println("Person's array before sorting....");
        for(Person person : personArr){
            System.out.println(person);
        }

        boolean flag = true;
        while(flag){
            System.out.println("Enter how would like to sort: 1. Age Asc\n2. Age Dsc\n3. First Name Asc\n4. First Name Dsc\n5. Last Name Asc\n6. Last Name Dsc\n7. Exit");
            int choice = sc.nextInt();
            sc.nextLine();
            System.out.println("Person's array after sorting....");
            switch(choice){
                case 1:
                    Arrays.sort(personArr, new Comparator<Person>() {
                        @Override
                        public int compare(Person o1, Person o2) {
                            return o1.getAge() - o2.getAge();
                        }
                    }); //** inner anonymous class
                    //Arrays.sort(personArr, new AgeAsComparatorAsc());
                    break;
                case 2:
                    Arrays.sort(personArr, new Comparator<Person>() {
                        @Override
                        public int compare(Person o1, Person o2) {
                            return o2.getAge() - o1.getAge();
                        }
                    }); // inner anonymous class
                    //Arrays.sort(personArr, new AgeAsComparatorDsc());
                    break;
                case 3:
                    Arrays.sort(personArr, new  Comparator<Person>() {
                        @Override
                        public int compare(Person o1, Person o2) {
                            return o1.getFname().compareTo(o2.getFname());
                        }
                    });
                    //Arrays.sort(personArr, new FnameAsComparatorAsc());
                    break;
                case 4:
                    Arrays.sort(personArr, new  Comparator<Person>() {
                        @Override
                        public int compare(Person o1, Person o2) {
                            return o2.getFname().compareTo(o1.getFname());
                        }
                    });
                    //Arrays.sort(personArr, new FnameAsComparatorDsc());
                    break;
                case 5:
                    Arrays.sort(personArr, new  Comparator<Person>() {
                        @Override
                        public int compare(Person o1, Person o2) {
                            return o1.getLname().compareTo(o2.getLname());
                        }
                    });
                    // Arrays.sort(personArr, new LnameAsComparatorAsc());
                    break;
                case 6:
                    Arrays.sort(personArr, new  Comparator<Person>() {
                        @Override
                        public int compare(Person o1, Person o2) {
                            return o2.getLname().compareTo(o1.getLname());
                        }
                    });
                    //Arrays.sort(personArr, new LnameAsComparatorDsc());
                    break;
                case 7:
                    flag = false;
                    break;
                default:
                    System.out.println("Invalid choice...try again...");
                    break;

            }
            System.out.println(Arrays.toString(personArr));
        }


//        Arrays.sort(personArr);
//        for(Person person : personArr){
//            System.out.println(person);
//        }

       // System.out.println(Arrays.toString(personArr));

        sc.close();
    }
}
