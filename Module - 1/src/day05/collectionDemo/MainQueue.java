package day05.collectionDemo;

import java.util.LinkedList;
import java.util.Queue;

public class MainQueue {
    public static void main(String[] args) {

        // integer
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(20);
        queue.add(35);
        queue.add(17);
        queue.add(33);
        System.out.println("Removing: " + queue.remove());
        System.out.println("Removing: " + queue.remove());
        System.out.println("Removing: " + queue.remove());
        System.out.println("Peeking: "  + queue.peek()); // peek returns value without removing it.

        // string
        Queue<String> strQueue = new LinkedList<String>();
        strQueue.add("Rohit");
        strQueue.add("Bumrah");
        strQueue.add("Surya");
        strQueue.add("Hardik");
        strQueue.add("Tilak");
        System.out.println("String Queue: " + strQueue);
        System.out.println("Removing: " + strQueue.remove());
        System.out.println("Removing: " + strQueue.remove());
        System.out.println("Peeking: " + strQueue.peek());


        // person
        Queue<Person> persons = new LinkedList<Person>();
        persons.offer(new Person("Bruce", "Wayne", 36));
        persons.add(new Person("Clark", "Kent", 43));
        persons.add(new Person("Harry", "Potter", 17));
        persons.add(new Person("Lily", "Potter", 36));
        persons.add(new Person("Tom", "Riddle", 38));
        System.out.println("Persons queue: " + persons);
        System.out.println("Removing: " + persons.remove());
        System.out.println("Removing: " + persons.remove());
        System.out.println("Peeking: " + persons.peek());

        // animal queue
        Queue<Animal> animals = new LinkedList<Animal>();
        animals.offer(new Animal("Chotu", 7));
        animals.add(new Animal("Chimtu", 4));
        animals.add(new Animal("Rox", 5));
        animals.add(new Animal("Husky", 6));
        System.out.println("Animals queue: " + animals);
        System.out.println("Removing: " + animals.remove());
        System.out.println("Removing: " + animals.remove());
        System.out.println("Peeking: " + animals.peek());
    }
}
