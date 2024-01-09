package me.shubhamjain.cachemanager.runner;

import me.shubhamjain.cachemanager.manager.DefaultCacheManager;
import me.shubhamjain.cachemanager.manager.CacheManager;

import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Please enter cache mechanism and cache capacity");
        }
        String cacheType = args[0];
        Integer capacity = Integer.parseInt(args[1]);
        CacheManager cacheManager = DefaultCacheManager.getInstance(cacheType, capacity);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter new element for cache");
            int next = scanner.nextInt();
            cacheManager.put(next);
            System.out.println("Updated Cache: " + cacheManager.get());
        }
    }
}
