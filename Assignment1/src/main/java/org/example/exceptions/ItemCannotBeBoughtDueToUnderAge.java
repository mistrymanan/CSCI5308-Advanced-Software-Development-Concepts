package org.example.exceptions;

public class ItemCannotBeBoughtDueToUnderAge extends Exception {
    public ItemCannotBeBoughtDueToUnderAge() {
    }

    public ItemCannotBeBoughtDueToUnderAge(String message) {
        super(message);
    }
}
