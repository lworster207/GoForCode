/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author apprentice
 */
public class VendingMachineDaoTest {

    VendingMachineDaoFileImpl dao = new VendingMachineDaoFileImpl("testProducts.txt");

    public VendingMachineDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws Exception {
        try {
            dao.loadItems();
        } catch (VendingMachinePersistenceException e) {
            // file may not exist
        }
        List<Item> itemList = dao.getAllItems();
        //dao.loadItems();
        for (Item item : itemList) {
            dao.removeItem(item.getItemId());
        }

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getName method, of class VendingMachineDao.
     */
    @Test
    public void testGetName() {
    }

    /**
     * Test of getQuantity method, of class VendingMachineDao.
     */
    @Test
    public void testGetQuantity() {
    }

    /**
     * Test of getPrice method, of class VendingMachineDao.
     */
    @Test
    public void testGetPrice() {
    }

    /**
     * Test of getAllItems method, of class VendingMachineDao.
     */
    @Test
    public void testGetAllItems() throws Exception {
        Item item1 = new Item("1", "Item1", new BigDecimal("1.50"), 1);
        Item item2 = new Item("2", "Item2", new BigDecimal("1.50"), 1);
        Item item3 = new Item("3", "Item3", new BigDecimal("1.50"), 1);
        Item item4 = new Item("4", "Item4", new BigDecimal("1.50"), 1);

        List<Item> allItemsList = dao.getAllItems();

        //assertEquals(0, allItemsList.size());
        dao.addItem(item1.getItemId(), item1);
        assertEquals(1, allItemsList.size());

        dao.addItem(item2.getItemId(), item2);
        dao.addItem(item3.getItemId(), item3);
        dao.addItem(item4.getItemId(), item4);

        assertEquals(4, allItemsList.size());

    }

    /**
     * Test of getItem method, of class VendingMachineDao.
     */
    @Test
    public void testAddGetItem() throws Exception {
        Item item = new Item("1", "Item1", new BigDecimal("1.50"), 1);

        dao.addItem(item.getItemId(), item);

        Item fromDao = dao.getItem(item.getItemId());

        assertEquals(item, fromDao);
    }

    @Test
    public void testRemoveItem() throws Exception {
        Item item1 = new Item("1", "Item1", new BigDecimal("1.50"), 1);
        Item item2 = new Item("2", "Item2", new BigDecimal("1.50"), 1);
        Item item3 = new Item("3", "Item3", new BigDecimal("1.50"), 1);
        Item item4 = new Item("4", "Item4", new BigDecimal("1.50"), 1);

        Item removedItem;

        //assertEquals(0, allItemsList.size());
        dao.addItem(item1.getItemId(), item1);
        dao.addItem(item2.getItemId(), item2);
        dao.addItem(item3.getItemId(), item3);
        dao.addItem(item4.getItemId(), item4);

        List<Item> allItemsList = dao.getAllItems();

        assertEquals(4, allItemsList.size());
        removedItem = dao.removeItem("1");
        assertEquals(item1, removedItem);

        assertEquals(3, allItemsList.size());

    }

}
