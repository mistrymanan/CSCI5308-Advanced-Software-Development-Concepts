package org.example.paymentservice;

import org.example.exceptions.InvalidInputException;

import java.util.Scanner;

public class InteractPayment implements Payment {
    @Override
    public double pay() throws InvalidInputException {
        Scanner sc=new Scanner(System.in);
        System.out.printf("Enter Interact ID:");
        String interactId=sc.nextLine();
        interactId = interactId.replaceAll("\\s+", "");
        isValidEmail(interactId);
        System.out.print("Enter Amount:");
        double amount=sc.nextDouble();
        sc.nextLine();
        System.out.println("");
        System.out.println("Select Gateway");
        System.out.println("1) CIBC");
        System.out.println("2) RBC");
        int option=sc.nextInt();
        sc.nextLine();
        InteractGateWay interactGateWay;
        switch (option){
            case 1:
                interactGateWay=new CIBCInteractGateway();
                break;
            default:
                interactGateWay=new RBCInteractGateway();
        }
        pay(interactId,amount,interactGateWay);

        return amount;
    }
    public boolean pay(String interactID,double amount,InteractGateWay interactGateWay) throws InvalidInputException {
        if (isValidEmail(interactID)) {
            return interactGateWay.processPayment(amount);
        } else {
            throw new InvalidInputException("Enter valid InteractID and Amount");
        }
    }

    public static boolean isValidEmail(String email) throws InvalidInputException {
        String regex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        if(!email.matches(regex)){
            throw new InvalidInputException("Invalid InteractID");
        }
        return true;
    }
}
