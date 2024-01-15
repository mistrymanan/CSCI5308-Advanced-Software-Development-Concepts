package org.example.entity;
public class Item {
    String name;
    int quantity;
    double price;
    boolean isRestricted;
    int restrictedAge;

    public Item() {
    }

    public Item(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.isRestricted = false;
    }
    public Item(String name, int quantity, double price,boolean isRestricted,int restrictedAge) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.isRestricted=isRestricted;
        this.restrictedAge=restrictedAge;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isRestricted() {
        return isRestricted;
    }

    public void setRestricted(boolean restricted) {
        isRestricted = restricted;
    }

    public int getRestrictedAge() {
        return restrictedAge;
    }

    public void setRestrictedAge(int restrictedAge) {
        this.restrictedAge = restrictedAge;
    }
}