/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.ChangeNoChangeDueException;
import com.sg.vendingmachine.dao.VendingMachineInsufficientFundsException;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface VendingMachineServiceLayer {

    public String getName(Item item) throws VendingMachinePersistenceException;

    public int getQuantity(Item item) throws VendingMachinePersistenceException;

    public void setQuantity(Item item, int quantity) throws VendingMachinePersistenceException;

    public BigDecimal getPrice(Item item) throws VendingMachinePersistenceException;

    public Item getItem(String itemId) throws VendingMachinePersistenceException;

    public List<Item> getAllItems() throws VendingMachinePersistenceException;

    public List<Item> getAllAvailableItems() throws VendingMachinePersistenceException;

    public void dispenseItem(Item item) throws VendingMachinePersistenceException;

    public void validateFunds(Item item) throws VendingMachineInsufficientFundsException, VendingMachinePersistenceException;

    public Item addItem(Item item) throws VendingMachinePersistenceException;

    public Item removeItem(Item item) throws VendingMachinePersistenceException;

    public BigDecimal getBalance() throws VendingMachinePersistenceException;

    public void setBalance(BigDecimal balance) throws VendingMachinePersistenceException;

    public void makeChange(BigDecimal balance) throws ChangeNoChangeDueException, VendingMachinePersistenceException;

    public void dispenseChange() throws VendingMachinePersistenceException;

    public Boolean changeIsDue();

    public int getQuarters();

    public int getNickels();

    public int getDimes();
}
