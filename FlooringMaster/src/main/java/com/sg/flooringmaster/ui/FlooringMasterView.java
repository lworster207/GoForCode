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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        } catch (UserIONoValueException e) {
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

    public String getDate() throws UserIONoValueException {
        LocalDate ld = null;
        String formatted = null;
        boolean invalidData = true;

        while (invalidData) {
            try {
                ld = io.readLocalDate("Order Date? ", 1, 12, 1, 31, 2000, 2050);
                formatted = ld.format(DateTimeFormatter.ofPattern("MMddyy"));
                invalidData = false;
            } catch (NumberFormatException e) {
                displayErrorMessage("Invalid entry for date.");
            }
        }

        return formatted;
        //return io.getNextLine("Order Date?");

    }

    public String getCustomerName() throws UserIONoValueException {
        return io.getNextLine("Customer Name?");
    }

    public void displayOrders(List<Order> orderList) {

        if (orderList != null) {
            for (Order order : orderList) {
                displayOrder(order);
            }
        }
    }

    public void displayOrder(Order order) {
        String delimiter = " | ";
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

    public int getOrderNumber(List<Order> orders) {
        boolean invalidEntry = true;
        int orderNumber = -1;

        while (invalidEntry) {
            try {
                orderNumber = io.readInt("Enter the order number");
                invalidEntry = false;
            } catch (UserIONoValueException e) {
                displayErrorMessage("Please enter a value.");
            }
        }
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

    public BigDecimal getArea() {
        boolean invalidData = true;
        BigDecimal retVal = null;

        while (invalidData) {
            try {
                retVal = io.readBigDecimal("Area");
                if (retVal != null) {
                    invalidData = false;
                } else {
                    displayErrorMessage("Invalid entry for area value.");
                }
            } catch (NumberFormatException e) {
                displayErrorMessage("Invalid entry for area value.");
            }
        }
        return retVal;
    }

    public String getStateOption(List<TaxRate> taxrates) {
        // return the state as entered by the user
        displayTaxRateMenu(taxrates);
        return io.readString("Enter the state: ");
    }

    public BigDecimal getEditArea(Order order) {
        BigDecimal area;
        try {
            area = new BigDecimal(io.getNextLine("Area (" + order.getArea() + ")"));
            return area;
        } catch (UserIONoValueException e) {
            return order.getArea();
        }
    }

    public String getEditDate(String curDate) {
        try {
            println("Date? (" + curDate + ")");
            return getDate();
        } catch (UserIONoValueException e) {
            return curDate;
        }

    }

    public String getEditCustomerName(Order order) {
        try {
            return io.getNextLine("Customer Name? (" + order.getCustomerName() + ")");
        } catch (UserIONoValueException e) {
            return order.getCustomerName();
        }
    }

    public String getEditProductTypeOption(Order order, List<Product> products) {
        // return the product type as entered by the user
        displayProductTypeMenu(products);
        try {
            return io.getNextLine("Enter the product type: (" + order.getProduct().getProductType() + ")");
        } catch (UserIONoValueException e) {
            return order.getProduct().getProductType();
        }
    }

    public String getEditStateOption(Order order, List<TaxRate> taxrates) {
        // return the state as entered by the user
        displayTaxRateMenu(taxrates);
        try {
            return io.getNextLine("Enter the state: (" + order.getStateTaxRate().getState() + ")");
        } catch (UserIONoValueException e) {
            return order.getStateTaxRate().getState();
        }

    }

    public String getConfirmation(String prompt) {
        String retVal = null;
        boolean invalidData = true;

        while (invalidData) {
            try {
                retVal = io.getNextLine(prompt);
                if (retVal.toUpperCase().equals("Y") || retVal.toUpperCase().equals("N")) {
                    invalidData = false;
                } else {
                    displayErrorMessage("Invalid entry - Please enter Y or N.");
                }

            } catch (UserIONoValueException e) {
                displayErrorMessage("Please enter Y or N.");
            }
        }
        return retVal.toUpperCase();
    }

}
