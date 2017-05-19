/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmaster.dao;

import com.sg.flooringmaster.dto.Order;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface FlooringMasterDao {

    public Order getOrder(String orderDate, String orderNumber);

    public Order addOrder(String orderDate, Order newOrder);

    public Order removeOrder(String orderDate, String orderNumber);

    public OrderDay getOrderDayByDate(String date);

    public List<Order> getOrdersByDate(String date) throws FlooringMasterNoOrdersForDateException;

    public void saveAllOrders();

    public int getNextOrderNumber();

}
