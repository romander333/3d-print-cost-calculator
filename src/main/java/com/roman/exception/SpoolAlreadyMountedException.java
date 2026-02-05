package com.roman.exception;

public class SpoolAlreadyMountedException extends RuntimeException {
    public SpoolAlreadyMountedException(String message) {
        super(message);
    }
}
