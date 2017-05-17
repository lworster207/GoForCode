/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmaster.service;

import com.sg.flooringmaster.dto.Order;
import java.time.LocalDate;

/**
 *
 * @author apprentice
 */
public interface FlooringMasterServiceLayer {

    public Order getOrder(LocalDate ld, int orderNumber);

    public Order addOrder(Order order);

    public Order removeOrder(LocalDate ld, int orderNumber);

    public void getOrdersByDate(String date);

    public void saveAllOrders();
}
