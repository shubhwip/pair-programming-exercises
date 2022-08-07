package com.pp.blackjack.model;

import java.util.HashMap;
import java.util.Map;

public enum FaceValues {
    // Ace is a special Case, Handle it inside the main logic
    // What if special case arises for all other cards ?
    // Ace("A", 1),
    Two("2", 2),
    Three("3", 3),
    Four("4", 4),
    Five("5", 5),
    Six("6", 6),
    Seven("7", 7),
    Eight("8", 8),  
    Nine("9", 9),
    Ten("10", 10),
    Jack("J", 10),
    Queen("Q", 10),
    King("K", 10);

    private final String key;
    private final int value;

    private static Map<String, FaceValues> valueToTextMapping;

    FaceValues(String key, int value) {
        this.key = key;
        this.value = value;
    }

    private static void initMapping() {
        valueToTextMapping = new HashMap<>();
        for (FaceValues s : values()) {
            valueToTextMapping.put(s.key, s);
        }
    }

    public int getValue(String key) {
        if (valueToTextMapping == null) {
            initMapping();
        }
        return valueToTextMapping.get(key).value;
    }
}
