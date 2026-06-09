package day05.arrayDemo;

import java.util.Comparator;

public class FnameAsComparatorAsc implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        return o1.getFname().compareTo(o2.getFname());
    }
}
