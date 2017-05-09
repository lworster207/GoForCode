/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineInsufficientFundsException;
import com.sg.vendingmachine.dao.VendingMachineNoItemInventoryException;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface VendingMachineServiceLayer {

    public Item getItem(String itemId) throws VendingMachinePersistenceException;

    public List<Item> getAllItems() throws VendingMachinePersistenceException;

    public List<Item> getAllAvailableItems() throws VendingMachinePersistenceException;

    public void dispenseItem(Item item) throws VendingMachinePersistenceException;

    public void validateFunds(Item item) throws VendingMachineInsufficientFundsException, VendingMachinePersistenceException;

    public void validateAvailability(Item item) throws VendingMachineNoItemInventoryException;

    public Item addItem(Item item) throws VendingMachinePersistenceException;

    public Item removeItem(Item item) throws VendingMachinePersistenceException;

    public BigDecimal getBalance();

    public void setBalance(BigDecimal balance);

}
