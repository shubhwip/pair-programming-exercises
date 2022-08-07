package com.pp.wordgame.game;

public class LeaderboardOutOfBoundException extends IndexOutOfBoundsException{
    public LeaderboardOutOfBoundException(String message) {
        super(message);
    }
}
