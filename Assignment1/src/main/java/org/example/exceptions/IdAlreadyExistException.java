package org.example.exceptions;

public class IdAlreadyExistException extends Exception {
    public IdAlreadyExistException() {
    }

    public IdAlreadyExistException(String message) {
        super(message);
    }
}