package me.shubhamjain.wordgame.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
public class WordGameUtils {

    private WordGameUtils() {}

    public static Map<Character, Integer> buildCharacterMap(String word) {
        log.debug("Building Character Map for word {}", word);
        Map<Character, Integer> wordMap = new HashMap<>();
        for(Character wordCharacter : word.toCharArray()) {
            wordMap.merge(wordCharacter, 1, Integer::sum);
        }
        return wordMap;
    }

    public static Optional<String> loadDataFile(String filename) {
        log.debug("Locating absolute path of file {}", filename);
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        URL url = classLoader.getResource(filename);
        if(url == null)
            return Optional.empty();
        File file = new File(url.getFile());
        return Optional.of(file.getAbsolutePath());
    }
}
