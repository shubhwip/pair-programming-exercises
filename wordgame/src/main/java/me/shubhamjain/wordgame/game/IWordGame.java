package me.shubhamjain.wordgame.game;

public interface IWordGame {
    int submitWord(String player, String word) throws DuplicateLeaderboardEntryException;

    String getPlayerNameAtPosition(int position);

    String getWordEntryAtPosition(int position);

    Integer getScoreAtPosition(int position);
}
