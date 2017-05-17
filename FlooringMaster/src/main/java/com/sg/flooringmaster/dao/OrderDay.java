/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmaster.dao;

import com.sg.flooringmaster.dto.Order;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author apprentice
 */
public class OrderDay {

    HashMap<String, Order> orderDay = new HashMap<>();

    public Order addOrder(String orderNumber, Order order) {
        orderDay.put(orderNumber, order);
        return orderDay.get(orderNumber);
    }

    public Order getOrder(String orderNumber) {
        Order returnOrder = null;
        // Integer key = orderNumber;

        if (orderDay.keySet().contains(orderNumber)) {
            returnOrder = orderDay.get(orderNumber);
        }
        return returnOrder;
    }

    public Order removeOrder(String orderNumber) {
        Order returnOrder = null;
        // Integer key = orderNumber;

        if (orderDay.keySet().contains(orderNumber)) {
            returnOrder = orderDay.remove(orderNumber);
        }
        return returnOrder;
    }

    public List<Order> getAllOrders() {
        //        return (items.values().stream().filter(item -> item.getQuantity() > 0).collect(Collectors.toList()));
        List<Order> retList = orderDay.values().stream().collect(Collectors.toList());
        return retList;
    }

    public int getSize() {
        return orderDay.size();
    }
}
