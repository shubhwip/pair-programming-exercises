package com.pp.exception;

public class InputFileParseException extends Exception {
    public InputFileParseException(String message, Exception e) {
        super(message, e);
    }
}
