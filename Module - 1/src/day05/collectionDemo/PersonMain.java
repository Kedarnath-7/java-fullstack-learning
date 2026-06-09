package day05.collectionDemo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class PersonMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of people: ");
        int numPeople = sc.nextInt();
        sc.nextLine();

        List<Person> personList = new ArrayList<Person>();

        //List<Person> personList = new LinkedList<Person>();
        //List<Person> personList = new Vector<Person>();
        //List<Person> personList = new Stack<Person>();

        // Queue<Person> personQueue = new PriorityQueue();

        for(int i = 0; i < numPeople; i++){
            System.out.println("Enter First name: ");
            String Fname = sc.nextLine();
            System.out.println("Enter Last name: ");
            String Lname = sc.nextLine();
            System.out.println("Enter age: ");
            int age = sc.nextInt();
            sc.nextLine();
            personList.add(new Person(Fname, Lname, age));
        }
        System.out.println("Persons list before sorting....");
        for(Person person : personList){
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
                    personList.sort(new  Comparator<Person>() {
                        @Override
                        public int compare(Person o1, Person o2) {
                            return o1.getAge() - o2.getAge();
                        }
                    });
                    break;
                case 2:
                    personList.sort(new Comparator<Person>() {
                        @Override
                        public int compare(Person o1, Person o2) {
                            return o2.getAge() - o1.getAge();
                        }
                    }); // inner anonymous class
                    break;
                case 3:
                    personList.sort(new Comparator<Person>() {
                        @Override
                        public int compare(Person o1, Person o2) {
                            return o1.getFname().compareTo(o2.getFname());
                        }
                    });
                    break;
                case 4:
                    personList.sort(new  Comparator<Person>() {
                        @Override
                        public int compare(Person o1, Person o2) {
                            return o2.getFname().compareTo(o1.getFname());
                        }
                    });
                    break;
                case 5:
                    personList.sort(new  Comparator<Person>() {
                        @Override
                        public int compare(Person o1, Person o2) {
                            return o1.getLname().compareTo(o2.getLname());
                        }
                    });
                    break;
                case 6:
                    personList.sort(new  Comparator<Person>() {
                        @Override
                        public int compare(Person o1, Person o2) {
                            return o2.getLname().compareTo(o1.getLname());
                        }
                    });
                    break;
                case 7:
                    flag = false;
                    break;
                default:
                    System.out.println("Invalid choice...try again...");
                    break;

            }
            System.out.println(personList);
        }



    }
}
