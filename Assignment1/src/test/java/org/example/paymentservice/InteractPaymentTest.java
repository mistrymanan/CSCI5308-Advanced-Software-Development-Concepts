package org.example.paymentservice;

import org.example.exceptions.InvalidInputException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class InteractPaymentTest {

    InteractPayment interactPayment=new InteractPayment();
    @Test
    void payTest() throws InvalidInputException {
        InteractGateWay interactGateWay= mock(CIBCInteractGateway.class);
        when(interactGateWay.processPayment(100)).thenReturn(true);
        assertTrue(interactPayment.pay("mananmistry10@gmail.com",100.0,interactGateWay));
    }
    @Test
    void payTestWithScotiaData() throws InvalidInputException {
        InteractGateWay interactGateWay= mock(InteractGateWay.class);
        when(interactGateWay.processPayment(100)).thenReturn(true);
        assertTrue(interactPayment.pay("mananmistry10@gmail.com",100.0,interactGateWay));
    }
    @Test
    void payTestFail() throws InvalidInputException {
        InteractGateWay interactGateWay= mock(CIBCInteractGateway.class);
        when(interactGateWay.processPayment(100)).thenReturn(false);
        assertFalse(interactPayment.pay("mananmistry10@gmail.com",100.0,interactGateWay));
    }
    @Test
    void payTestWithInvalidInteractId() {
        InteractGateWay interactGateWay= mock(CIBCInteractGateway.class);
        when(interactGateWay.processPayment(100)).thenReturn(true);
        assertThrows(InvalidInputException.class,()->{
            interactPayment.pay("mananmistry10@gmail",100.0,interactGateWay);
        });
    }
}
