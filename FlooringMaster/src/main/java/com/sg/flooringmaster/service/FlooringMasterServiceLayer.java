/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmaster.service;

import com.sg.flooringmaster.dao.OrderDay;
import com.sg.flooringmaster.dto.Order;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface FlooringMasterServiceLayer {

    public Order getOrder(String ld, String orderNumber);

    public Order addOrder(String ld, Order order);

    public Order removeOrder(String ld, String orderNumber);

    public OrderDay getOrderDayByDate(String date);

    public List<Order> getOrdersByDate(String date);

    public void saveAllOrders();
}
