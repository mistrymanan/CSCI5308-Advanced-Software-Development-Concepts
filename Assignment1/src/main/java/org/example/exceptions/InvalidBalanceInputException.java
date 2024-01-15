package org.example.exceptions;

public class InvalidBalanceInputException extends Exception {
    public InvalidBalanceInputException() {
    }

    public InvalidBalanceInputException(String message) {
        super(message);
    }
}