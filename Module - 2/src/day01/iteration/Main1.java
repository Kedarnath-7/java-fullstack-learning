package day01.iteration;

import java.util.*;

public class Main1 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Kedarnath");
        list.add("Ms Dhoni");
        list.add("Virat Kohli");
        list.add("Suresh Raina");
        for(String s : list){
            System.out.println(s);
        }
        Iterator<String> itr = list.iterator();
        System.out.println(itr.getClass().getName());
        while( itr.hasNext() ) {
            System.out.println(itr.next());
        }


        List<Integer> list1 = new LinkedList<Integer>();
        list1.add(20);
        list1.add(30);
        list1.add(40);
        list1.add(50);
        for(Integer i : list1){
            System.out.println(i);
        }
        Iterator<Integer> itr1 = list1.iterator();
        System.out.println(itr1.getClass().getName());
        while(itr1.hasNext()){
            System.out.println(itr1.next());
        }


        Set<Integer> list2 = new HashSet<Integer>();
        list2.add(10);
        list2.add(30);
        list2.add(50);
        list2.add(15);
        list2.add(20);
        for(Integer i : list2){
            System.out.println(i);
        }
        Iterator<Integer> itr2 = list2.iterator();
        System.out.println(itr2.getClass().getName());
        while(itr2.hasNext()){
            System.out.println(itr2.next());
        }

        MyIterable myIterable = new MyIterable(10, 20 ,2);
        Iterator iterator = myIterable.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }


        // by default the Iterable and Iterator are Object
        // we need to downcast required class
        // or else change the generic object to required class type in the signature
        // like how MyIterable and MyIterator are implemented

//        for(Object i : myIterable){
//            System.out.println((Integer)i);
//        }

        for(Integer i: myIterable){
            System.out.println(i);
        }


        System.out.println(myIterable.getClass().getName());
    }
}
