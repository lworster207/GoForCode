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

    private boolean changesToSave;

    public FlooringMasterController(FlooringMasterServiceLayer service, FlooringMasterView view, FlooringMasterServiceLayer stubService, String env) {
        // allow the service to be determined at runtime - based on the env argument value.
        this.view = view;
        if (env.toLowerCase().equals("production")) {
            this.service = service;
        } else {
            this.service = stubService;
        }
        changesToSave = false;

    }

    public void run() {
        // run until exit is chosen

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
                    exitApp();
                    keepGoing = false;
                    break;
            }

        }
    }

    public void exitApp() {
        if (changesToSave) {
            String confirmation = view.getConfirmation("You have unsaved changes, do you want to save them?");
            if (confirmation.toLowerCase().equals("y")) {
                saveChanges();
            }
        }
    }

    public void saveChanges() {
        // save All the Orders at their current state.
        service.saveAllOrders();
        changesToSave = false;
    }

    public void editOrder() {
        // allow changes to an existing order.
        String date = null;
        List<Order> orderList = null;
        Order orderToEdit = null;
        Integer orderNumber;

        try {
            // get the Order date
            date = view.getDate();

            // get and display orders for that date
            orderList = service.getOrdersByDate(date);
            view.displayOrders(orderList);

            // get the order number to edit,  from those orders
            orderNumber = view.getOrderNumber(orderList);
            orderToEdit = service.getOrder(date, orderNumber.toString());

            // edit the Order.
            editTheOrder(date, orderToEdit);
            displayOrdersForDate(date);

        } catch (FlooringMasterNoOrdersForDateException | UserIONoValueException e) {
            System.out.println(e.getClass().getName());
            if (e.getClass().getName().contains("FlooringMasterNoOrdersForDateException")) {
                view.displayErrorMessage("No orders found for date: " + date);
            }
        }

    }

    public void removeOrder() {
        // remove an order from the runtime order list
        String date = null;
        List<Order> orderList = null;
        Order orderToRemove = null;

        Integer orderNumber;

        try {
            // get the order date and display orders
            date = view.getDate();
            orderList = service.getOrdersByDate(date);
            view.displayOrders(orderList);

            // get the order number to remove
            orderNumber = view.getOrderNumber(orderList);
            orderToRemove = service.getOrder(date, orderNumber.toString());

            // display the order
            view.displayOrder(orderToRemove);

            // confirm the removal
            String confirmation = view.getConfirmation("Remove this order?");
            if (confirmation.toLowerCase().equals("y")) {
                // remove the order from the runtime orders
                orderToRemove = service.removeOrder(date, orderNumber.toString());
                changesToSave = true;
            }
            displayOrdersForDate(date);
        } catch (FlooringMasterNoOrdersForDateException | UserIONoValueException e) {
            //System.out.println(e.getClass().getName());
            if (e.getClass().getName().contains("FlooringMasterNoOrdersForDateException")) {
                view.displayErrorMessage("No orders found for date: " + date);
            }
        }

    }

    public void createOrder() {

        // create a new order
        Order order = new Order();
        Boolean invalidData = true;
        List<Order> orderList = null;

        // get the next order number.
        int orderNumber = service.getNextOrderNumber();

        Product product = null;
        TaxRate taxrate = null;

        BigDecimal area = null;

        String customerName = null;
        String prodType = null;
        String state = null;
        String date = "";

        List<TaxRate> taxRates = service.getAllTaxRates();
        List<Product> allProducts = service.getAllProducts();

        invalidData = true;
        while (invalidData) {
            try {
                // get the Order date
                date = view.getDate();
                // System.out.println("date: " + date);
                invalidData = false;
            } catch (UserIONoValueException e) {
                view.displayErrorMessage("You must enter a value for date.");
            }
        }

        invalidData = true;
        while (invalidData) {
            try {
                // get the customer name
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
            if (area != null) {
                invalidData = false;
            } else {
                view.displayErrorMessage("Invalid value for Area. Please re-enter.");
            }

        }

        Order newOrder = new Order(orderNumber, customerName, taxrate, product, area);

        view.displayOrder(newOrder);

        String confirmation = view.getConfirmation("Add this order?");
        if (confirmation.toLowerCase().equals("y")) {

            try {
                service.getOrdersByDate(date);
            } catch (FlooringMasterNoOrdersForDateException e) {
                //  its really not an issue if there are no orders for this date.
            }

            newOrder = service.addOrder(date, newOrder);
            changesToSave = true;

            // view.displayBanner("NewOrder:" + newOrder.toString());
            //order = view.createNewOrder(taxRates, allProducts);
            // List<Product> products                = Order newOrder = view.getNewOrder();
        }

        displayOrdersForDate(date);

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
            changesToSave = true;
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

    public void displayOrdersForDate(String date) {

        try {
            List<Order> orderList = service.getOrdersByDate(date);
            view.displayOrders(orderList);
        } catch (FlooringMasterNoOrdersForDateException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

}
