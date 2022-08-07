package com.pp.wordgame.wordpool;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Set;

public class DefaultPopulateWordTests {

    IPopulateWords populateWords;

    @BeforeEach
    public void setup() {
        populateWords = new DefaultPopulateWords();
    }

    @Test
    public void givenValidFileName_whenPopulateWordsIsCalled_shouldSuccessfullyPopulateWords() {
        // Act
        Set<String> words = populateWords.populateWords("words.txt");
        // Assert
        Assertions.assertEquals(172824, words.size());
    }

    @Test
    public void givenUnavailableFileName_whenPopulateWordsIsCalled_shouldSuccessfullyPopulateWords() {
        // Act & Assert
        Assertions.assertThrows(InvalidWordFileException.class,
                () -> populateWords.populateWords("random_unavailable_file.txt"));
    }

    private String loadDataFile(String filename) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(filename).getFile());
        return file.getAbsolutePath();
    }

}
