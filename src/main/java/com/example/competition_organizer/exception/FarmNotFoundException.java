package com.example.competition_organizer.exception;

public class FarmNotFoundException extends RuntimeException {
    public FarmNotFoundException(String message) {
        super(message);
    }
}
