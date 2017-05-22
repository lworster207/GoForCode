/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmaster.service;

import com.sg.flooringmaster.dao.FlooringMasterDao;
import com.sg.flooringmaster.dao.FlooringMasterDaoFileImpl;
import com.sg.flooringmaster.dao.OrderDay;
import com.sg.flooringmaster.dto.Order;
import com.sg.flooringmaster.dto.Product;
import com.sg.flooringmaster.dto.TaxRate;
import java.math.BigDecimal;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author apprentice
 */
public class FlooringMasterServiceLayerTest {

    FlooringMasterServiceLayer service;
    Product product = new Product("Steel", new BigDecimal("5.95"), new BigDecimal("8.95"));
    TaxRate taxrate = new TaxRate("Maine", new BigDecimal("7.5"));

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

    /**
     * Test of getOrder method, of class FlooringMasterServiceLayer.
     */
    @Test
    public void testGetOrder() {

        Order newOrder = new Order(10, "custName", taxrate, product, new BigDecimal("100"), new BigDecimal("495.75"), new BigDecimal("1495.75"), new BigDecimal("295.00"), new BigDecimal("1895.00"));
        Order expOrder;

        List<Order> testOrders;

        expOrder = service.addOrder("testservicedate", newOrder);

        assertEquals(expOrder, newOrder);

        expOrder = service.getOrder("testservicedate", String.valueOf(newOrder.getOrderNumber()));
        assertEquals(expOrder, newOrder);
    }

    /**
     * Test of addOrder method, of class FlooringMasterServiceLayer.
     */
    @Test
    public void testAddOrder() {

        Order newOrder = new Order(10, "custName", taxrate, product, new BigDecimal("100"), new BigDecimal("495.75"), new BigDecimal("1495.75"), new BigDecimal("295.00"), new BigDecimal("1895.00"));
        Order expOrder;

        List<Order> testOrders;

        expOrder = service.addOrder("testservicedate", newOrder);

        assertEquals(expOrder, newOrder);

    }

    /**
     * Test of getOrderDayByDate method, of class FlooringMasterServiceLayer.
     */
    @Test
    public void testGetOrderDayByDate() throws Exception {

        Order newOrder = new Order(10, "custName", taxrate, product, new BigDecimal("100"), new BigDecimal("495.75"), new BigDecimal("1495.75"), new BigDecimal("295.00"), new BigDecimal("1895.00"));
        Order expOrder;

        List<Order> testOrders;
        OrderDay orderDay;
        OrderDay expOrderday;

        expOrder = service.addOrder("testservicedate", newOrder);

        orderDay = service.getOrderDayByDate("testservicedate");

        assertEquals(expOrder, orderDay.getOrder(String.valueOf(newOrder.getOrderNumber())));

    }

    /**
     * Test of getOrdersByDate method, of class FlooringMasterServiceLayer.
     */
    @Test
    public void testGetOrdersByDate() throws Exception {
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

    }

    /**
     * Test of getAllTaxRates method, of class FlooringMasterServiceLayer.
     */
    @Test
    public void testGetAllTaxRates() {
        List<TaxRate> trList = service.getAllTaxRates();
        assertEquals(trList.size(), 4);
    }

    /**
     * Test of getTaxRate method, of class FlooringMasterServiceLayer.
     */
    @Test
    public void testGetTaxRate() throws Exception {
        TaxRate testTaxRate = service.getTaxRate("IN");

        assertTrue(testTaxRate.getTaxRate().equals(new BigDecimal("6.00")));

    }

    /**
     * Test of getAllProducts method, of class FlooringMasterServiceLayer.
     */
    @Test
    public void testGetAllProducts() {
        List<Product> prList = service.getAllProducts();
        assertEquals(prList.size(), 4);

    }

    /**
     * Test of getProduct method, of class FlooringMasterServiceLayer.
     */
    @Test
    public void testGetProduct() throws Exception {
        Product product = service.getProduct("Wood");
        assertEquals(product.getProductType(), "Wood");
    }

    /**
     * Test of getNextOrderNumber method, of class FlooringMasterServiceLayer.
     */
    @Test
    public void testGetNextOrderNumber() {

        int orderNum = service.getNextOrderNumber();
        int expOrderNum = orderNum + 1;

        assertEquals(expOrderNum, service.getNextOrderNumber());
    }

}
