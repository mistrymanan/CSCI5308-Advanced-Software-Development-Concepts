package org.example.services;

import org.example.entity.Item;
import org.example.entity.VendingMachine;
import org.example.exceptions.InvalidBalanceInputException;
import org.example.exceptions.InvalidInputException;
import org.example.exceptions.VendingMachineAlreadyExistAtLocationError;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class VendingMachineServiceTest {
    private VendingMachineService vendingMachineService = new VendingMachineService();
    private List<VendingMachine> vendingMachineList;

    @BeforeEach
    public void init() {
        vendingMachineList = new ArrayList<>();
        vendingMachineService = new VendingMachineService(vendingMachineList);
    }

    @Test
    public void testGetVendingMachineList() {
        List<VendingMachine> expectedList = new ArrayList<>();
        List<VendingMachine> actualList = vendingMachineService.getVendingMachineList();
        Assertions.assertEquals(expectedList, actualList);
    }

    @Test
    public void testSetVendingMachineList() {
        List<VendingMachine> newList = new ArrayList<>();
        vendingMachineService.setVendingMachineList(newList);
        List<VendingMachine> updatedList = vendingMachineService.getVendingMachineList();
        Assertions.assertEquals(newList, updatedList);
    }

    @Test
    public void testAddVendingMachine()  {
        ArrayList<Item> items=new ArrayList<>();
        items.add(new Item("Pepsi",5,1.5));
        items.add(new Item("Beer",5,2.5));

        //mocking of db
//        when(mockVendingMachineDataService.getVendingMachines())
//                .thenReturn(
//                new ArrayList<>(Arrays.asList(
//                        new VendingMachine("V1","ABC",items,0,"DSU"),
//                        new VendingMachine("V2","DEF",items,0,"HSC"),
//                        new VendingMachine("V3","GHI",items,0,"Goldberg")
//                ))
//        );
        VendingMachine vendingMachine = null;
        try {
            vendingMachine = new VendingMachine("V1","ABC",items,0,"DSU");
            vendingMachineService.addVendingMachine(vendingMachine);
            List<VendingMachine> vendingMachineList = vendingMachineService.getVendingMachineList();
            Assertions.assertEquals(1, vendingMachineList.size());
            Assertions.assertEquals(vendingMachine, vendingMachineList.get(0));
        } catch (InvalidBalanceInputException | InvalidInputException | VendingMachineAlreadyExistAtLocationError e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void testAddVendingMachine_NullVendingMachine() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            vendingMachineService.addVendingMachine(null);
        });
    }

    @Test
    public void testGetVendingMachineUsingLocationForExceptions(){
        Assertions.assertThrows(NullPointerException.class,()->vendingMachineService.getVendingMachineUsingLocation(null));
        Assertions.assertThrows(InvalidInputException.class,()->vendingMachineService.getVendingMachineUsingLocation(""));
    }

    @Test
    public void testAddVendingMachine_DuplicateLocation() {

        VendingMachine vendingMachine1;
        VendingMachine vendingMachine2;
        try {
            vendingMachine1=new VendingMachine("V1","ABC",null,0,"DSU");
            vendingMachine2 = new VendingMachine("V2","ABC",null,0,"DSU");
            Assertions.assertThrows(VendingMachineAlreadyExistAtLocationError.class, () -> {
                vendingMachineService.addVendingMachine(vendingMachine1);
                vendingMachineService.addVendingMachine(vendingMachine2);
            });
        } catch (InvalidBalanceInputException | InvalidInputException e) {
            throw new RuntimeException(e);
        }


    }

    @Test
    public void testTotalRevenue(){
        VendingMachine vendingMachine1;
        VendingMachine vendingMachine2;
        try {
            vendingMachine1=new VendingMachine("V1","ABC",null,300,"DSU");
            vendingMachine2 = new VendingMachine("V2","ABC",null,400,"DSU1");

            vendingMachineService.addVendingMachine(vendingMachine1);
            vendingMachineService.addVendingMachine(vendingMachine2);

            Assertions.assertEquals(700,vendingMachineService.totalRevenue());
        } catch (InvalidBalanceInputException | InvalidInputException | VendingMachineAlreadyExistAtLocationError e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void testTotalRevenueWhenThereIsNoVendingMachine(){
        vendingMachineService.setVendingMachineList(null);
        Assertions.assertThrows(NullPointerException.class,()->vendingMachineService.totalRevenue());
    }

    @Test
    public void testGetListOfLocation(){
        VendingMachine vendingMachine1;
        VendingMachine vendingMachine2;
        try {
            vendingMachine1=new VendingMachine("V1","ABC",null,300,"DSU");
            vendingMachine2 = new VendingMachine("V2","ABC",null,400,"DSU1");

            vendingMachineService.addVendingMachine(vendingMachine1);
            vendingMachineService.addVendingMachine(vendingMachine2);

            Assertions.assertEquals(Arrays.asList("DSU","DSU1"),vendingMachineService.getListOfLocation());
        } catch (InvalidBalanceInputException | InvalidInputException | VendingMachineAlreadyExistAtLocationError e) {
            throw new RuntimeException(e);
        }
    }

}
