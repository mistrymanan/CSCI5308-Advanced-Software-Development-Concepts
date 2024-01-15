package org.example.paymentservice;

public class CIBCInteractGateway implements InteractGateWay {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing payment of $"+amount+" through interact");
        System.out.println("Payment processed through CIBC Interact.");
        return true;
    }
}
