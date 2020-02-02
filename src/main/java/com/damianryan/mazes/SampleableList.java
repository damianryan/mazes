package com.damianryan.mazes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class SampleableList<T> extends ArrayList<T> {

    private Random random = new Random();

    public SampleableList(int initialCapacity) {
        super(initialCapacity);
    }

    public SampleableList() {
    }

    public SampleableList(Collection<? extends T> c) {
        super(c);
    }

    public T sample() {
        int size = size();
        return size > 0 ? get(random.nextInt(size)) : null;
    }
}
