package day05.collectionDemo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainArrayList {
    public static void main(String[] args) {
        
        // list of integers
        List<Integer> list = new ArrayList<Integer>();

        // List<Integer> list = new LinkedList<Integer>();
        //List<Integer> list = new Vector<Integer>();
        //List<Stack> list = new Stack<Integer>();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        System.out.println(list);
        System.out.println(list.get(3));
        System.out.println(list.getLast());
        System.out.println(list.set(1, 10));
        System.out.println(list);
        list.remove(4);
        System.out.println(list);
        Collections.sort(list);


        // list of strings
        List<String> listString = new ArrayList<String>();
        listString.add("Tony Stark");
        listString.add("Peter Parker");
        listString.add("Steve Rogers");
        listString.add("Thor Odinson");
        listString.add("Peter Quill");
        System.out.println(listString);
        listString.remove(4);
        System.out.println(listString);
        System.out.println(listString.set(3, "Point Breaker"));
        System.out.println(listString);
        Collections.sort(listString);

        
        // list of persons
        List<Person> persons = new ArrayList<Person>();
        persons.add(new Person("MS", "Dhoni", 44));
        persons.add(new Person("Ruturaj", "Gaikwad", 28));
        persons.add(new Person("Sanju", "Samson", 32));
        persons.add(new Person("Shivam", "Dube", 33));
        persons.add(new Person("Ayush", "Mahtre", 19));
        persons.add(new Person("Dewald", "Brevis", 27));
        persons.add(new Person("Prashant", "Veer", 18));
        System.out.println(persons);
        persons.remove(6);
        persons.set(0, new Person("Thala", "Dhoni", 44));
        System.out.println(persons);
        //Collections.sort(persons);
        persons.sort(new  Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getFname().compareTo(o2.getFname());
            }
        });
        System.out.println(persons);


        // list of animals
        List<Animal> animals = new ArrayList<Animal>();
        animals.add(new Animal("Chimtu", 3));
        animals.add(new Animal("Chotu", 4));
        animals.add(new Animal("Simba", 2));
        animals.add(new Animal("Husky", 4));
        animals.add(new Animal("Pitbull", 6));
        System.out.println(animals);
        animals.remove(3);
        System.out.println(animals);
        animals.sort(new Comparator<Animal>() {
            @Override
            public int compare(Animal o1, Animal o2) {
                return o1.getOwner().compareTo(o2.getOwner());
            }
        });
        System.out.println(animals);
        
        
        
        
        
    }
}
