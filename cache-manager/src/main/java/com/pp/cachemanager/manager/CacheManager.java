package com.pp.cachemanager.manager;

import java.util.List;

public interface CacheManager {

    void put(Integer val);

    void putAll(List<Integer> vals);

    List<Integer> get();
}
