package day05.arrayDemo;

import java.util.Comparator;

public class LnameAsComparatorAsc implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        return o1.getLname().compareTo(o2.getLname());
    }

}
