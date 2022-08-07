package com.pp.cachemanager.type;

import java.util.List;

public interface Cache {
    void put(Integer val);

    List<Integer> get();

    void clear();

    boolean isEmpty();
}
