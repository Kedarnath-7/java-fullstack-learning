package day05.arrayDemo;

import java.util.Comparator;

public class LnameAsComparatorDsc implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        return o2.getLname().compareTo(o1.getLname());
    }
}
