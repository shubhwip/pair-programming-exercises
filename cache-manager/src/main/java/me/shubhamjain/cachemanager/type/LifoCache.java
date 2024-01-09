package me.shubhamjain.cachemanager.type;

import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class LifoCache implements Cache {
    private final int size;
    Stack<Integer> s;

    public LifoCache(final int size) {
        this.size = size;
        this.s = new Stack<>();
    }

    @Override
    public void put(Integer val) {
        if (s.size() >= size)
            s.pop();
        s.push(val);
    }

    @Override
    public List<Integer> get() {
        return s.stream().collect(Collectors.toList());
    }

    @Override
    public void clear() {
        s.clear();
    }

    @Override
    public boolean isEmpty() {
        return s.isEmpty();
    }
}
