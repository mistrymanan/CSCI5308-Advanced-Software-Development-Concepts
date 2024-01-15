package org.example.exceptions;

public class InsufficientCashForSelectedItem extends Exception {
    public InsufficientCashForSelectedItem() {
    }

    public InsufficientCashForSelectedItem(String message) {
        super(message);
    }
}