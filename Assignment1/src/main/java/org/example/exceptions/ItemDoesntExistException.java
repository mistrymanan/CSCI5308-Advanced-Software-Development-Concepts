package org.example.exceptions;

public class ItemDoesntExistException extends Exception {
    public ItemDoesntExistException() {
    }

    public ItemDoesntExistException(String message) {
        super(message);
    }
}