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

    VendingMachineDao dao = new VendingMachineDaoFileImpl("testProducts.txt");

    public VendingMachineDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws VendingMachinePersistenceException {
        try {
            //dao.getAllItems();

            List<Item> itemList = dao.getAllItems();
            //dao.loadItems();
            for (Item item : itemList) {
                dao.removeItem(item.getItemId());
            }
        } catch (VendingMachinePersistenceException e) {
            // file may not exist
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getName method, of class VendingMachineDao.
     */
    @Test
    public void testGettersAndSetters() throws Exception {

        List<Item> allItemsList;
        String testName;
        int itemCount;
        BigDecimal itemPrice;

        Item item1 = new Item("A1", "Item1", new BigDecimal("1.50"), 1);
        Item item2 = new Item("B2", "Item2", new BigDecimal("1.50"), 1);
        Item item3 = new Item("C3", "Item3", new BigDecimal("1.50"), 1);
        Item item4 = new Item("D4", "Item4", new BigDecimal("1.50"), 1);

        dao.addItem(item1.getItemId(), item1);
        dao.addItem(item2.getItemId(), item2);
        dao.addItem(item3.getItemId(), item3);
        dao.addItem(item4.getItemId(), item4);

        // test getName
        assertEquals("Item3", dao.getItem(item3.getItemId()).getName());

        // test getPrice
        assertEquals(item3.price.compareTo(dao.getItem(item3.getItemId()).getPrice()), 0);

        // test getQuantity
        assertEquals(1, dao.getItem(item3.getItemId()).getQuantity());

        // test setName
        item1.setName("item1TestName");
        assertEquals("item1TestName", item1.getName());

        // test setPrice
        itemPrice = new BigDecimal("2.00");
        item2.setPrice(itemPrice);
        assertEquals(itemPrice.compareTo(item2.getPrice()), 0);

        //test setQuantity
        item3.setQuantity(3);
        assertEquals(3, item3.getQuantity());

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

    @Test
    public void testGetAllAvailableItems() throws Exception {
        // set one of the item quantities to zero.

        Item item1 = new Item("A1", "Item1", new BigDecimal("1.50"), 1);
        Item item2 = new Item("B2", "Item2", new BigDecimal("1.50"), 1);
        Item item3 = new Item("C3", "Item3", new BigDecimal("1.50"), 0);
        Item item4 = new Item("D4", "Item4", new BigDecimal("1.50"), 1);

        List<Item> allItemsList;

        //assertEquals(0, allItemsList.size());
        dao.addItem(item1.getItemId(), item1);
        dao.addItem(item2.getItemId(), item2);
        dao.addItem(item3.getItemId(), item3);
        dao.addItem(item4.getItemId(), item4);
        allItemsList = dao.getAllAvailableItems();
        assertEquals(3, allItemsList.size());
    }

    @Test
    public void testGetAllAvailableItemsNoItemsAvailable() throws Exception {
        // set all of the item quantities to zero. simulates machine is sold out

        Item item1 = new Item("A1", "Item1", new BigDecimal("1.50"), 0);
        Item item2 = new Item("B2", "Item2", new BigDecimal("1.50"), 0);
        Item item3 = new Item("C3", "Item3", new BigDecimal("1.50"), 0);
        Item item4 = new Item("D4", "Item4", new BigDecimal("1.50"), 0);

        List<Item> allItemsList;

        //assertEquals(0, allItemsList.size());
        dao.addItem(item1.getItemId(), item1);
        dao.addItem(item2.getItemId(), item2);
        dao.addItem(item3.getItemId(), item3);
        dao.addItem(item4.getItemId(), item4);
        allItemsList = dao.getAllAvailableItems();
        assertEquals(0, allItemsList.size());
    }

    /**
     * Test of getAllItems method, of class VendingMachineDao.
     */
    @Test
    public void testGetAllItems() throws Exception {
        Item item1 = new Item("A1", "Item1", new BigDecimal("1.50"), 1);
        Item item2 = new Item("B2", "Item2", new BigDecimal("1.50"), 1);
        Item item3 = new Item("C3", "Item3", new BigDecimal("1.50"), 1);
        Item item4 = new Item("D4", "Item4", new BigDecimal("1.50"), 1);

        List<Item> allItemsList = dao.getAllItems();

        //assertEquals(0, allItemsList.size());
        dao.addItem(item1.getItemId(), item1);
        allItemsList = dao.getAllItems();
        assertEquals(1, allItemsList.size());

        dao.addItem(item2.getItemId(), item2);
        dao.addItem(item3.getItemId(), item3);
        dao.addItem(item4.getItemId(), item4);
        allItemsList = dao.getAllItems();
        assertEquals(4, allItemsList.size());

    }

    /**
     * Test of getItem and addItem methods of class VendingMachineDao.
     */
    @Test
    public void testAddGetItem() throws Exception {
        Item item = new Item("A1", "Item1", new BigDecimal("1.50"), 1);

        dao.addItem(item.getItemId(), item);

        Item fromDao = dao.getItem(item.getItemId());

        assertEquals(item, fromDao);
    }

    @Test
    public void testRemoveItem() throws Exception {
        Item item1 = new Item("A1", "Item1", new BigDecimal("1.50"), 1);
        Item item2 = new Item("B2", "Item2", new BigDecimal("1.50"), 1);
        Item item3 = new Item("C3", "Item3", new BigDecimal("1.50"), 1);
        Item item4 = new Item("D4", "Item4", new BigDecimal("1.50"), 1);

        Item removedItem;

        //assertEquals(0, allItemsList.size());
        dao.addItem(item1.getItemId(), item1);
        dao.addItem(item2.getItemId(), item2);
        dao.addItem(item3.getItemId(), item3);
        dao.addItem(item4.getItemId(), item4);

        List<Item> allItemsList = dao.getAllItems();

        assertEquals(4, allItemsList.size());
        removedItem = dao.removeItem(item1.getItemId());
        assertEquals(item1, removedItem);

        allItemsList = dao.getAllItems();
        assertEquals(3, allItemsList.size());

    }

}
