package org.example.exceptions;

public class ItemWithGivenQuantityDoesntExist extends Exception {
    public ItemWithGivenQuantityDoesntExist() {
    }

    public ItemWithGivenQuantityDoesntExist(String message) {
        super(message);
    }
}
