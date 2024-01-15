package org.example.paymentservice;

import org.example.exceptions.InvalidInputException;

public interface Payment {
    public double pay() throws InvalidInputException;
}
