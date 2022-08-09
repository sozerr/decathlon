package com.kuehne.nagel.decathlon.utils;

public class DecathlonException extends RuntimeException {

    public DecathlonException(String message) {
        super(message);
    }

    public DecathlonException(String message, Exception e) {
        super(message, e);

    }
}
