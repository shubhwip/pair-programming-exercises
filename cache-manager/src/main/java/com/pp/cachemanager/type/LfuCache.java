package com.pp.cachemanager.type;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LfuCache implements Cache {

    private final int size;
    Map<Integer, Integer> map;

    public LfuCache(int size) {
        this.size = size;
        this.map = new LinkedHashMap<>(size);
    }

    @Override
    public void put(Integer val) {
        if (map.size() >= size) {
            removeLeastAccessedElement();
        }
        map.merge(val, 1, Integer::sum);
    }

    private void removeLeastAccessedElement() {
        Integer key = map
                .entrySet()
                .stream()
                .min((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1)
                .get()
                .getKey();
        map.remove(key);
    }

    @Override
    public List<Integer> get() {
        return map.keySet().stream().collect(Collectors.toList());
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }
}
