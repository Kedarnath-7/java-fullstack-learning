package day01.iteration;

import java.util.Iterator;

public class MyIterable implements Iterable<Integer> {
    private int start;
    private int end;
    private int step;
    public MyIterable(int start, int end, int step) {
        this.start = start;
        this.end = end;
        this.step = step;
    }

    @Override
    public Iterator iterator() {
        return new MyIterator(start, end, step);
    }

}
