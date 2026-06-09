package day01.interConversionCollections;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Demo {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(45);
        list.add(10);
        list.add(15);
        list.add(20);
        System.out.println(list);

        Set<Integer> set = new LinkedHashSet<>(list);
        System.out.println(set);
        System.out.println(list instanceof Set);
        System.out.println(list instanceof List);
        System.out.println("==================");
        list = new ArrayList<>(set);
        System.out.println(list);
        System.out.println(list instanceof Set);
        System.out.println(list instanceof List);



    }
}
