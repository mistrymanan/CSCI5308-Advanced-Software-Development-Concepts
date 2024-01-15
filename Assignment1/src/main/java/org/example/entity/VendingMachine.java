package org.example.entity;
import org.example.exceptions.*;

import java.util.ArrayList;
import java.util.List;

// VendingMachine is a normal POJO class which is having methods to work with it
public class VendingMachine {
    String id;
    String companyName;
    List<Item> items;
    double balance;
    String location;

    //ITEM_CAPACITY is a variable that will help us to ensure the limit of each item in vending machine
    static int ITEM_CAPACITY=20;
    public VendingMachine() {
        this.items=new ArrayList<>();
    }


    public VendingMachine(String id, String companyName, List<Item> items, long balance, String location) throws InvalidBalanceInputException, InvalidInputException {
        if(balance<0){
            // balance of machine cannot be in negative so we will throw a customised InvalidBalanceInputException
            throw new InvalidBalanceInputException("Balance can't be negative");
        }
        if(location==""){
            //location can't be blank,so throwing InvalidInputException
            throw new InvalidInputException();
        }
        if(location==null){
            //location can also not be null, so to handle it,we are throwing NullPointerException
            throw new NullPointerException();
        }
        if(companyName==null){
            //company name can't be null
            throw new NullPointerException();
        }else if(companyName=="" || companyName==" " || companyName.length()<2){
            //company name can't be blank and it should be having name with more than 2 char
            throw new InvalidInputException();
        }
        this.id = id;
        this.companyName = companyName;
        this.items = items;
        this.balance = balance;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    // it's a setter function for companyName property, which will throw Nullpointer and InvalidInputException
    // based on condition
    public void setCompanyName(String companyName) throws InvalidInputException {
        if(companyName==null){
            throw new NullPointerException();
        }else if(companyName=="" || companyName==" " || companyName.length()<2){
            throw new InvalidInputException();
        }
        this.companyName = companyName;
    }

    public List<Item> getItems() {
        return items;
    }

    // setter method of Item, it will also throw NullPointerException if passing null
    public void setItems(List<Item> items) {
        if(items==null){
            throw new NullPointerException();
        }
        this.items = items;
    }

    // add method of Item, it will also throw NullPointerException if we add null item
    public void addItem(Item item){
        if(item==null){
            throw new NullPointerException();
        }
        this.items.add(item);
    }

    public double getBalance() {
        return balance;
    }

    // setter method of balance property, it can't be negative to ensure that we are checking and throwing
    // InvalidBalanceInputException
    public void setBalance(double balance) throws InvalidBalanceInputException {
        if(balance<0){
            throw new InvalidBalanceInputException();
        }
        this.balance = balance;
    }

    public String getLocation() {
        return location;
    }

    // setter method of location is ensuring that it can't be null and blank
    // accordingly it is throwing NullPointerException and InvalidInputException
    public void setLocation(String location) throws InvalidInputException {
        if(location==null){
            throw new NullPointerException();
        }
        if(location==""){
            throw new InvalidInputException();
        }
        this.location = location;
    }

    // updateQuantityOfGivenItem is a method that helps to update quantity keeping every possible corner cases in mind
    public void updateQuantityOfGivenItem(Item item) throws ItemBufferFullException {
        // Item can't be null
        if(item==null){
            throw new NullPointerException();
        }
        // can't exceed Item_capacity
        if(item.getQuantity()>ITEM_CAPACITY){
            throw new ItemBufferFullException();
        }

        Item existingItem=this.items.stream().filter(i -> i.name.equals(item.name)).findAny().orElse(null);
        // update the quantity if and only if, we are able to find the item in existing database of Item
        if(existingItem!=null){
            // adding new item quantity is not exceeding the Item_capacity then and only then will add otherwise we will throw ItemBufferFullException
            if(existingItem.getQuantity() + item.getQuantity() > ITEM_CAPACITY){
                throw new ItemBufferFullException();
            }else{
                existingItem.setQuantity(existingItem.getQuantity()+item.getQuantity());
            }
        }else{
            // if item don't exist then will add whole new item in our list database
            this.items.add(item);
        }
    }

    // addNewItemInVendingMachine, a method which will add new item to the item list of our vending machine
    public void addNewItemInVendingMachine(Item item){
        //ensures null item constraints
        if(item==null){
            throw new NullPointerException();
        }
        // if items property is not, initialized then we will create a new instance of ArrayList and assign to it otherwise we will directly add the item
        if(this.items!=null){
            this.items.add(item);
        }else{
            this.items=new ArrayList<>();
            items.add(item);
        }
    }

    // in order to buy an item,user has to pass itemName,quantity of purchase and cash to buyItemFromVendingMachineFunction
    // the function is also throwing InvalidInputException, InsufficientCashForSelectedItem, ItemDoesntExistException, InvalidBalanceInputException Exceptions based on constraints
    public boolean buyItemFromVendingMachine(String itemName,int quantity,double cash,int age) throws InvalidInputException, InsufficientCashForSelectedItem, ItemDoesntExistException, InvalidBalanceInputException, ItemCannotBeBoughtDueToUnderAge, ItemWithGivenQuantityDoesntExist {
        // check itemName constraint and throw InvalidInputException if itemName is null or blank
        if( itemName==null || itemName.equals("")){
            throw new InvalidInputException();
        }
        boolean userAddedSufficientCash= checkUserHasGivenSufficientCashForSelectedItem(itemName,quantity,cash);

        // checking whether user has added sufficient Cash or not and if item is restricted then will check the age
        if(
                userAddedSufficientCash
                        &&
                        (   !isItemRestricted(itemName)
                                || (isItemRestricted(itemName)
                                && age>=getItemRestrictedAge(itemName))
                        )
        ){
            if(!checkRequestedQuantityExist(itemName,quantity)){
                throw new ItemWithGivenQuantityDoesntExist("Item With Given Quantity Doesn't Exist");
            }
            // reducing the item which is bought
            reduceTheQuantityOfBoughtItem(itemName,quantity);
            // increasing the balance with given quantity and cash
            increaseTheBalance(cash);
            return true;
        }else if((isItemRestricted(itemName) && ! ( age>=getItemRestrictedAge(itemName)))){
            // as item is restricted and user is restricted due to being underage we are throwing ItemCannotBeBoughtDueToUnderAge Exception
            throw new ItemCannotBeBoughtDueToUnderAge();
        }else{
            // if there is no sufficient Cash then we are throwing InsufficientCashForSelectedItem
                throw new InsufficientCashForSelectedItem("Please Add sufficient cash for selected Items");
        }
    }

    // below method returns the quantity of item based on given Item name
    public int getItemQuantity(String itemName){
        Item existingItem=this.items.stream().filter(i -> i.name.equals(itemName)).findAny().orElse(null);
        if(existingItem==null){
            throw new NullPointerException();
        }
        return existingItem.getQuantity();
    }

    // below method returns the restricted age of item based on given Item name
    public int getItemRestrictedAge(String itemName) throws InvalidInputException, ItemDoesntExistException {
        Item existingItem=getItem(itemName);
        return existingItem.getRestrictedAge();
    }

    public boolean isItemRestricted(String itemName){
        Item existingItem=this.items.stream().filter(i -> i.name.equals(itemName)).findAny().orElse(null);
        if(existingItem==null){
            throw new NullPointerException();
        }
        return existingItem.isRestricted();
    }

    // getItemPrice method will provide the price of given Item and also throws ItemDoesntExistException, InvalidInputException
    public double getItemPrice(String itemName) throws ItemDoesntExistException, InvalidInputException {
        Item existingItem=getItem(itemName);
        return existingItem.getPrice();
    }

    // below method helps to check if users has provided sufficient cash for given item or not
    public boolean checkUserHasGivenSufficientCashForSelectedItem(String itemName,int quantity,double cash) throws ItemDoesntExistException, InvalidInputException {
        // also ensuring that item quantity is >0
        // getItemQuantity(itemName)>0 &&
        if(quantity*getItemPrice(itemName)<=cash){
            return true;
        }
        return false;
    }
    public boolean checkRequestedQuantityExist(String itemName,int quantity) throws InvalidInputException, ItemDoesntExistException {
        Item i=getItem(itemName);
        return i.getQuantity()>=quantity;
    }

//    // below method helps to check if users is allowed to purchase the product or not
//    public boolean checkUserIsAboveRestrictedAge(String itemName,int quantity,long cash) throws ItemDoesntExistException, InvalidInputException {
//        // also ensuring that item quantity is >0
//        if(getItemQuantity(itemName)>0 && (quantity*getItemPrice(itemName)<=cash)){
//            return true;
//        }
//        return false;
//    }

    // getItem method will filter out the arrayList object with items property reference, and if found then it will provide the item
    public Item getItem(String itemName) throws ItemDoesntExistException, InvalidInputException {
        //ensuring itemName constraints
        if( itemName==null || itemName.equals("")){
            throw new InvalidInputException();
        }

        Item existingItem=this.items.stream().filter(i -> i.name.equals(itemName)).findAny().orElse(null);

        if(existingItem!=null){
            return existingItem;
        }else{
            // throwing ItemDoesn't exist Exception if not found
            throw new ItemDoesntExistException();
        }
    }

    // below method will help to increase the balance of vending machine
    private void increaseTheBalance(double totalCash) throws InvalidBalanceInputException {
        setBalance(getBalance()+totalCash);
    }

    public void reduceTheQuantityOfBoughtItem(String itemName,int purchaseQuantity) throws InvalidInputException, ItemDoesntExistException {
        Item existingItem=getItem(itemName);
        existingItem.setQuantity(existingItem.getQuantity()-purchaseQuantity);
    }

}