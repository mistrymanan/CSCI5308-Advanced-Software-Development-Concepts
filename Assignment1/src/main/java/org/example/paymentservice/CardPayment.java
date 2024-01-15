package org.example.paymentservice;

import org.example.exceptions.InvalidInputException;

import java.util.Scanner;

public class CardPayment implements Payment {
    @Override
    public double pay() throws InvalidInputException {
        Scanner sc=new Scanner(System.in);
        System.out.printf("Enter Card Details");
        String cardNumber=sc.nextLine();
        System.out.println("Enter Amount");
        String amount=sc.nextLine();
        cardNumber = cardNumber.replaceAll("\\s+", "");
        amount = amount.replaceAll("\\s+", "");
        pay(cardNumber,amount);
        return Double.parseDouble(amount);
    }
    public boolean pay(String cardNumber,String amount) throws InvalidInputException {
        if (cardNumber.matches("(\\d){16}") && amount.matches("^\\d{1,}$|(?=^.{1,}$)^\\d+\\.\\d{1,}$")) {
            return true;
        } else {
            throw new InvalidInputException("Enter Valid Card Number it should include total 16 digits");
        }
    }
}
