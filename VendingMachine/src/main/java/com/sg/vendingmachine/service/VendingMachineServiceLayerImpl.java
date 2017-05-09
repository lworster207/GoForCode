/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.Change;
import com.sg.vendingmachine.ChangeNoChangeDueException;
import com.sg.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachineInsufficientFundsException;
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
    public VendingMachineAuditDao auditDao;
    public BigDecimal userBalance;
    public Change usersChange;

    public VendingMachineServiceLayerImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;

        this.userBalance = new BigDecimal("0.00");
        this.usersChange = new Change();

    }

    @Override
    public String getName(Item item) throws VendingMachinePersistenceException {
        auditDao.writeAuditEntry("getName");
        return dao.getName(item);
    }

    @Override
    public int getQuantity(Item item) throws VendingMachinePersistenceException {
        auditDao.writeAuditEntry("getQuantity");
        return dao.getQuantity(item);
    }

    @Override
    public BigDecimal getPrice(Item item) throws VendingMachinePersistenceException {
        auditDao.writeAuditEntry("getPrice");
        return dao.getPrice(item);
    }

    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        auditDao.writeAuditEntry("getAllItems ");
        return dao.getAllItems();
    }

    @Override
    public Item getItem(String itemId) throws VendingMachinePersistenceException {
        auditDao.writeAuditEntry("getItem id:" + itemId);
        return dao.getItem(itemId);
    }

    @Override
    public void setQuantity(Item item, int quantity) throws VendingMachinePersistenceException {
        auditDao.writeAuditEntry("setQuantity to " + quantity);
        dao.setQuantity(item, quantity);
    }

    @Override
    public List<Item> getAllAvailableItems() throws VendingMachinePersistenceException {
        // return dao.getAllItems().stream().filter(item -> item.getQuantity() > 0).collect(Collectors.toList());
        auditDao.writeAuditEntry("getAllAvailableItems ");
        return dao.getAllAvailableItems();
    }

    @Override
    public BigDecimal getBalance() throws VendingMachinePersistenceException {
        auditDao.writeAuditEntry("getBalance");
        return this.userBalance;
    }

    @Override
    public void setBalance(BigDecimal balance) throws VendingMachinePersistenceException {
        auditDao.writeAuditEntry("setBalance " + balance.toString());
        this.userBalance = balance;
    }

    @Override
    public void validateFunds(Item item) throws VendingMachineInsufficientFundsException, VendingMachinePersistenceException {
        // see if there are enough funds to make the purchase
        auditDao.writeAuditEntry("validateFunds against  " + item.getPrice().toString());
        if (this.userBalance.compareTo(item.getPrice()) < 0) {
            throw new VendingMachineInsufficientFundsException("Insufficient Funds for this purchase.");
        }

    }

    @Override
    public Item addItem(Item item) throws VendingMachinePersistenceException {
        auditDao.writeAuditEntry("addItem " + item.getName());
        return dao.addItem(item.getItemId(), item);
    }

    @Override
    public Item removeItem(Item item) throws VendingMachinePersistenceException {
        auditDao.writeAuditEntry("removeItem " + item.getName());
        return dao.removeItem(item.getItemId());
    }

    @Override
    public void makeChange(BigDecimal balance) throws ChangeNoChangeDueException, VendingMachinePersistenceException {
        try {
            auditDao.writeAuditEntry("makeChange for" + balance.toString());
        } catch (VendingMachinePersistenceException e) {
            throw new VendingMachinePersistenceException("Error writing audit file");
        }
        usersChange.makeChange(balance);
        if (!changeIsDue()) {
            throw new ChangeNoChangeDueException();
        }
    }

    @Override
    public void dispenseChange() throws VendingMachinePersistenceException {
        auditDao.writeAuditEntry("dispenseChange for  " + userBalance.toString());

        this.userBalance = userBalance.subtract(userBalance);
        this.usersChange.dispenseChange();
    }

    @Override
    public void dispenseItem(Item item) throws VendingMachinePersistenceException {

        try {
            auditDao.writeAuditEntry("dispenseItem " + item.getName());
            dao.setQuantity(item, item.getQuantity() - 1);
        } catch (VendingMachinePersistenceException e) {
            throw new VendingMachinePersistenceException("Error dispensing item");
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
