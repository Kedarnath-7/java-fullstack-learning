package day01.iteration;

import java.util.Iterator;

public class MyIterator implements Iterator<Integer> {
    private int start;
    private int end;
    private int step;

    public MyIterator(int start, int end, int step) {
        this.start = start;
        this.end = end;
        this.step = step;
    }

    @Override
    public boolean hasNext() {
        return start <= end;
    }

    @Override
    public Integer next() {
        int value = start;
        start += step;
        return value;
    }
}
