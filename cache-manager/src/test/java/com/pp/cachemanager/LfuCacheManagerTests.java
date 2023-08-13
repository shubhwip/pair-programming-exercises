package com.pp.cachemanager;

import com.pp.cachemanager.manager.CacheManager;
import com.pp.cachemanager.manager.DefaultCacheManager;
import com.pp.cachemanager.type.Cache;
import com.pp.cachemanager.type.InvalidCacheTypeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LfuCacheManagerTests {
    private CacheManager cacheManager;
    private Cache cache;

    @BeforeEach
    public void setUp() {
        cacheManager = DefaultCacheManager.getInstance("lfu", 3);
    }

    @Test
    public void givenCacheValue_whenAskedToAddNewValueToCache_returnsUpdatedCacheValues() {
        cacheManager.putAll(Arrays.asList(1, 2));
        cacheManager.put(3);
        // Then
        assertEquals(3, cacheManager.get().size());
        assertEquals(1, Optional.ofNullable(cacheManager.get().get(0)));
    }

    @Test
    public void givenEmptyCacheValues_whenAskedToAddNewValueToCache_returnsUpdatedCacheValues() {
        cacheManager.put(1);
        // Then
        assertEquals(1, cacheManager.get().size());
    }

    @Test
    public void givenCacheIsFull_whenAskedToAddNewValueToCache_returnsUpdatedCacheValues() {
        cacheManager.putAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        cacheManager.put(8);
        // Then
        assertEquals(3, cacheManager.get().size());
        assertEquals(6, Optional.ofNullable(cacheManager.get().get(0)));
    }

    @Test
    public void givenCacheIsFullWithSameValuesAccessed_whenAskedToAddNewValueToCache_returnsUpdatedCacheValues() {
        cacheManager.putAll(Arrays.asList(1, 2, 3, 4, 2, 3));
        cacheManager.put(6);
        // Then
        assertEquals(3, cacheManager.get().size());
        assertEquals(2, Optional.ofNullable(cacheManager.get().get(0)));
    }

    @Test
    public void givenCacheValue_whenAskedToAddNewValueToCacheAndCacheTypeIsNotGiven_throwsCacheMechanismInvalidException() {

        Assertions.assertThrows(InvalidCacheTypeException.class, () -> {
            DefaultCacheManager.getInstance("fifoj", 7);
        });
    }

    @Test
    public void runMultiThreadTask_WhenPutDataInConcurrentToCache_ThenNoDataLost() throws Exception {
        final ExecutorService executorService = Executors.newFixedThreadPool(5);
        cacheManager = DefaultCacheManager.getInstance("lfu", 50);
        CountDownLatch countDownLatch = new CountDownLatch(50);
        Random rand = new Random();
        try {
            IntStream.range(0, 50).<Runnable>mapToObj(key -> () -> {
                int a = rand.nextInt(10);
                cacheManager.put(a);
                countDownLatch.countDown();
            }).forEach(executorService::submit);
            countDownLatch.await();
        } finally {
            executorService.shutdown();
        }
        assertEquals(cacheManager.get().size(), 10);
    }

}
