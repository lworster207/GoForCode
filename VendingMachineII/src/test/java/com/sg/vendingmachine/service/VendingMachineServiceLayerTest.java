/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.vendingmachine.dao.VendingMachineAuditDaoStubImpl;
import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachineDaoFileImpl;
import com.sg.vendingmachine.dao.VendingMachineInsufficientFundsException;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author apprentice
 */
public class VendingMachineServiceLayerTest {

    private VendingMachineServiceLayer service;

    public VendingMachineServiceLayerTest() {
        VendingMachineDao dao = new VendingMachineDaoFileImpl("testProducts.txt");
        VendingMachineAuditDao auditDao = new VendingMachineAuditDaoStubImpl();

        service = new VendingMachineServiceLayerImpl(dao, auditDao);
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getItem method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testGetItem() throws Exception {

        Item item = new Item("SL1", "SLItem1", new BigDecimal("1.50"), 1);
        service.addItem(item);

        Assert.assertEquals(item, service.getItem(item.getItemId()));

    }

    /**
     * Test of getAllItems method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testGetAllItems() throws Exception {
        Item item1 = new Item("A1", "Item1", new BigDecimal("1.50"), 1);
        Item item2 = new Item("B2", "Item2", new BigDecimal("1.50"), 1);
        Item item3 = new Item("C3", "Item3", new BigDecimal("1.50"), 1);
        Item item4 = new Item("D4", "Item4", new BigDecimal("1.50"), 1);

        List<Item> allItemsList;

        //assertEquals(0, allItemsList.size());
        service.addItem(item1);
        allItemsList = service.getAllItems();
        assertEquals(1, allItemsList.size());

        service.addItem(item2);
        service.addItem(item3);
        service.addItem(item4);
        allItemsList = service.getAllItems();
        assertEquals(4, allItemsList.size());

    }

    /**
     * Test of getAllAvailableItems method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testGetAllAvailableItems() throws Exception {
        Item item1 = new Item("A1", "Item1", new BigDecimal("1.50"), 1);
        Item item2 = new Item("B2", "Item2", new BigDecimal("1.50"), 0);
        Item item3 = new Item("C3", "Item3", new BigDecimal("1.50"), 1);
        Item item4 = new Item("D4", "Item4", new BigDecimal("1.50"), 1);

        List<Item> allItemsList;

        //assertEquals(0, allItemsList.size());
        service.addItem(item1);
        allItemsList = service.getAllAvailableItems();
        assertEquals(1, allItemsList.size());

        service.addItem(item2);
        service.addItem(item3);
        service.addItem(item4);
        allItemsList = service.getAllAvailableItems();
        assertEquals(3, allItemsList.size());
    }

    /**
     * Test of dispenseItem method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testDispenseItem() throws Exception {
        Item item1 = new Item("A1", "Item1", new BigDecimal("1.50"), 1);
        Item item2 = new Item("B2", "Item2", new BigDecimal("1.50"), 2);

        service.addItem(item1);
        service.addItem(item2);

        try {
            service.dispenseItem(item1);
            assertEquals(service.getItem(item1.getItemId()).getQuantity(), 0);

            service.dispenseItem(item2);
            assertEquals(service.getItem(item2.getItemId()).getQuantity(), 1);
        } catch (VendingMachinePersistenceException e) {
            assertTrue(false);
        }
    }

    /**
     * Test of validateFunds method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testValidateFunds() throws Exception {
        BigDecimal balance;
        Item item1 = new Item("A1", "Item1", new BigDecimal("1.50"), 1);
        Item item2 = new Item("B2", "Item2", new BigDecimal("1.50"), 0);
        Item item3 = new Item("C3", "Item3", new BigDecimal("1.50"), 1);
        Item item4 = new Item("D4", "Item4", new BigDecimal("1.50"), 1);

        List<Item> allItemsList;

        //assertEquals(0, allItemsList.size());
        service.addItem(item1);
        service.addItem(item2);

        balance = new BigDecimal("2.00");
        service.setBalance(balance);

        try {
            service.validateFunds(item2);
            assertTrue(true);
        } catch (VendingMachineInsufficientFundsException e) {
            assertTrue(false);
        }

        balance = item1.getPrice().subtract(new BigDecimal("0.05"));
        service.setBalance(balance);

        try {
            service.validateFunds(item1);
            assertTrue(false);
        } catch (VendingMachineInsufficientFundsException e) {
            assertTrue(true);
        }

    }

    /**
     * Test of getBalance method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testGetBalance() {
        BigDecimal balance;

        balance = new BigDecimal("2.00");

        service.setBalance(balance);

        assertEquals(balance.compareTo(service.getBalance()), 0);

    }

    /**
     * Test of setBalance method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testSetBalance() {
        BigDecimal balance;

        balance = new BigDecimal("2.00");

        service.setBalance(balance);

        assertEquals(balance.compareTo(service.getBalance()), 0);

    }

}
