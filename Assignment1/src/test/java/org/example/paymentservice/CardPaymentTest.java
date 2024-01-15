package org.example.paymentservice;

import org.example.exceptions.InvalidInputException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CardPaymentTest {
    @Test
    void payTest() throws InvalidInputException {
        CardPayment cardPayment=new CardPayment();
        assertTrue(cardPayment.pay("1234123412341234","100"));
    }
    @Test
    void payTestWithInvalidCardNumber() {
        CardPayment cardPayment=new CardPayment();
        assertThrows(InvalidInputException.class,()-> cardPayment.pay("123412341234123","100"));
    }
}
