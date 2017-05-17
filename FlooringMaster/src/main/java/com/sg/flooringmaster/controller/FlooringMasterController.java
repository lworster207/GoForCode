/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmaster.controller;

import com.sg.flooringmaster.dto.Order;
import com.sg.flooringmaster.service.FlooringMasterServiceLayer;
import com.sg.flooringmaster.ui.FlooringMasterView;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class FlooringMasterController {

    private FlooringMasterView view;
    private FlooringMasterServiceLayer service;

    public FlooringMasterController(FlooringMasterServiceLayer service, FlooringMasterView view) {
        this.view = view;
        this.service = service;

    }

    public void run() {
        Order order;
        Order order2;

        boolean keepGoing = true;
        int option = 0;

        while (keepGoing) {
            option = view.displayMenuAndGetOption();
            switch (option) {
                case 1:
                    // display orders
                    displayOrders();
                    break;
                case 2:
                    // add orders
                    addOrder();
                    break;
                case 3:
                    // edit order
                    break;
                case 4:
                    // remove order
                    break;
                case 5:
                    // save all changes
                    break;
                case 6:
                    // quit
                    keepGoing = false;
                    break;
            }

        }

    }

    public void addOrder() {
        List<Product> products
                = Order newOrder = view.getNewOrder();

    }

    public void displayOrders() {
        // display the orders for a specific date
        String date = view.getDate();
        List<Order> orderList = service.getOrdersByDate(date);
        view.displayOrders(orderList);

    }
}
