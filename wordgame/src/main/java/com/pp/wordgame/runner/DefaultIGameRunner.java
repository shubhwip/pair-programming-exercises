package com.pp.wordgame.runner;

import com.pp.wordgame.game.DefaultWordGame;
import com.pp.wordgame.game.DuplicateLeaderboardEntryException;
import com.pp.wordgame.game.IWordGame;
import com.pp.wordgame.wordpool.DefaultPopulateWords;
import com.pp.wordgame.wordpool.ValidWordsImpl;
import com.pp.wordgame.wordpool.IPopulateWords;
import com.pp.wordgame.wordpool.IValidWords;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultIGameRunner implements IGameRunner {

    private final String startingWord;
    private static final String SCORE_MESSAGE = "Score of Word Submission is {} by Player {}";

    public DefaultIGameRunner(String startingWord) {
        this.startingWord = startingWord;
    }

    @Override
    public void run() {
        IPopulateWords populateWords = new DefaultPopulateWords();
        IValidWords validWords = new ValidWordsImpl(populateWords, "words.txt");
        IWordGame wordGame = new DefaultWordGame(startingWord, validWords);
        // Game simulation
        try {
            log.info(SCORE_MESSAGE, wordGame.submitWord("shubham", "all"), "shubham");
            log.info(SCORE_MESSAGE, wordGame.submitWord("steve", "word"), "steve");
            log.info(SCORE_MESSAGE, wordGame.submitWord("sherlock", "tale"), "sherlock");
            log.info(SCORE_MESSAGE, wordGame.submitWord("sridhar", "glly"), "sridhar");
            log.info(SCORE_MESSAGE, wordGame.submitWord("sharat", "woolly"), "sharat");
            log.info(SCORE_MESSAGE, wordGame.submitWord("shrey", "adder"), "shrey");
            log.info(SCORE_MESSAGE, wordGame.submitWord("shekhar", "all"), "shekhar");
        } catch (DuplicateLeaderboardEntryException e) {
            log.error(e.getMessage());
        }

        log.info("Player at First Position is {}",  wordGame.getPlayerNameAtPosition(0));
        log.info("Player at Second Position is {}",  wordGame.getPlayerNameAtPosition(1));
        log.info("Player at Third Position is {}",  wordGame.getPlayerNameAtPosition(2));

        log.info("Score at Third Position is {}",  wordGame.getScoreAtPosition(2));
        log.info("Word at First Position is {}",  wordGame.getWordEntryAtPosition(0));

    }
}
