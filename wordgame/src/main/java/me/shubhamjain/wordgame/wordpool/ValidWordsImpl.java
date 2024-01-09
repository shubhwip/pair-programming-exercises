package me.shubhamjain.wordgame.wordpool;

import java.util.Set;

public class ValidWordsImpl implements IValidWords{

    final IPopulateWords populateWords;
    final String wordsLocation;
    final Set<String> words;

    public ValidWordsImpl(IPopulateWords populateWords, String wordsLocation) {
        this.populateWords = populateWords;
        this.wordsLocation = wordsLocation;
        this.words = populateWords.populateWords(wordsLocation);
    }

    @Override
    public boolean contains(String givenWord) {
        return words.contains(givenWord);
    }

    @Override
    public int size() {
        return words.size();
    }
}
