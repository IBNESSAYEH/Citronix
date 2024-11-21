package com.youcode.citronix.exception.champExceptions;

public class ChampNotFoundException extends RuntimeException {
    public ChampNotFoundException(String message) {
        super(message);
    }
}
