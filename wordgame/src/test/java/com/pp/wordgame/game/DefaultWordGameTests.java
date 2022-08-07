package com.pp.wordgame.game;

import com.pp.wordgame.wordpool.DefaultPopulateWords;
import com.pp.wordgame.wordpool.ValidWordsImpl;
import com.pp.wordgame.wordpool.IPopulateWords;
import com.pp.wordgame.wordpool.IValidWords;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DefaultWordGameTests {

    IValidWords validWords;
    IPopulateWords populateWords;
    IWordGame wordGame;

    @BeforeEach
    void setUp() {
        populateWords = new DefaultPopulateWords();
        validWords = new ValidWordsImpl(populateWords, "words.txt");
        wordGame = new DefaultWordGame("areallylongword", validWords);
    }

    @Test
    void givenStartingString_whenPlayersSubmitsStringSequentially_thenLeaderboardShouldBeUpdated() throws DuplicateLeaderboardEntryException {
        // Act & Assert
        Assertions.assertEquals(3, wordGame.submitWord("shubham", "all"));
        Assertions.assertEquals(4, wordGame.submitWord("steve", "word"));
        Assertions.assertEquals(0, wordGame.submitWord("sherlock", "tale"));
        Assertions.assertEquals(0, wordGame.submitWord("sridhar", "glly"));
        Assertions.assertEquals(6, wordGame.submitWord("sharat", "woolly"));
        Assertions.assertEquals(0, wordGame.submitWord("shrey", "adder"));

        Assertions.assertEquals("sharat", wordGame.getPlayerNameAtPosition(0));
        Assertions.assertEquals("steve", wordGame.getPlayerNameAtPosition(1));
        Assertions.assertEquals("shubham", wordGame.getPlayerNameAtPosition(2));

        Assertions.assertEquals(3, (int) wordGame.getScoreAtPosition(2));
        Assertions.assertEquals("woolly", wordGame.getWordEntryAtPosition(0));
    }



    @Test
    void givenStartingString_whenPlayersSubmitsString_thenCheckingLeaderboardAtInvalidPositionsReturnsNothing() throws DuplicateLeaderboardEntryException {
        // Act & Assert
        Assertions.assertEquals(3, wordGame.submitWord("shubham", "all"));
        Assertions.assertEquals(4, wordGame.submitWord("steve", "word"));

        Assertions.assertEquals("steve", wordGame.getPlayerNameAtPosition(0));
        Assertions.assertEquals("shubham", wordGame.getPlayerNameAtPosition(1));
        Assertions.assertEquals(null, wordGame.getPlayerNameAtPosition(2));
        Assertions.assertEquals(null, wordGame.getPlayerNameAtPosition(8));

        Assertions.assertEquals(null, wordGame.getScoreAtPosition(7));
        Assertions.assertEquals(null, wordGame.getWordEntryAtPosition(5));
    }

    @Test
    void givenStartingString_whenPlayersPlayMultipleTimes_thenCheckingLeaderboardAtInvalidPositionsReturnsNothing() throws DuplicateLeaderboardEntryException {
        // Act & Assert
        Assertions.assertEquals(3, wordGame.submitWord("shubham", "all"));
        Assertions.assertEquals(4, wordGame.submitWord("steve", "word"));
        Assertions.assertEquals(6, wordGame.submitWord("shubham", "woolly"));
        Assertions.assertEquals(0, wordGame.submitWord("shubham", "tale"));

        Assertions.assertEquals("shubham", wordGame.getPlayerNameAtPosition(0));
        Assertions.assertEquals("steve", wordGame.getPlayerNameAtPosition(1));
        Assertions.assertEquals("shubham", wordGame.getPlayerNameAtPosition(2));
        Assertions.assertEquals(null, wordGame.getPlayerNameAtPosition(8));

        Assertions.assertEquals(null, wordGame.getScoreAtPosition(7));
        Assertions.assertEquals(null, wordGame.getWordEntryAtPosition(5));
    }

    @Test
    void givenStartingString_whenPlayersPlaySubmitsSameWordsInLeaderboard_thenSubmitWordShouldThrowDuplicateLeaderboardEntryException() throws DuplicateLeaderboardEntryException {
        // Act & Assert
        Assertions.assertEquals(3, wordGame.submitWord("shubham", "all"));
        Assertions.assertEquals(4, wordGame.submitWord("steve", "word"));
        Assertions.assertEquals(6, wordGame.submitWord("shubham", "woolly"));
        Assertions.assertThrows(DuplicateLeaderboardEntryException.class, () -> wordGame.submitWord("steve", "all"));
    }

}

