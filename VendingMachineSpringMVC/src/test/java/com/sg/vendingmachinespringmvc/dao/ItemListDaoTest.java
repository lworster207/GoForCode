/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.Item;
import java.math.BigDecimal;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class ItemListDaoTest {

    private ItemListDao dao;

    public ItemListDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("itemListDao", ItemListDao.class);
        dao.addItem("1", new Item("1", "Doritos", new BigDecimal("1.25"), 5));
        dao.addItem("2", new Item("2", "Lays", new BigDecimal("1.25"), 7));
        dao.addItem("3", new Item("3", "Snickers", new BigDecimal("1.50"), 2));
        dao.addItem("4", new Item("4", "Milky Way", new BigDecimal("1.25"), 1));
        dao.addItem("5", new Item("5", "Three Musketeers", new BigDecimal("1.50"), 6));
        dao.addItem("6", new Item("6", "Lays BBQ", new BigDecimal("1.25"), 3));
        dao.addItem("7", new Item("7", "M&M Plain", new BigDecimal("1.75"), 10));
        dao.addItem("8", new Item("8", "Milk Duds", new BigDecimal("1.65"), 5));
        dao.addItem("9", new Item("9", "M&M Peanut", new BigDecimal("1.75"), 5));
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getName method, of class ItemListDao.
     */
    @Test
    public void testGetName() throws Exception {

        Item expItem = dao.addItem("1", new Item("1", "Doritos", new BigDecimal("1.25"), 5));

        Assert.assertEquals("Doritos", dao.getItem("1").getName());

    }

    /**
     * Test of getAllItems method, of class ItemListDao.
     */
    @Test
    public void testGetAllItems() throws Exception {

        dao.addItem("1", new Item("1", "Doritos", new BigDecimal("1.25"), 5));
        dao.addItem("2", new Item("2", "Lays", new BigDecimal("1.25"), 7));
        dao.addItem("3", new Item("3", "Snickers", new BigDecimal("1.50"), 2));
        dao.addItem("4", new Item("4", "Milky Way", new BigDecimal("1.25"), 1));
        dao.addItem("5", new Item("5", "Three Musketeers", new BigDecimal("1.50"), 6));
        dao.addItem("6", new Item("6", "Lays BBQ", new BigDecimal("1.25"), 3));
        dao.addItem("7", new Item("7", "M&M Plain", new BigDecimal("1.75"), 10));
        dao.addItem("8", new Item("8", "Milk Duds", new BigDecimal("1.65"), 4));
        dao.addItem("9", new Item("9", "M&M Peanut", new BigDecimal("1.75"), 7));

        List<Item> result = dao.getAllItems();

        Assert.assertEquals(9, result.size());

    }

    /**
     * Test of getAllAvailableItems method, of class ItemListDao.
     */
    @Test
    public void testGetAllAvailableItems() throws Exception {

        dao.setQuantity(dao.getItem("8"), 0);
        dao.setQuantity(dao.getItem("9"), 0);
        List<Item> result = dao.getAllAvailableItems();

        assertEquals(7, result.size());
    }

    /**
     * Test of getItem method, of class ItemListDao.
     */
    @Test
    public void testGetItem() throws Exception {
        Item expected = dao.addItem("10", new Item("10", "M&M Peanut Butter", new BigDecimal("1.75"), 7));
        Item result = dao.getItem("10");
        assertEquals(expected, result);
    }

    /**
     * Test of setQuantity method, of class ItemListDao.
     */
    @Test
    public void testSetQuantity() throws Exception {
        dao.setQuantity(dao.getItem("8"), 0);
        assertEquals(0, dao.getItem("8").getQuantity());

        dao.setQuantity(dao.getItem("8"), 20);
        assertEquals(20, dao.getItem("8").getQuantity());
    }

    /**
     * Test of getQuantity method, of class ItemListDao.
     */
    @Test
    public void testGetQuantity() throws Exception {
        dao.setQuantity(dao.getItem("8"), 0);
        assertEquals(0, dao.getItem("8").getQuantity());

        dao.setQuantity(dao.getItem("8"), 20);
        assertEquals(20, dao.getItem("8").getQuantity());
    }

    /**
     * Test of getPrice method, of class ItemListDao.
     */
    @Test
    public void testGetPrice() throws Exception {
        Item expected = dao.addItem("10", new Item("10", "M&M Peanut Butter", new BigDecimal("1.75"), 7));
        assertEquals(dao.getItem("10").getPrice(), new BigDecimal("1.75"));

    }

    /**
     * Test of addItem method, of class ItemListDao.
     */
    @Test
    public void testAddItem() throws Exception {
        Item expected = dao.addItem("10", new Item("10", "M&M Peanut Butter", new BigDecimal("1.75"), 7));
        Item result = dao.getItem("10");
        assertEquals(expected, result);
    }

    /**
     * Test of removeItem method, of class ItemListDao.
     */
    @Test
    public void testRemoveItem() throws Exception {

        Item expected = dao.addItem("10", new Item("10", "M&M Peanut Butter", new BigDecimal("1.75"), 7));
        Item result = dao.getItem("10");
        assertEquals(expected, result);

        dao.removeItem("10");
        result = dao.getItem("10");
        assertNull(result);
    }

}
