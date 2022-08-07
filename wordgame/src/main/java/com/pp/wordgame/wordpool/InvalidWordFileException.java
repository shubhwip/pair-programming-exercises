package com.pp.wordgame.wordpool;

public class InvalidWordFileException extends RuntimeException{
    public InvalidWordFileException(Exception e) {
        super(e);
    }
}
