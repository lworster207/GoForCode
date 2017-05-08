/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface VendingMachineDao {

    public String getName(Item item) throws VendingMachinePersistenceException;

    public List<Item> getAllItems() throws VendingMachinePersistenceException;

    public List<Item> getAllAvailableItems() throws VendingMachinePersistenceException;

    public Item getItem(String itemId) throws VendingMachinePersistenceException;

    public void setQuantity(Item item, int quantity) throws VendingMachinePersistenceException;

    public int getQuantity(Item item) throws VendingMachinePersistenceException;

    public BigDecimal getPrice(Item item) throws VendingMachinePersistenceException;

    public Item addItem(String itemId, Item item) throws VendingMachinePersistenceException;

    public Item removeItem(String itemId) throws VendingMachinePersistenceException;

    public void loadItems() throws VendingMachinePersistenceException;

    public void writeItems() throws VendingMachinePersistenceException;

}
