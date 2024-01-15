package org.example.exceptions;

public class VendingMachineAlreadyExistAtLocationError extends Exception{
    public VendingMachineAlreadyExistAtLocationError() {
    }

    public VendingMachineAlreadyExistAtLocationError(String message) {
        super(message);
    }
}