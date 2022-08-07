package com.pp.cachemanager.type;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

public class LruCache implements Cache {

    Set<Integer> cache;
    int capacity;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public LruCache(int capacity) {
        this.cache = new LinkedHashSet<>(capacity);
        this.capacity = capacity;
    }

    private boolean get(Integer key) {
        this.lock.writeLock().lock();
        try {
            if (!cache.contains(key))
                return false;
            cache.remove(key);
            cache.add(key);
        } finally {
            this.lock.writeLock().unlock();
        }
        return true;
    }

    @Override
    public void put(Integer key) {
        this.lock.writeLock().lock();
        try {
            if (get(key) == false) {
                if (cache.size() == capacity) {
                    int firstEntry = cache.iterator().next();
                    cache.remove(firstEntry);
                }
                cache.add(key);
            }
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    @Override
    public List<Integer> get() {
        return cache.stream().collect(Collectors.toList());
    }

    @Override
    public void clear() {
        cache.clear();
    }

    @Override
    public boolean isEmpty() {
        return cache.isEmpty();
    }
}
