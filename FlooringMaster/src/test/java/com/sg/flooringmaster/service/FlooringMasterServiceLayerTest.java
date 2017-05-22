/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmaster.service;

import com.sg.flooringmaster.dao.FlooringMasterDao;
import com.sg.flooringmaster.dao.FlooringMasterDaoFileImpl;
import com.sg.flooringmaster.dto.Order;
import com.sg.flooringmaster.dto.Product;
import com.sg.flooringmaster.dto.TaxRate;
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
public class FlooringMasterServiceLayerTest {

    FlooringMasterServiceLayer service;

    public FlooringMasterServiceLayerTest() {
        FlooringMasterDao dao = new FlooringMasterDaoFileImpl();

        service = new FlooringMasterServiceLayerImpl(dao);
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
     * Test of getOrder method, of class FlooringMasterServiceLayer.
     */
    @Test
    public void testAddAndGetOrder() {
        Product product = new Product("Steel", new BigDecimal("5.95"), new BigDecimal("8.95"));
        TaxRate taxrate = new TaxRate("Maine", new BigDecimal("7.5"));

        Order newOrder = new Order(10, "custName", taxrate, product, new BigDecimal("100"), new BigDecimal("495.75"), new BigDecimal("1495.75"), new BigDecimal("295.00"), new BigDecimal("1895.00"));
        Order newOrder2 = new Order(11, "custName", taxrate, product, new BigDecimal("100"), new BigDecimal("495.75"), new BigDecimal("1495.75"), new BigDecimal("295.00"), new BigDecimal("1895.00"));
        Order newOrder3 = new Order(12, "custName", taxrate, product, new BigDecimal("100"), new BigDecimal("495.75"), new BigDecimal("1495.75"), new BigDecimal("295.00"), new BigDecimal("1895.00"));

        Order expOrder;

        List<Order> testOrders;

        expOrder = service.addOrder("testservicedate", newOrder);

        assertEquals(expOrder, newOrder);

    }

    /**
     * Test of removeOrder method, of class FlooringMasterServiceLayer.
     */
    @Test
    public void testRemoveOrder() throws Exception {
        Product product = new Product("Steel", new BigDecimal("5.95"), new BigDecimal("8.95"));
        TaxRate taxrate = new TaxRate("Maine", new BigDecimal("7.5"));

        Order newOrder = new Order(10, "custName", taxrate, product, new BigDecimal("100"), new BigDecimal("495.75"), new BigDecimal("1495.75"), new BigDecimal("295.00"), new BigDecimal("1895.00"));
        Order newOrder2 = new Order(11, "custName", taxrate, product, new BigDecimal("100"), new BigDecimal("495.75"), new BigDecimal("1495.75"), new BigDecimal("295.00"), new BigDecimal("1895.00"));
        Order newOrder3 = new Order(12, "custName", taxrate, product, new BigDecimal("100"), new BigDecimal("495.75"), new BigDecimal("1495.75"), new BigDecimal("295.00"), new BigDecimal("1895.00"));

        Order expOrder;

        List<Order> testOrders;

        expOrder = service.addOrder("testservicedate", newOrder);
        expOrder = service.addOrder("testservicedate", newOrder2);
        expOrder = service.addOrder("testservicedate", newOrder3);

        testOrders = service.getOrdersByDate("testservicedate");

        assertEquals(3, testOrders.size());
        Integer orderNumber = newOrder.getOrderNumber();
        service.removeOrder("testservicedate", orderNumber.toString());

        testOrders = service.getOrdersByDate("testservicedate");
        assertEquals(2, testOrders.size());

    }

    /**
     * Test of getOrderDayByDate method, of class FlooringMasterServiceLayer.
     */
    /**
     * Test of saveAllOrders method, of class FlooringMasterServiceLayer.
     */
    @Test
    public void testSaveAllOrders() {
    }

}
