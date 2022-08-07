package com.pp.cachemanager.manager;

import com.pp.cachemanager.type.CacheFactory;
import com.pp.cachemanager.type.Cache;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class DefaultCacheManager implements CacheManager {

    Cache cache;
    Integer capacity;

    private DefaultCacheManager(Cache cache, Integer capacity) {
        this.cache = cache;
        this.capacity = capacity;
    }

    public static CacheManager getInstance(String cacheType, Integer capacity) {
        return new DefaultCacheManager(CacheFactory.getInstance(cacheType, capacity), capacity);
    }

    @Override
    public void put(Integer val) {
        cache.put(val);
    }

    @Override
    public void putAll(List<Integer> vals) {
        vals.stream().forEach(v -> cache.put(v));
    }

    @Override
    public List<Integer> get() {
        return cache.get();
    }
}
