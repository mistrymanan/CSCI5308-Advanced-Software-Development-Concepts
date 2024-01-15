
# ASDC Assignment 1

The Assignment Submision Contains Vending Machine Applications 

## Instruction to Run the program

It is a Maven Project, you can run Main.Java file

The Application is about a Vending Machine 
4 packages such as entity,exceptions,paymentservice,services

- Test cases have been written under src/test folder. majority of tests are written in VendingMachineTest File which is also having mockup of Database using VendingMachineDataService interface.

- Before executing all the test, I am initializing the Vending Machine with Items using Database mocking with initializeDataFromDB() method under VendingMachineTest.Java

- The program has main two paths, Admin and User

- Admin can Add new Vending Machine, Edit Item Inventory and can check the Revenue

- User can Only Buy the Items, before that user has to select the location.

-  User also has Multiple option of payments, they can go for either card payment or Interact Payment, under Intereact they can select either CIBC or RBC for payment gateway
