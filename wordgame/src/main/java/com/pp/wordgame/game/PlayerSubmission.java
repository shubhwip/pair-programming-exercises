package com.pp.wordgame.game;

import lombok.Value;

@Value
public class PlayerSubmission implements Comparable<PlayerSubmission>, Cloneable {
    private Integer entry;
    private String player;
    private String word;
    private Integer score;

    @Override
    public int compareTo(PlayerSubmission playerSubmission) {
        int playerScore1 = this.getScore();
        int playerScore2 = playerSubmission.getScore();
        if (!this.getScore().equals(playerSubmission.getScore())) return playerScore1 - playerScore2;
        else return playerSubmission.getEntry() - this.getEntry();
    }
}
