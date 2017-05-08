/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.Change;
import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author apprentice
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    public VendingMachineDao dao;
    public BigDecimal userBalance;
    public Change usersChange;

    public VendingMachineServiceLayerImpl(VendingMachineDao dao) {
        this.dao = dao;
        this.userBalance = new BigDecimal("0.00");
        this.usersChange = new Change();
    }

    @Override
    public String getName(Item item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getQuantity(Item item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BigDecimal getPrice(Item item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Item> getAllItems() {
        List<Item> allItems = null;
        try {
            dao.getAllItems();
        } catch (VendingMachinePersistenceException e) {

        }
        return allItems;
    }

    @Override
    public Item getItem(String itemId) {
        Item item = null;
        try {
            item = dao.getItem(itemId);
        } catch (VendingMachinePersistenceException e) {

        }
        return item;
    }

    @Override
    public void setQuantity(Item item, int quantity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Item> getAllAvailableItems() throws VendingMachinePersistenceException {
        //dao.loadItems();
        return dao.getAllItems().stream().filter(item -> item.getQuantity() > 0).collect(Collectors.toList());
    }

    @Override
    public BigDecimal getBalance() {
        return this.userBalance;
    }

    @Override
    public void setBalance(BigDecimal balance) {
        this.userBalance = balance;
    }

    public void creditBalance(BigDecimal amount) {
        // increase user balance by the newValue
        this.userBalance.add(amount);

    }

    public void debitBalance(BigDecimal amount) {
        this.userBalance.subtract(amount);

    }

    @Override
    public void makeChange(BigDecimal balance) {
        this.usersChange.makeChange(balance);
    }

    @Override
    public void dispenseChange() {
        userBalance = userBalance.subtract(userBalance);
        usersChange.dispenseChange();
    }

    @Override
    public void dispenseItem(Item item) throws VendingMachinePersistenceException {

        //Item dispensedItem = item;
        dao.getItem(item.itemId).setQuantity(item.getQuantity() - 1);
        //serviceui.dao.item.setQuantity(item.getQuantity() - 1);

        dao.writeItems();

    }

    @Override
    public void dispenseItemInLine(Item item) {

        //Item dispensedItem = item;
        try {
            dao.getItem(item.itemId).setQuantity(item.getQuantity() - 1);
            //serviceui.dao.item.setQuantity(item.getQuantity() - 1);

            dao.writeItems();
        } catch (VendingMachinePersistenceException e) {

        }

    }

    @Override
    public Boolean changeIsDue() {
        return usersChange.changeIsDue();
    }

    @Override
    public int getQuarters() {
        return usersChange.getQuarters();
    }

    @Override
    public int getDimes() {
        return usersChange.getDimes();
    }

    @Override
    public int getNickels() {
        return usersChange.getNickels();
    }

}
