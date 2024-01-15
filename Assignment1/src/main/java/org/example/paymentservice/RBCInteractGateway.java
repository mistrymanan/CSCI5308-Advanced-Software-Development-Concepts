package org.example.paymentservice;


public class RBCInteractGateway implements InteractGateWay {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing payment of $"+amount+" through interact");
        System.out.println("Payment processed througth CIBC Interact.");
        return false;
    }
}
