package day05.arrayDemo;

import java.util.Comparator;

public class AgeAsComparatorDsc implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        return o2.getAge() - o1.getAge();
    }
}
