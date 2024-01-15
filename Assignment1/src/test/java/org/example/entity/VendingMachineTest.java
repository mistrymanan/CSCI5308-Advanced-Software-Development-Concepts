package org.example.entity;

import org.example.exceptions.*;
import org.example.services.VendingMachineDataService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class VendingMachineTest {
    VendingMachine vendingMachine=new VendingMachine();

    @Mock
    VendingMachineDataService mockVendingMachineDataService;

    @BeforeAll
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetterSetterOfId(){
        vendingMachine.setId("id");
        assertEquals("id",vendingMachine.getId());
    }

    @Test
    public void testGetterSetterOfCompanyNamePropery() throws InvalidInputException {
        vendingMachine.setCompanyName("ABC");
        assertEquals("ABC",vendingMachine.getCompanyName());
    }
    @Test
    public void testGetterAndSetterOfItems(){
        ArrayList<Item> itemList=new ArrayList<>(Arrays.asList(
                new Item("Pepsi",5,1.5)
                ,new Item("Beer",5,2.5,true,18)
        ));
        vendingMachine.setItems(itemList);
        assertEquals(itemList,vendingMachine.getItems());
    }

    @Test
    public void testVendingMachineConstructor()  {
        ArrayList<Item> itemList=new ArrayList<>(Arrays.asList(
                new Item("Pepsi",5,1.5)
                ,new Item("Beer",5,2.5,true,18)
        ));
        VendingMachine v1;
        try{
            v1=new VendingMachine("V1","ABC",itemList,0,"DSU");
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        assertEquals("V1",v1.getId());
        assertEquals("ABC",v1.getCompanyName());
        assertEquals(itemList,v1.getItems());
        assertEquals(0,v1.getBalance());
        assertEquals("DSU",v1.getLocation());
    }
    @Test
    public void testVendingMachineConstructorForThrowingExceptions(){
        //create object of vending machine
        assertThrows(NullPointerException.class,()->{
            new VendingMachine("V1",null,null,0,"DSU");
        });
        assertThrows(NullPointerException.class,()->{
            new VendingMachine("V1","ABC",null,0,null);
        });
        assertThrows(InvalidInputException.class,()->{
            new VendingMachine("V1","ABC",null,0,"");
        });
        assertThrows(InvalidInputException.class,()->{
            new VendingMachine("V1","",null,0,"DSU");
        });
        assertThrows(InvalidBalanceInputException.class,()->{
            new VendingMachine("V1","ABC",null,-1,"DSU");
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {-1,-88,-100})
    public void checkNegativeBalanceSetup(int number){
        assertThrows(InvalidBalanceInputException.class,()->{vendingMachine.setBalance(number);});
    }

    @Test
    public void checkNullValueOfItems(){
//        VendingMachine vendingMachine=new VendingMachine();
        assertThrows(NullPointerException.class,()->{vendingMachine.setItems(null);});
    }
    @Test
    public void checkNullValueOfLocation(){
//        VendingMachine vendingMachine=new VendingMachine();
        assertThrows(NullPointerException.class,()->{vendingMachine.setLocation(null);});
    }
    @Test
    public void checkSettingValueOfLocation(){
//        VendingMachine vendingMachine=new VendingMachine();
        try {
            vendingMachine.setLocation("HSC");
            assertEquals("HSC",vendingMachine.getLocation());
        } catch (InvalidInputException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    public void checkBlankValueOfLocation(){
//        VendingMachine vendingMachine=new VendingMachine();
        assertThrows(InvalidInputException.class,()->{vendingMachine.setLocation("");});
    }

    @Test
    public void checkNullValueOfCompanyName(){
//        VendingMachine vendingMachine=new VendingMachine();
        assertThrows(NullPointerException.class,()->{vendingMachine.setCompanyName(null);});
    }
    @Test
    public void checkBlankValueOfCompanyName(){
//        VendingMachine vendingMachine=new VendingMachine();
        assertThrows(InvalidInputException.class,()->{vendingMachine.setCompanyName("");});
    }
    @Test
    public void checkAddingNullItem(){
//        VendingMachine vendingMachine=new VendingMachine();
        assertThrows(NullPointerException.class,()->{vendingMachine.addItem(null);});
    }
    @Test
    public void checkAddingNewItem(){
        Item item=new Item("KitKat",20,1.98);
//        VendingMachine vendingMachine=new VendingMachine();
        vendingMachine.addItem(item);
        try {
            assertEquals(vendingMachine.getItem("KitKat"),item);
        } catch (ItemDoesntExistException | InvalidInputException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    public void checkExceedingBufferLimit(){
//        VendingMachine vendingMachine=new VendingMachine();
        assertThrows(ItemBufferFullException.class,()->{vendingMachine.updateQuantityOfGivenItem(new Item("Pepsi",25,1));});
    }
    @Test
    public void checkAddingNullItemToVendingMachine(){
//        VendingMachine vendingMachine=new VendingMachine();
        assertThrows(NullPointerException.class,()->{vendingMachine.updateQuantityOfGivenItem(null);});
    }
    @Test
    public void multipleCheckForUpdateQuantityOfGivenItem() {
        assertDoesNotThrow(()->{
            vendingMachine.updateQuantityOfGivenItem(new Item("Pepsi",15,1.5));
        });
        assertThrows(ItemBufferFullException.class,
                ()->{
            vendingMachine.updateQuantityOfGivenItem(new Item("Pepsi",1,1.5));
                });
        try {
            assertEquals(20,vendingMachine.getItem("Pepsi").getQuantity());
            vendingMachine.updateQuantityOfGivenItem(new Item("Milk",15,6.88));
            assertEquals(15,vendingMachine.getItem("Milk").getQuantity());
        } catch (ItemDoesntExistException | InvalidInputException | ItemBufferFullException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void checkAddingNonExistingItemToVendingMachine(){
        assertDoesNotThrow(()->{ vendingMachine.updateQuantityOfGivenItem(new Item("pepsi",1,2)); });
    }

    @Test
    public void checkAddingNewNullItemToVendingMachine(){
        assertThrows(NullPointerException.class,()->{
            vendingMachine.addNewItemInVendingMachine(null);
        });
    }

    @Test
    public void checkAddingNewItemWhenVendingMachineDontHaveItemListInitialized(){
        VendingMachine v1;
        try{
            v1=new VendingMachine("V1","ABC",null,0,"DSU");
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
       v1.addNewItemInVendingMachine( new Item("KitKat",5,1.5));
    }
    @Test
    public void checkAddingNewItemToVendingMachine(){
        vendingMachine.addNewItemInVendingMachine(new Item("KitKat",20,1.98));
        assertEquals(20,vendingMachine.getItemQuantity("KitKat"));
    }

    @Test
    public void checkBuyingItemWithInvalidNames(){
        assertThrows(InvalidInputException.class,()->{
            vendingMachine.buyItemFromVendingMachine("",6,5,10);
        });
    }

    @Test
    public void checkItemQuantity(){
        assertThrows(NullPointerException.class,()->{
            vendingMachine.getItemQuantity("lasf");
        });
        assertEquals(5,vendingMachine.getItemQuantity("Pepsi"));
    }

    @Test
    public void checkBuyingItemWithExceedingQuantity(){
        assertThrows(ItemWithGivenQuantityDoesntExist.class,()->{
            vendingMachine.buyItemFromVendingMachine("Pepsi",20,30,15);
        });
    }

    @Test
    public void checkBuyingRestrictedItem() throws InvalidInputException, InvalidBalanceInputException, InsufficientCashForSelectedItem, ItemDoesntExistException, ItemCannotBeBoughtDueToUnderAge, ItemWithGivenQuantityDoesntExist {
        assertThrows(ItemCannotBeBoughtDueToUnderAge.class, ()->{
            vendingMachine.buyItemFromVendingMachine("Beer",1,3,15);
        } );
    }
    @Test
    public void checkBuyingRestrictedItemWithRightAge() throws InvalidInputException, InvalidBalanceInputException, InsufficientCashForSelectedItem, ItemDoesntExistException, ItemCannotBeBoughtDueToUnderAge, ItemWithGivenQuantityDoesntExist {
        assertTrue(vendingMachine.buyItemFromVendingMachine("Beer",1,2.5,19));
    }
    @Test
    public void testBuyItemWithInSufficientCash(){
        assertThrows(InsufficientCashForSelectedItem.class,()->{
            vendingMachine.buyItemFromVendingMachine("Pepsi",20,3,15);
        });
    }

    @Test
    public void testBuyItemFromVendingMachine() throws InvalidInputException, InvalidBalanceInputException, ItemWithGivenQuantityDoesntExist, InsufficientCashForSelectedItem, ItemDoesntExistException, ItemCannotBeBoughtDueToUnderAge {
        //throws  InvalidInputException, InsufficientCashForSelectedItem, ItemDoesntExistException, InvalidBalanceInputException, ItemCannotBeBoughtDueToUnderAge
        assertTrue(vendingMachine.buyItemFromVendingMachine("Pepsi",5,5*1.5,15));
//        thro(vendingMachine.buyItemFromVendingMachine("Pepsi",5,5*1.5,15));
        assertThrows(ItemWithGivenQuantityDoesntExist.class,()->{
            vendingMachine.buyItemFromVendingMachine("Pepsi",5,5*1.5,15);
        });
    }

    @Test
    public void testGetItemRestrictedAge(){
        //check for NullPointer and with restricted items
        try {
            assertEquals(18,vendingMachine.getItemRestrictedAge("Beer"));
        } catch (InvalidInputException | ItemDoesntExistException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void testGetItemRestrictedAgeWhenThereIsNoItemIn(){
        //check for NullPointer and with restricted items
        try {
            assertEquals(18,vendingMachine.getItemRestrictedAge("Beer"));
        } catch (InvalidInputException | ItemDoesntExistException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void testIsItemRestricted(){
        assertTrue(vendingMachine.isItemRestricted("Beer"));
        assertFalse(vendingMachine.isItemRestricted("Pepsi"));
        assertThrows(NullPointerException.class,()->{
            vendingMachine.isItemRestricted(null);
        });
    }

    @Test
    public void testGetItemPrice() throws InvalidInputException, ItemDoesntExistException {
        //check ItemDoesntExistException, InvalidInputException
        // and also price of predefined items
        assertEquals(1.5,vendingMachine.getItemPrice("Pepsi"));
    }

    @Test
    public void testCheckUserHasGivenSufficientCashForSelectedItem() throws InvalidInputException, ItemDoesntExistException {
        //checkUserHasGivenSufficientCashForSelectedItem
        assertTrue(vendingMachine.checkUserHasGivenSufficientCashForSelectedItem("Pepsi",5,7.5));
    }

    @Test
    public void testGetItem(){
        // validate  ItemDoesntExistException, InvalidInputException
        assertThrows(ItemDoesntExistException.class,()->{
            vendingMachine.getItem("Milk");
        });
        assertThrows(InvalidInputException.class,()->{
            vendingMachine.getItem("");
        });
    }
    @Test
    public void testIncreaseTheBalance() throws InvalidInputException, InvalidBalanceInputException, ItemWithGivenQuantityDoesntExist, InsufficientCashForSelectedItem, ItemDoesntExistException, ItemCannotBeBoughtDueToUnderAge {
        vendingMachine.buyItemFromVendingMachine("Pepsi",1,1.5,15);
        vendingMachine.buyItemFromVendingMachine("Beer",1,2.5,18);
        assertEquals(4,vendingMachine.getBalance());
    }

    @Test
    public void testReduceTheQuantityOfBoughtItem() throws InvalidInputException, InvalidBalanceInputException, ItemWithGivenQuantityDoesntExist, InsufficientCashForSelectedItem, ItemDoesntExistException, ItemCannotBeBoughtDueToUnderAge {
        vendingMachine.buyItemFromVendingMachine("Pepsi",1,1.5,15);
        assertEquals(4,vendingMachine.getItemQuantity("Pepsi"));
    }

    @BeforeEach
    public void initializeDataFromDB() throws InvalidInputException, InvalidBalanceInputException {
        System.out.println("Initializing Item Inventory");
        when(mockVendingMachineDataService.getItemListVendingMachine())
                .thenReturn(
                new ArrayList<>(Arrays.asList(
                        new Item("Pepsi",5,1.5)
                        ,new Item("Beer",5,2.5,true,18)
                ))
        );
        vendingMachine.setItems(mockVendingMachineDataService.getItemListVendingMachine());
    }
    @AfterEach
    public void cleanDataOfItems(){
        // clean the items from DB
        vendingMachine.setItems(new ArrayList<>());
        try {
            vendingMachine.setBalance(0);
        } catch (InvalidBalanceInputException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Cleaning Item Inventory");
    }

}