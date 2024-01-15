package org.example.services;

import org.example.entity.Item;
import org.example.entity.VendingMachine;

import java.util.List;

public interface VendingMachineDataService {
    public List<VendingMachine> getVendingMachines();
    public List<Item> getItemListVendingMachine();
}
