package me.shubhamjain.cachemanager.type;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

public class FifoCache implements Cache {

    private final int size;
    Queue<Integer> queue;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public FifoCache(int size) {
        this.size = size;
        this.queue = new LinkedList<>();
    }

    @Override
    public void put(Integer val) {
        this.lock.writeLock().lock();
        try {
            if (queue.size() >= size) {
                queue.remove();
            }
            queue.add(val);
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    @Override
    public List<Integer> get() {
        return queue.stream().collect(Collectors.toList());
    }

    @Override
    public void clear() {
        queue.clear();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
