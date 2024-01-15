package org.example;

import org.example.entity.Item;
import org.example.entity.VendingMachine;
import org.example.exceptions.*;
import org.example.paymentservice.CardPayment;
import org.example.paymentservice.InteractPayment;
import org.example.paymentservice.Payment;
import org.example.services.VendingMachineService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {

    public static VendingMachineService vendingMachineService;

    static {
    vendingMachineService=new VendingMachineService();
        ArrayList<Item> items=new ArrayList<>();
        items.add(new Item("Pepsi",5,1.5));
        items.add(new Item("Beer",5,2.5,true,18));
        try {
            VendingMachine vendingMachine = new VendingMachine("V1","ABC",items,0,"DSU");
            vendingMachineService.addVendingMachine(vendingMachine);
        } catch (InvalidBalanceInputException | InvalidInputException | VendingMachineAlreadyExistAtLocationError e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        boolean flag=true;
        while(flag){
            System.out.println("Welcome to Vending Machine");
            System.out.print("Press 1 for Admin,2 to Access Vending Machine,3 to Exit:");
            switch (sc.nextInt()){
                case 1:
                    try {
                        admin(sc);
                    } catch (InvalidInputException | InvalidBalanceInputException e) {
                        System.out.println("Error:->"+e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        user(sc);
                    } catch (InvalidInputException e) {
                        System.out.println("Error->"+e.getMessage());
                    }
                    break;
                case 3:
                    flag=false;
                    break;
            }
        }
        System.out.println("Exit.......");
    }

    public static void user(Scanner sc) throws InvalidInputException {
        System.out.println("Welcome to our Vending Machine");
        System.out.println("Please Select Location");

        List<String> locations = null;
        try{
            locations=vendingMachineService.getListOfLocation();
            locations.stream().forEach(System.out::println);
        }catch (NullPointerException e){
            System.out.println("Error-> No Vending Machine Listed!");
            return;
        }

        List<String> finalLocations = locations;
        IntStream
                .range(0,locations.size())
                .forEach(i-> System.out.println(i+") "+ finalLocations.get(i)));
        int option=sc.nextInt();
        sc.nextLine();
        VendingMachine vendingMachine=vendingMachineService.getVendingMachineUsingLocation(finalLocations.get(option));
        System.out.println("Welcome to "+vendingMachine.getLocation()+"'s Vending Machine!");
        System.out.println("Please Select Item you want to buy");
        if(vendingMachine.getItems()!=null){
//            vendingMachine.getItems().stream().forEach(System.out::println);
            System.out.println("Option \t Name \t Quantity \t Price");
            IntStream
                    .range(0, vendingMachine.getItems().size())
                    .forEach(i-> {
                        Item item=vendingMachine.getItems().get(i);
                        if(item.getQuantity()!=0){
                            System.out.println(i+") "+ item.getName() +" "+item.getQuantity()+" "+item.getPrice());
                        }
                    });
            System.out.print("Item Number:");
            newLine();
            option = sc.nextInt();
            sc.nextLine();
            Item item=vendingMachine.getItems().get(option);
            String itemName=item.getName();
            // logic of buy
            System.out.print("Entery Quantity");
            int quantity=sc.nextInt();
            sc.nextLine();
            while(quantity>item.getQuantity()){
                System.out.print("Please Enter Valid Quantity:");
                quantity=sc.nextInt();
                sc.nextLine();
            }
            int age=0;
            if(item.isRestricted()){
                System.out.print("Enter Your Age:");
                age= sc.nextInt();
                newLine();
                if(age<item.getRestrictedAge()){
                    System.out.println("Sorry Item is Restricted, You can't Buy Thank You!");
                    return;
                }
            }
            System.out.println("Your Total Cost->"+quantity*item.getPrice());
            System.out.println("Please Select Payment Method");
            double payment=processPayment(sc);
            if(payment>=quantity*item.getPrice()){
                try {
                    vendingMachine.buyItemFromVendingMachine(itemName,quantity,payment,age);
                    System.out.println("Thank you for Purchasing");
                } catch (InsufficientCashForSelectedItem | ItemDoesntExistException | InvalidBalanceInputException |
                         ItemCannotBeBoughtDueToUnderAge | ItemWithGivenQuantityDoesntExist e) {
                    System.out.println("Error->"+e.getMessage());
                }
                if(payment>quantity*item.getPrice()){
                    System.out.println("Please Collect change->"+(payment-quantity*item.getPrice()));
                }
            }else{
                System.out.println("Payment Failed,Please Try Again later");
            }
        }
    }

    public static double processPayment(Scanner sc) {
        System.out.println("1)Card Payment");
        System.out.println("2)Interact Payment");
        int option=sc.nextInt();
        sc.nextLine();
        Payment payment=getPaymentObject(option);
        try {
            return payment.pay();
        } catch (InvalidInputException e) {
            System.out.println("Error->"+e.getMessage());
        }
        return 0;
    }
    public static Payment getPaymentObject(int i){
        if(i==1){
            return new CardPayment();
        }else{
            return new InteractPayment();
        }
    }
    public static void admin(Scanner sc) throws InvalidInputException, InvalidBalanceInputException {
        System.out.println("Welcome to Admin Portal");
        System.out.println("1) To Add Vending Machine");
        System.out.println("2) Select and Edit Vending Machine");
        System.out.println("3) Total Revenue");
        int command = sc.nextInt();
        sc.nextLine();
        switch (command){
            case 1:
                addNewVendingMachine(sc);
                break;
            case 2:
                selectAndUpdateItemDetails(sc);
                break;
            case 3:
                try{
                    System.out.println("Total Revenue->"+vendingMachineService.totalRevenue());
                }catch (NullPointerException e){
                    System.out.println("Error->"+e.getMessage());
                }
                break;
        }
    }

    private static void selectAndUpdateItemDetails(Scanner sc) {
        System.out.println("Select Location among which you want to Edit");
        List<String> locations = null;

        try{
           locations=vendingMachineService.getListOfLocation();
           locations.stream().forEach(System.out::println);
        }catch (NullPointerException e){
            System.out.println("Error-> No Vending Machine Listed!");
            return;
        }

        List<String> finalLocations = locations;
        IntStream
                .range(0,locations.size())
                .forEach(i-> System.out.println(i+") "+ finalLocations.get(i)));
        int option=sc.nextInt();
        sc.nextLine();
        try {
            VendingMachine vendingMachine=vendingMachineService.getVendingMachineUsingLocation(locations.get(option));
            addOrUpdateNewOrOldItem(vendingMachine,sc);
        } catch (InvalidInputException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addOrUpdateNewOrOldItem(VendingMachine vendingMachine, Scanner sc){
        Item item=new Item();
        System.out.println("Enter Item Details");
        System.out.print("Name:");
        item.setName(sc.nextLine());
        newLine();
        System.out.println("Quantity:");
        item.setQuantity(sc.nextInt());
        sc.nextLine();
        newLine();
        System.out.print("Price:");
        item.setPrice(sc.nextDouble());
        sc.nextLine();
        newLine();
        System.out.print("is Item Restricted ? y/n");
        if(sc.nextLine().equals("y")){
            item.setRestricted(true);
            System.out.print("Enter Age:");
            item.setRestrictedAge(sc.nextInt());
            newLine();
        }
        try {
            vendingMachine.updateQuantityOfGivenItem(item);
        } catch (ItemBufferFullException e) {
            System.out.println("Error->"+e.getMessage());
        }
    }
    public static void newLine(){
        System.out.print("\n");
    }
    public static void addNewVendingMachine(Scanner sc) throws InvalidBalanceInputException, InvalidInputException {
        System.out.println("Enter Details:");
        VendingMachine vm=new VendingMachine();
        vm.setItems(new ArrayList<>());
        vm.setBalance(0);
        System.out.print("Company Name:");
        vm.setCompanyName(sc.nextLine());
        System.out.print("Location:");
        vm.setLocation(sc.nextLine());
        vm.setId("1");
        try {
            vendingMachineService.addVendingMachine(vm);
        } catch (VendingMachineAlreadyExistAtLocationError e) {
            System.out.println("Error->"+"Vending Machine Already Exist at Selected Location!");
        }
    }
}