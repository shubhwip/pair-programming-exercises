package me.shubhamjain.wordgame.game;

import me.shubhamjain.wordgame.utils.WordGameUtils;
import me.shubhamjain.wordgame.wordpool.IValidWords;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class DefaultWordGame implements IWordGame {

    private final Map<Character, Integer> givenWordCharacterMap;
    private final IValidWords validWords;
    private static final int LEADERBOARD_SIZE = 10;
    private static final String LEADERBOARD_ERROR = "Leaderboard error {}";

    private final ReentrantLock lock = new ReentrantLock();

    private final PriorityBlockingQueue<PlayerSubmission> leaderboard;

    private static final AtomicInteger playerSession = new AtomicInteger(0);

    public DefaultWordGame(String startingWord, IValidWords validWords) {
        this.givenWordCharacterMap = WordGameUtils.buildCharacterMap(startingWord);
        this.validWords = validWords;
        this.leaderboard = new PriorityBlockingQueue<>();
    }

    @Override
    public int submitWord(String player, String word) throws DuplicateLeaderboardEntryException {
        Objects.requireNonNull(player, "Player should not be null");
        Objects.requireNonNull(word, "Word should not be null");
        log.info("Player {} is submitting a word {}", player, word);
        if (word.length() == 0 || checkWordAvailabilityInLeaderboard(word)) {
            log.debug("Either Word already exists in leaderboard or word is empty string");
            throw new DuplicateLeaderboardEntryException("Either Word already exists in leaderboard or word is empty string");
        }
        AtomicInteger wordScore = new AtomicInteger(0);

        // Building given word map
        Map<Character, Integer> wordMap = WordGameUtils.buildCharacterMap(word);

        // Checking word availability in the starting string map
        log.debug("Checking if word's characters are present in starting string map");
        if (checkWordAvailabilityInStartingWord(wordMap)) {
            log.debug("Checking if word is present in valid words list");
            if (validWords.contains(word)) {
                wordScore.set(word.length());
                PlayerSubmission playerSubmission = new PlayerSubmission(playerSession.getAndIncrement(), player, word, wordScore.get());
                lock.lock();
                if (leaderboard.size() >= LEADERBOARD_SIZE) {
                    leaderboard.poll();
                }
                leaderboard.add(playerSubmission);
                lock.unlock();
            }
        }
        return wordScore.get();
    }

    @Override
    public String getPlayerNameAtPosition(int position) {
        Optional<PlayerSubmission> playerSubmission = Optional.empty();
        try {
            playerSubmission = Optional.ofNullable(getPlayerSubmissionAtPosition(position));
        } catch (LeaderboardOutOfBoundException e) {
            log.error(LEADERBOARD_ERROR, e.getMessage());
        }
        return playerSubmission.map(PlayerSubmission::getPlayer).orElse(null);
    }

    @Override
    public String getWordEntryAtPosition(int position) {
        Optional<PlayerSubmission> playerSubmission = Optional.empty();
        try {
            playerSubmission = Optional.ofNullable(getPlayerSubmissionAtPosition(position));
        } catch (LeaderboardOutOfBoundException e) {
            log.error(LEADERBOARD_ERROR, e.getMessage());
        }
        return playerSubmission.map(PlayerSubmission::getWord).orElse(null);
    }

    @Override
    public Integer getScoreAtPosition(int position) {
        Optional<PlayerSubmission> playerSubmission = Optional.empty();
        try {
            playerSubmission = Optional.ofNullable(getPlayerSubmissionAtPosition(position));
        } catch (LeaderboardOutOfBoundException e) {
            log.error(LEADERBOARD_ERROR, e.getMessage());
        }
        return playerSubmission.map(PlayerSubmission::getScore).orElse(null);
    }

    /**
     * checkWordAvailabilityInLeaderboard checks whether word exists in leaderboard or not
     *
     * @param word given word
     * @return return true if word exists in leaderboard otherwise false
     */
    private boolean checkWordAvailabilityInLeaderboard(String word) {
        for(PlayerSubmission playerSubmission : leaderboard) {
            if(playerSubmission.getWord().equals(word))
                return true;
        }
        return false;
    }

    private boolean checkWordAvailabilityInStartingWord(Map<Character, Integer> wordMap) {
        // Good Set of optimizations done here
        for (Map.Entry<Character, Integer> entry : wordMap.entrySet()) {
            if (givenWordCharacterMap.getOrDefault(entry.getKey(), -1) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }

    private PlayerSubmission getPlayerSubmissionAtPosition(int position) throws LeaderboardOutOfBoundException {
        log.debug("Getting Player Results at Position {} in Leaderboard", position);
        PriorityBlockingQueue<PlayerSubmission> lb = new PriorityBlockingQueue<>(leaderboard);
        PlayerSubmission playerSubmission = null;
        if (lb.isEmpty()) {
            throw new LeaderboardOutOfBoundException("Leaderboard not populated");
        }
        if (position < 0 || position > 9) {
            throw new LeaderboardOutOfBoundException("Leaderboard Invalid Position" + position);
        }
        int pos = lb.size() - position;
        while (pos > 0) {
            playerSubmission = lb.poll();
            pos--;
        }
        return playerSubmission;
    }
}
