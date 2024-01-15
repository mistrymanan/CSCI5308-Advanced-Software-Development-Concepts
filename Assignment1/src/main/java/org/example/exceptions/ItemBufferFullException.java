package org.example.exceptions;

public class ItemBufferFullException extends Exception {
    public ItemBufferFullException() {
    }

    public ItemBufferFullException(String message) {
        super(message);
    }
}