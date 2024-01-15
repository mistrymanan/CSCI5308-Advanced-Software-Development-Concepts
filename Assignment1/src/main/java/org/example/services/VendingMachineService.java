package org.example.services;

import org.example.entity.VendingMachine;
import org.example.exceptions.InvalidInputException;
import org.example.exceptions.VendingMachineAlreadyExistAtLocationError;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VendingMachineService {
    List<VendingMachine> vendingMachineList;

    public VendingMachineService() {
    }

    public VendingMachineService(List<VendingMachine> vendingMachineList) {
        this.vendingMachineList = vendingMachineList;
    }

    public List<VendingMachine> getVendingMachineList() {
        return vendingMachineList;
    }

    public void setVendingMachineList(List<VendingMachine> vendingMachineList) {
        this.vendingMachineList = vendingMachineList;
    }

    public void addVendingMachine(VendingMachine vendingMachine) throws VendingMachineAlreadyExistAtLocationError, InvalidInputException {
        if(vendingMachine==null){
            throw new NullPointerException();
        }
        try{
            if(getVendingMachineUsingLocation(vendingMachine.getLocation())!=null){
                throw new VendingMachineAlreadyExistAtLocationError("Please Provide other location for Vending Machine!");
            }
        }catch (NullPointerException e){
        }
        if(this.vendingMachineList==null){
            this.vendingMachineList=new ArrayList<>();
        }
        this.vendingMachineList.add(vendingMachine);
    }

    public VendingMachine getVendingMachineUsingLocation(String location) throws InvalidInputException {

        if(location==null){
            throw new NullPointerException("Location can't be null!");
        }else if(location.equals("")){
            throw new InvalidInputException("Location can't be blank!");
        }

        VendingMachine vendingMachine=this.vendingMachineList.stream().filter(v->v.getLocation().equals(location)).findAny().orElse(null);
        return vendingMachine;
    }

    public double totalRevenue(){
        if(this.vendingMachineList==null){
            throw new NullPointerException("No Vending Machine Listed,Please add!");
        }
        return this.vendingMachineList.stream().mapToDouble(v->v.getBalance()).sum();
    }

    public List<String> getListOfLocation(){
        return this.vendingMachineList.stream().map(v->v.getLocation()).collect(Collectors.toList());
    }
}