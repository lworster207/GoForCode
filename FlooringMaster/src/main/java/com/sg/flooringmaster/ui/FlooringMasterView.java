/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmaster.ui;

import com.sg.flooringmaster.dto.Order;
import com.sg.flooringmaster.dto.Product;
import com.sg.flooringmaster.dto.TaxRate;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class FlooringMasterView {

    UserIO io;

///////////        CONSTRUCTORS        \\\\\\\\\\
    public FlooringMasterView(UserIO io) {
        this.io = io;
    }

///////////        METHODS        \\\\\\\\\\
    public int displayMenuAndGetOption() {
        int choice = 0;

        // display the main menu
        io.println("\n\n");
        displayBanner("Main Menu");
        io.println("1. List Orders");
        io.println("2. Add Order");
        io.println("3. Remove Order");
        io.println("4. Edit Order");
        io.println("5. Save Changes");
        io.println("6. Exit");

        try {
            choice = io.readInt("Main Menu Option? :", 1, 6);
        } catch (NumberFormatException e) {
            println("Please enter a number between 1-6");
        }
        return choice;

    }

    public void displayExitMessage() {
        io.println("=====  Exiting  =====");
    }

    public void displayBanner(String banner) {
        io.println("===== " + banner + " =====");
    }

    public void print(String message) {
        io.print(message);
    }

    public void println(String message) {
        io.println(message);
    }

    public void displayErrorMessage(String errorMsg) {
        io.println("=== ERROR ===");
        io.println(errorMsg);
    }

    public String getDate() {
        return io.readString("Order Date?");

    }

    public String getCustomerName() {
        return io.readString("Customer Name?");
    }

    public void displayOrders(List<Order> orderList) {
        String delimiter = " | ";
        for (Order order : orderList) {
            io.println(order.getOrderNumber() + delimiter
                    + order.getCustomerName() + delimiter
                    + order.getStateTaxRate().getState() + delimiter
                    + order.getStateTaxRate().getTaxRate() + delimiter
                    + order.getProduct().getProductType() + delimiter
                    + order.getArea() + delimiter
                    + order.getMaterialCost() + delimiter
                    + order.getLaborCost() + delimiter
                    + order.getTax() + delimiter
                    + order.getTotalCost());

        }
    }

    public int getOrderNumber(List<Order> orders) {
        int orderNumber = io.readInt("Enter the order number");
        return orderNumber;
    }

    public void displayProductTypeMenu(List<Product> products) {
        // display the list of available product types
        io.println("Available Product Types");
        for (Product cp : products) {
            System.out.println(cp.getProductType());
        }
    }

    public String getProductTypeOption(List<Product> products) {
        // return the product type as entered by the user
        displayProductTypeMenu(products);
        return io.readString("Enter the product type: ");
    }

    public void displayTaxRateMenu(List<TaxRate> taxrates) {
        // display the list of TaxRates
        io.println("Available States:");
        for (TaxRate tr : taxrates) {
            io.println(tr.getState());
        }
    }

    public String getStateOption(List<TaxRate> taxrates) {
        // return the state as entered by the user
        displayTaxRateMenu(taxrates);
        return io.readString("Enter the state: ");
    }

    public BigDecimal getArea() {
        return io.readBigDecimal("Area");
    }
}
