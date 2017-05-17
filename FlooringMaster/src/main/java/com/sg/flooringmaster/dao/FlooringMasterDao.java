/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmaster.dao;

import com.sg.flooringmaster.dto.Order;
import java.time.LocalDate;

/**
 *
 * @author apprentice
 */
public interface FlooringMasterDao {

    public Order getOrder(LocalDate orderDate, int orderNumber);

    public Order addOrder(LocalDate orderDate, Order newOrder);

    public Order removeOrder(LocalDate orderDate, int orderNumber);

    public boolean getOrdersByDate(String date);

    public void saveAllOrders();

    public int getNextOrderNumber();

}
