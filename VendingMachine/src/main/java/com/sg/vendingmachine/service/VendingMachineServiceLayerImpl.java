/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineDao;
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
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    public VendingMachineDao dao;
    public BigDecimal userBalance;
    //public Change usersChange;

    public VendingMachineServiceLayerImpl(VendingMachineDao dao) {
        this.dao = dao;

        // store the cash received...
        this.userBalance = new BigDecimal("0.00");
    }

    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        // return ALL items
        return dao.getAllItems();
    }

    @Override
    public Item getItem(String itemId) throws VendingMachinePersistenceException {
        return dao.getItem(itemId);
    }

    @Override
    public List<Item> getAllAvailableItems() throws VendingMachinePersistenceException {
        // get only those items with available inventory
        return dao.getAllAvailableItems();
    }

    @Override
    public BigDecimal getBalance() {
        // get the current balance available
        return this.userBalance;
    }

    @Override
    public void setBalance(BigDecimal balance) {
        this.userBalance = balance;
    }

    @Override
    public void validateAvailability(Item item) throws VendingMachineNoItemInventoryException {
        if (item.getQuantity() < 1) {
            throw new VendingMachineNoItemInventoryException("Item is not available");
        }
    }

    @Override
    public void validateFunds(Item item) throws VendingMachineInsufficientFundsException, VendingMachinePersistenceException {
        // see if there are enough funds to make the purchase
        //auditDao.writeAuditEntry("validateFunds against  " + item.getPrice().toString());
        if (this.userBalance.compareTo(item.getPrice()) < 0) {
            throw new VendingMachineInsufficientFundsException("Insufficient Funds for this purchase.");
        }

    }

    @Override
    public Item addItem(Item item) throws VendingMachinePersistenceException {
        // used for a potential admin menu and for testing
        return dao.addItem(item.getItemId(), item);
    }

    @Override
    public Item removeItem(Item item) throws VendingMachinePersistenceException {
        // used for a potential admin menu and for testing
        return dao.removeItem(item.getItemId());
    }

    @Override
    public void dispenseItem(Item item) throws VendingMachinePersistenceException {
        // decrement the item quantity
        try {
            dao.setQuantity(item, item.getQuantity() - 1);
        } catch (VendingMachinePersistenceException e) {
            throw new VendingMachinePersistenceException("Error dispensing item");
        }
    }

}
