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
import com.sg.flooringmaster.ui.UserIONoValueException;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class FlooringMasterController {

    private FlooringMasterView view;
    private FlooringMasterServiceLayer service;

    public FlooringMasterController(FlooringMasterServiceLayer service, FlooringMasterView view, FlooringMasterServiceLayer stubService, String env) {
        this.view = view;
        if (env.toLowerCase().equals("production")) {
            this.service = service;
        } else {
            this.service = stubService;
        }

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
        String date = null;
        List<Order> orderList = null;
        Order orderToEdit = null;
        Integer orderNumber;

        try {
            date = view.getDate();

            orderList = service.getOrdersByDate(date);
            view.displayOrders(orderList);

            orderNumber = view.getOrderNumber(orderList);
            orderToEdit = service.getOrder(date, orderNumber.toString());

            editTheOrder(date, orderToEdit);

        } catch (FlooringMasterNoOrdersForDateException | UserIONoValueException e) {
            System.out.println(e.getClass().getName());
            if (e.getClass().getName().contains("FlooringMasterNoOrdersForDateException")) {
                view.displayErrorMessage("No orders found for date: " + date);
            }
        }

    }

    public void removeOrder() {
        String date = null;
        List<Order> orderList = null;
        Order orderToRemove = null;

        Integer orderNumber;

        try {
            date = view.getDate();
            orderList = service.getOrdersByDate(date);
            view.displayOrders(orderList);

            orderNumber = view.getOrderNumber(orderList);
            orderToRemove = service.getOrder(date, orderNumber.toString());
            view.displayOrder(orderToRemove);
            String confirmation = view.getConfirmation("Remove this order?");
            if (confirmation.toLowerCase().equals("y")) {
                orderToRemove = service.removeOrder(date, orderNumber.toString());
            }
        } catch (FlooringMasterNoOrdersForDateException | UserIONoValueException e) {
            System.out.println(e.getClass().getName());
            if (e.getClass().getName().contains("FlooringMasterNoOrdersForDateException")) {
                view.displayErrorMessage("No orders found for date: " + date);
            }
        }

    }

    public void createOrder() {

        Order order = new Order();
        Boolean invalidData = true;

        int orderNumber = service.getNextOrderNumber();

        Product product = null;
        TaxRate taxrate = null;

        BigDecimal area = null;

        String customerName = null;
        String prodType = null;
        String state = null;
        String date = "testAdddate";

        List<TaxRate> taxRates = service.getAllTaxRates();
        List<Product> allProducts = service.getAllProducts();

        invalidData = true;
        while (invalidData) {
            try {
                date = view.getDate();
                System.out.println("date: " + date);
                invalidData = false;
            } catch (UserIONoValueException e) {
                view.displayErrorMessage("You must enter a value for date.");
            }
        }

        invalidData = true;
        while (invalidData) {
            try {
                customerName = view.getCustomerName();
                invalidData = false;
            } catch (UserIONoValueException e) {
                view.displayErrorMessage("You must enter a value for name.");
            }
        }

        // get the product type
        invalidData = true;
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

        invalidData = true;
        while (invalidData) {
            area = view.getArea();
            invalidData = false;
        }

        Order newOrder = new Order(orderNumber, customerName, taxrate, product, area);

        view.displayOrder(newOrder);

        String confirmation = view.getConfirmation("Save this order?");
        if (confirmation.toLowerCase().equals("y")) {

            newOrder = service.addOrder(date, newOrder);

            view.displayBanner("NewOrder:" + newOrder.toString());

            //order = view.createNewOrder(taxRates, allProducts);
            // List<Product> products                = Order newOrder = view.getNewOrder();
        }

    }

    public void editTheOrder(String orderDate, Order order) {

        Boolean invalidData = true;

        Integer orderNumber = order.getOrderNumber();

        Product product = null;
        TaxRate taxrate = null;

        BigDecimal area;

        String customerName = null;
        String prodType = null;
        String state = null;
        String editDate = "testAdddate";

        List<TaxRate> taxRates = service.getAllTaxRates();
        List<Product> allProducts = service.getAllProducts();

        editDate = view.getEditDate(orderDate);

        customerName = view.getEditCustomerName(order);

        // get the product type
        while (invalidData) {
            try {
                prodType = view.getEditProductTypeOption(order, allProducts);
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
                state = view.getEditStateOption(order, taxRates);
                taxrate = service.getTaxRate(state);
                invalidData = false;
            } catch (TaxRateNotFoundException e) {
                view.displayErrorMessage(e.getMessage());
            }
        }

        area = view.getEditArea(order);

        Order newOrder = new Order(orderNumber, customerName, taxrate, product, area);

        view.displayOrder(newOrder);

        String confirmation = view.getConfirmation("Save changes?");
        if (confirmation.toLowerCase().equals("y")) {
            service.removeOrder(orderDate, orderNumber.toString());
            newOrder = service.addOrder(editDate, newOrder);
            // saveChanges();
        }
//        view.displayBanner("NewOrder: " + editDate + " | " + newOrder.toString());
    }

    public void displayOrders() {
        // display the orders for a specific date
        String date;
        try {
            date = view.getDate();

            List<Order> orderList = service.getOrdersByDate(date);
            view.displayOrders(orderList);
        } catch (FlooringMasterNoOrdersForDateException | UserIONoValueException e) {
            view.displayErrorMessage(e.getMessage());
        }

    }

}
