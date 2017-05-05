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
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author apprentice
 */
public class VendingMachineDaoTest {

    public VendingMachineDaoTest() {
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
    public void testGetAllItems() {
    }

    /**
     * Test of getItem method, of class VendingMachineDao.
     */
    @Test
    public void testGetItem() {
    }

    /**
     * Test of setQuantity method, of class VendingMachineDao.
     */
    @Test
    public void testSetQuantity() {
    }

    public class VendingMachineDaoImpl implements VendingMachineDao {

        public String getName(Item item) {
            return "";
        }

        public int getQuantity(Item item) {
            return 0;
        }

        public BigDecimal getPrice(Item item) {
            return null;
        }

        public List<Item> getAllItems() {
            return null;
        }

        public Item getItem(String itemId) {
            return null;
        }

        public void setQuantity(Item item, int quantity) {
        }
    }

}
