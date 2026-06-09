package day05.collectionDemo;

import java.util.PriorityQueue;
import java.util.Queue;

public class MainPriorityQueue {
    public static void main(String[] args) {

        // priority queue sorts elements before removing

        Queue<Integer> queue = new PriorityQueue<Integer>();
        // for reversing the order of storing we can use Collections.reverseOrder() comparator
        // Queue<Integer> queue = new PriorityQueue<Integer>(Collections.reverseOrder());
        queue.add(35);
        queue.offer(23); // .offer is same as add
        queue.add(45);
        queue.add(77);
        queue.add(18);
        System.out.println("Integer queue: " + queue);
        System.out.println("removing: " + queue.remove());  // remove method returns a value
        System.out.println("removing: " + queue.remove());
        System.out.println("removing: " + queue.poll()); // .poll is same as remove
        System.out.println(queue);
        System.out.println("Peeking : " + queue.peek()); // peek only returns value without removing


        // string
        Queue<String>  strQueue = new PriorityQueue<String>();
        strQueue.add("Iron Man");
        strQueue.add("Spiderman");
        strQueue.add("Captain America");
        strQueue.add("Thor");
        System.out.println("String queue: " + strQueue);
        System.out.println("Peeking: " + strQueue.peek());
        System.out.println("removing: " + strQueue.remove());
        System.out.println("removing: " + strQueue.remove());
        System.out.println("Peeking: " + strQueue.peek());
        System.out.println(strQueue);


        // person
        //Queue<Person> persons = new PriorityQueue<Persons>(Collections.reverseOrder());
        Queue<Person> persons = new PriorityQueue<Person>();
        persons.add(new Person("MS", "Dhoni", 44));
        persons.add(new Person("Suresh", "Raina", 37));
        persons.add(new Person("Dwayne", "Bravo", 39));
        persons.add(new Person("Du", "Plessis", 41));
        persons.add(new Person("Shane", "Watson", 42));
        System.out.println("Persons queue: " + persons);
        System.out.println("Peeking: " + persons.peek());
        System.out.println("removing: " + persons.remove());
        System.out.println("removing: " + persons.remove());
        System.out.println("Peeking: " + persons.peek());
        System.out.println("Persons queue: " + persons);


        // animal
        //Queue<Animal> animals = new PriorityQueue<Animal>(Collections.reverseOrder());
        Queue<Animal> animals = new PriorityQueue<Animal>();
        animals.add(new Animal("Chimtu", 5));
        animals.add(new Animal("Roxxy", 7));
        animals.add(new Animal("Sheperd", 4));
        animals.add(new Animal("Chotu", 2));
        animals.add(new Animal("Husky", 8));
        System.out.println("Animals queue: " + animals);
        System.out.println("Peeking: " + animals.peek());
        System.out.println("removing: " + animals.remove());
        System.out.println("removing: " + animals.remove());
        System.out.println("Peeking: " + animals.peek());
        System.out.println("Animals queue: " + animals);

    }
}
