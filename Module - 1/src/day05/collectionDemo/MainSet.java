package day05.collectionDemo;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class MainSet {
    public static void main(String[] args) {
        // Set<Integer> set = new LinkedHashSet<Integer>();  --> for preserving the insertion order of set
        // Set<Integer> set = new TreeSet<Integer>(); --> for sorted values of set
        Set<Integer> set = new HashSet<Integer>();
        set.add(10);
        set.add(25);
        set.add(30);
        set.add(25);
        set.add(10);
        set.add(50);
        System.out.println("Set: " + set);


        // String set
        Set<String> setString = new LinkedHashSet<String>();
        //Set<String> setString = new HashSet<String>();
        // Set<String> setString = new TreeSet<String>();
        setString.add("Dhoni");
        setString.add("Rohit");
        setString.add("Kohli");
        setString.add("Dhoni");
        setString.add("Suresh");
        setString.add("Kohli");
        System.out.println("Set: " + setString);

        //persons
        Set<Person> personSet = new LinkedHashSet<>();
        personSet.add(new Person("MS", "Dhoni", 44));
        personSet.add(new Person("Virat", "Kohli", 37));
        personSet.add(new Person("MS", "Dhoni", 44));
        personSet.add(new Person("Rohit", "Sharma", 39));
        System.out.println("Set: " + personSet);


    }
}
