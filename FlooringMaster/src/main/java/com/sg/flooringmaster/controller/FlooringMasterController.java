/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmaster.controller;

import com.sg.flooringmaster.dao.FlooringMasterNoOrdersForDateException;
import com.sg.flooringmaster.dao.TaxRateNotFoundException;
import com.sg.flooringmaster.dto.Order;
import com.sg.flooringmaster.dto.Product;
import com.sg.flooringmaster.dto.ProductNotFoundException;
import com.sg.flooringmaster.dto.TaxRate;
import com.sg.flooringmaster.service.FlooringMasterServiceLayer;
import com.sg.flooringmaster.ui.FlooringMasterView;
import java.math.BigDecimal;
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
                    createOrder();
                    break;
                case 4:
                    // edit order
                    editOrder();
                    break;
                case 3:
                    // remove order
                    removeOrder();
                    break;
                case 5:
                    // save all changes
                    saveChanges();
                    break;
                case 6:
                    // quit
                    keepGoing = false;
                    break;
            }

        }
    }

    public void saveChanges() {
        service.saveAllOrders();
    }

    public void editOrder() {
        String date = view.getDate();
        List<Order> orderList = null;
        Order orderToEdit = null;

        Integer orderNumber;

        try {
            orderList = service.getOrdersByDate(date);
            view.displayOrders(orderList);

            orderNumber = view.getOrderNumber(orderList);
            orderToEdit = service.getOrder(date, orderNumber.toString());

        } catch (FlooringMasterNoOrdersForDateException e) {
            view.displayErrorMessage("No orders found for date: " + date);
        }

    }

    public void removeOrder() {
        String date = view.getDate();
        List<Order> orderList = null;
        Order orderToRemove = null;

        Integer orderNumber;

        try {
            orderList = service.getOrdersByDate(date);
            view.displayOrders(orderList);

            orderNumber = view.getOrderNumber(orderList);
            orderToRemove = service.removeOrder(date, orderNumber.toString());

        } catch (FlooringMasterNoOrdersForDateException e) {
            view.displayErrorMessage("No orders found for date: " + date);
        }

    }

    public void createOrder() {

        Order order = new Order();
        Boolean invalidData = true;

        int orderNumber = service.getNextOrderNumber();

        Product product = null;
        TaxRate taxrate = null;

        BigDecimal area;

        String customerName = null;
        String prodType = null;
        String state = null;
        String date = "testAdddate";

        List<TaxRate> taxRates = service.getAllTaxRates();
        List<Product> allProducts = service.getAllProducts();

        customerName = view.getCustomerName();

        // get the product type
        while (invalidData) {
            try {
                prodType = view.getProductTypeOption(allProducts);
                product = service.getProduct(prodType);
                invalidData = false;
            } catch (ProductNotFoundException e) {
                view.displayErrorMessage(e.getMessage());
            }
        }

        // get the taxrate
        invalidData = true;
        while (invalidData) {
            try {
                state = view.getStateOption(taxRates);
                taxrate = service.getTaxRate(state);
                invalidData = false;
            } catch (TaxRateNotFoundException e) {
                view.displayErrorMessage(e.getMessage());
            }
        }

        area = view.getArea();

        Order newOrder = new Order(orderNumber, customerName, taxrate, product, area);
        newOrder = service.addOrder(date, newOrder);

        view.displayBanner("NewOrder:" + newOrder.toString());

        //order = view.createNewOrder(taxRates, allProducts);
        // List<Product> products                = Order newOrder = view.getNewOrder();
    }

    public void displayOrders() {
        // display the orders for a specific date
        String date = view.getDate();
        try {
            List<Order> orderList = service.getOrdersByDate(date);
            view.displayOrders(orderList);
        } catch (FlooringMasterNoOrdersForDateException e) {
            view.displayErrorMessage("No orders found for date: " + date);
        }

    }

}
