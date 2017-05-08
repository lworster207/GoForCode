/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface VendingMachineServiceLayer {

    public String getName(Item item);

    public int getQuantity(Item item);

    public void setQuantity(Item item, int quantity);

    public BigDecimal getBalance();

    public void setBalance(BigDecimal balance);

    public BigDecimal getPrice(Item item);

    public Item getItem(String itemId);

    public List<Item> getAllItems();

    public List<Item> getAllAvailableItems() throws VendingMachinePersistenceException;

    public void makeChange(BigDecimal balance);

    public void dispenseChange();

    public void dispenseItem(Item item) throws VendingMachinePersistenceException;

    public void dispenseItemInLine(Item item);

    public Boolean changeIsDue();

    public int getQuarters();

    public int getNickels();

    public int getDimes();
}
