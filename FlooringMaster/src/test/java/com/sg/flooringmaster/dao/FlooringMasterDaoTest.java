/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmaster.dao;

import com.sg.flooringmaster.dto.Order;
import com.sg.flooringmaster.dto.Product;
import com.sg.flooringmaster.dto.TaxRate;
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

/**
 *
 * @author apprentice
 */
public class FlooringMasterDaoTest {

    FlooringMasterDao dao = new FlooringMasterDaoFileImpl();

    public FlooringMasterDaoTest() {
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
     * Test of getOrder method, of class FlooringMasterDao.
     */
    @Test
    public void testAddAndGetOrder() {
        Product product = new Product("Steel", new BigDecimal("5.95"), new BigDecimal("8.95"));
        TaxRate taxrate = new TaxRate("Maine", new BigDecimal("7.5"));

        Order newOrder = new Order(10, "custName", taxrate, product, new BigDecimal("100"), new BigDecimal("495.75"), new BigDecimal("1495.75"), new BigDecimal("295.00"), new BigDecimal("1895.00"));

        Order expOrder;
        expOrder = dao.addOrder("testdate", newOrder);
        Assert.assertEquals(expOrder, newOrder);
        expOrder = dao.getOrder("testdate", ((Integer) newOrder.getOrderNumber()).toString());
        Assert.assertEquals(expOrder, newOrder);

    }

    /**
     * Test of removeOrder method, of class FlooringMasterDao.
     */
    @Test
    public void testRemoveOrder() {
        Product product = new Product("Steel", new BigDecimal("5.95"), new BigDecimal("8.95"));
        TaxRate taxrate = new TaxRate("Maine", new BigDecimal("7.5"));

        Order newOrder = new Order(10, "custName", taxrate, product, new BigDecimal("100"), new BigDecimal("495.75"), new BigDecimal("1495.75"), new BigDecimal("295.00"), new BigDecimal("1895.00"));

        Order expOrder;
        expOrder = dao.addOrder("testdate", newOrder);
        Assert.assertEquals(expOrder, newOrder);
        expOrder = dao.getOrder("testdate", ((Integer) newOrder.getOrderNumber()).toString());
        Assert.assertEquals(expOrder, newOrder);

    }

    /**
     * Test of getOrdersByDate method, of class FlooringMasterDao.
     */
    @Test
    public void testGetOrdersByDate() throws Exception {

        Product product = new Product("Steel", new BigDecimal("5.95"), new BigDecimal("8.95"));
        TaxRate taxrate = new TaxRate("Maine", new BigDecimal("7.5"));

        Order newOrder = new Order(10, "custName", taxrate, product, new BigDecimal("100"), new BigDecimal("495.75"), new BigDecimal("1495.75"), new BigDecimal("295.00"), new BigDecimal("1895.00"));
        Order newOrder2 = new Order(11, "custName", taxrate, product, new BigDecimal("100"), new BigDecimal("495.75"), new BigDecimal("1495.75"), new BigDecimal("295.00"), new BigDecimal("1895.00"));
        Order newOrder3 = new Order(12, "custName", taxrate, product, new BigDecimal("100"), new BigDecimal("495.75"), new BigDecimal("1495.75"), new BigDecimal("295.00"), new BigDecimal("1895.00"));

        Order expOrder;

        List<Order> testOrders;

        expOrder = dao.addOrder("testdate", newOrder);
        assertEquals(expOrder, newOrder);
        expOrder = dao.getOrder("testdate", ((Integer) newOrder.getOrderNumber()).toString());
        assertEquals(expOrder, newOrder);

        expOrder = dao.addOrder("testdate", newOrder2);
        expOrder = dao.addOrder("testdate", newOrder3);

        testOrders = dao.getOrdersByDate("testdate");
        assertEquals(3, testOrders.size());

        expOrder = dao.removeOrder("testdate", ((Integer) newOrder2.getOrderNumber()).toString());
        assertNull(dao.getOrder("testdate", ((Integer) expOrder.getOrderNumber()).toString()));

        testOrders = dao.getOrdersByDate("testdate");
        assertEquals(2, testOrders.size());

        expOrder = dao.addOrder("testdate2", newOrder);
        assertEquals(expOrder, newOrder);
        expOrder = dao.getOrder("testdate2", ((Integer) newOrder.getOrderNumber()).toString());
        assertEquals(expOrder, newOrder);

        expOrder = dao.addOrder("testdate2", newOrder2);
        expOrder = dao.addOrder("testdate2", newOrder3);
        testOrders = dao.getOrdersByDate("testdate2");
        assertEquals(3, testOrders.size());

    }

    /**
     * Test of saveAllOrders method, of class FlooringMasterDao.
     */
    @Test
    public void testSaveAllOrders() {
    }

    /**
     * Test of getNextOrderNumber method, of class FlooringMasterDao.
     */
    @Test
    public void testGetNextOrderNumber() {
    }

}
