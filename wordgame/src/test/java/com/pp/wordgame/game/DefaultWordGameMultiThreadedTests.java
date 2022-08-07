package com.pp.wordgame.game;

import com.pp.wordgame.wordpool.DefaultPopulateWords;
import com.pp.wordgame.wordpool.ValidWordsImpl;
import com.pp.wordgame.wordpool.IPopulateWords;
import com.pp.wordgame.wordpool.IValidWords;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class DefaultWordGameMultiThreadedTests {

    IValidWords validWords;
    IPopulateWords populateWords;
    IWordGame wordGame;

    private final static int TEST_THREAD_COUNT = 10;
    private volatile int threadsRun = 0;

    @BeforeEach
    public void setUp() {
        populateWords = new DefaultPopulateWords();
        validWords = new ValidWordsImpl(populateWords, "words.txt");
        wordGame = new DefaultWordGame("areallylongword", validWords);
    }


    @Test
    void givenStartingString_whenPlayersSubmitsStringConcurrently_thenLeaderboardShouldBeUpdated() {
        // Arrange
        List<String> players = Arrays.asList("shubham", "steve", "sherlock", "sridhar", "sharat", "shrey", "shekhar");
        List<String> words = Arrays.asList("all", "word", "tale", "glly", "woolly", "adder", "all");

        final AtomicInteger g = new AtomicInteger(0);
        final ExecutorService executorThreadPool = Executors.newFixedThreadPool(TEST_THREAD_COUNT);

        // Act
        Runnable r = () -> {
            int local = g.getAndIncrement();
            try {
                wordGame.submitWord(players.get(local), words.get(local));
            } catch (DuplicateLeaderboardEntryException e) {
                e.printStackTrace();
            }
            threadsRun++;
            if (threadsRun >= TEST_THREAD_COUNT) {
                executorThreadPool.shutdown();
            }
        };

        for (int i = 0; i < TEST_THREAD_COUNT; i++) {
            executorThreadPool.submit(r);
        }
        try {
            if (!executorThreadPool.awaitTermination(3500, TimeUnit.MILLISECONDS)) {
                executorThreadPool.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorThreadPool.shutdownNow();
        }

        // Assert
        Assertions.assertEquals("sharat", wordGame.getPlayerNameAtPosition(0));
        Assertions.assertEquals("steve", wordGame.getPlayerNameAtPosition(1));

        Assertions.assertEquals(3, (int) wordGame.getScoreAtPosition(2));
        Assertions.assertEquals("woolly", wordGame.getWordEntryAtPosition(0));
    }

    @Test
    void givenStartingString_whenThousandPlayersSubmitsStringConcurrently_thenLeaderboardShouldBeUpdated() throws DuplicateLeaderboardEntryException {
        // Arrange
        wordGame = new DefaultWordGame("zithernzithernszitherszitizitiszitszizitzizithzizzlezizzledzizzleszizzlingzlotezlotieszlotyzlotychzlotyszoazoantharianzoantharianszoariazoarialzoariumzodiaczodiacalzodiacszoeazoeaezoealzoeaszoeciazoeciumzoftigzoiczoisitezoisiteszombizombiezombielikezombieszombificationzombificationszombifiedzombifieszombifyzombifyingzombiismzombiismszombiszonalzonallyzonaryzonatezonatedzonationzonationszonezonedzonelesszonerzonerszoneszonetimezonetimeszoningzonkzonkedzonkingzonkszonulazonulaezonularzonulaszonulezonuleszoozoochorezoochoreszooeciazooeciumzoogeniczoogeographerzoogeographerszoogeographiczoogeographicalzoogeographicallyzoogeographieszoogeographyzoogleazoogleaezooglealzoogleaszoogloeazoogloeaezoogloeaszooidzooidalzooidszookeeperzookeeperszookszoolaterzoolaters", validWords);
        List<String> words = Arrays.asList("zit","zizzlingzl","","nzith","it","iazoarialzo","r","szi","ith","h","rsz","zitiz","t","zitis","tis","szi","i","szizi","i","itziz","","zit","zizzlingzl","zizz","zzl","leziz","ziz","z","led","dzizz","izzle","zles","eszi","iazoarialzo","zzli","li","n","","o","ez","loti","","e","zloty","otyz","","l","tyc","ch","","ot","ysz","zoa","az","oant","nth","h","rianz","anzo","zoant","anth","tha","arian","","ns","z","ariaz","","zoar","","ialz","lzoar","","riumz","um","zo","dia","a","zodia","","aca","alzod","zodia","","","szoea","oe","az","oea","aez","","ealzo","lzoea","o","aszoe","zoeci","eciaz","i","zoeci","e","iumzo","m","oftig","tigzo","gz","","czo","","sit","t","zo","isit","","eszo","zomb","mbiz","","ombie","b","","ombie","bieli","elike","ike","","om","biesz","","zomb","mbi","i","ica","at","ionz","n","ombi","b","fic","catio","t","onsz","sz","o","bifi","","ed","zom","","","","szomb","ombif","bifyz","fyzom","zom","mbify","ify","yingz","ngzom","zomb","m","i","smzo","zomb","mb","ii","sm","szo","om","biszo","szona","onalz","alzon","zon","","llyzo","y","ona","","yzon","ona","at","ezona","ona","ate","ed","","nat","t","onzon","zona","n","ti","ons","szone","one","ezo","one","edzon","zone","nel","","","zone","ner","r","o","","sz","onesz","esz","zonet","neti","timez","mezo","z","","t","mes","szoni","on","in","g","o","kzo","on","","","","kin","ngzon","zonk","n","szonu","o","u","azonu","","u","ae","","nul","lar","rzonu","","ulasz","as","zonu","nu","le","zon","n","les","sz","o","z","ocho","ho","rez","","ochor","hore","reszo","sz","","ecia","iaz","zoo","","ci","umzo","zoog","ogen","en","","zoo","","eo","gr","a","h","r","o","","ograp","ra","phe","e","s","oog","","o","raphi","phic","iczo","z","","eog","gr","","hi","","","oog","geog","ogra","ra","phica","","al","lyzoo","","o","eogra","graph","ap","h","es","z","oge","eo","gr","aphyz","hyzo","zoo","ogl","leaz","azoo","oo","glea","","ezoog","oogl","g","ealzo","","o","gle","ea","","o","g","oea","","o","","o","","","ogl","loea","eas","sz","","i","zoo","oidal","dalz","lzoo","ooid","id","s","o","keep","eperz","erz","zo","","eeper","","rszo","zoo","","szool","oolat","","ter","rzool","o","","zithernzithernsz");

        final AtomicInteger g = new AtomicInteger(0);
        final ExecutorService executorThreadPool = Executors.newFixedThreadPool(TEST_THREAD_COUNT);

        // Act
        Runnable r = () -> {
            int local = g.getAndIncrement();
            try {
                wordGame.submitWord("player"+local, words.get(local));
            } catch (DuplicateLeaderboardEntryException e) {
                e.printStackTrace();
            }
            threadsRun++;
            if (threadsRun >= 500) {
                executorThreadPool.shutdown();
            }
        };

        for (int i = 0; i < 500; i++) {
            executorThreadPool.submit(r);
        }
        try {
            if (!executorThreadPool.awaitTermination(3500, TimeUnit.MILLISECONDS)) {
                executorThreadPool.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorThreadPool.shutdownNow();
        }

        // Assert
        Assertions.assertEquals("player380", wordGame.getPlayerNameAtPosition(0));
        Assertions.assertEquals("player13", wordGame.getPlayerNameAtPosition(1));

        Assertions.assertEquals(5, wordGame.getScoreAtPosition(2));
        Assertions.assertEquals("zithernzithernsz", wordGame.getWordEntryAtPosition(0));
    }
}
