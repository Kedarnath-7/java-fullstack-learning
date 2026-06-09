package day05.arrayDemo;

import java.util.Comparator;

public class FnameAsComparatorDsc implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        return o2.getFname().compareTo(o1.getFname());
    }
}
