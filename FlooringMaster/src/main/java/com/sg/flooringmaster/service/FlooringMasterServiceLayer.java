/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmaster.service;

import com.sg.flooringmaster.dao.FlooringMasterNoOrdersForDateException;
import com.sg.flooringmaster.dao.OrderDay;
import com.sg.flooringmaster.dao.TaxRateNotFoundException;
import com.sg.flooringmaster.dto.Order;
import com.sg.flooringmaster.dto.Product;
import com.sg.flooringmaster.dto.ProductNotFoundException;
import com.sg.flooringmaster.dto.TaxRate;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface FlooringMasterServiceLayer {

    public Order getOrder(String ld, String orderNumber);

    public Order addOrder(String ld, Order order);

    public Order removeOrder(String ld, String orderNumber);

    public OrderDay getOrderDayByDate(String date) throws FlooringMasterNoOrdersForDateException;

    public List<Order> getOrdersByDate(String date) throws FlooringMasterNoOrdersForDateException;

    public List<TaxRate> getAllTaxRates();

    public TaxRate getTaxRate(String state) throws TaxRateNotFoundException;

    public List<Product> getAllProducts();

    public Product getProduct(String productType) throws ProductNotFoundException;

    public void saveAllOrders();

    public int getNextOrderNumber();
}
