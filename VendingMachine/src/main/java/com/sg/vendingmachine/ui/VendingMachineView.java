/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.ui;

import com.sg.vendingmachine.VendingMachineMenu;
import static com.sg.vendingmachine.VendingMachineMenu.ADDCASH;
import static com.sg.vendingmachine.VendingMachineMenu.CANCEL;
import static com.sg.vendingmachine.VendingMachineMenu.EXIT;
import static com.sg.vendingmachine.VendingMachineMenu.PURCHASEITEM;
import com.sg.vendingmachine.dao.VendingMachineExitGetCashMenuException;
import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class VendingMachineView {

    private UserIO io;

///////////        CONSTRUCTORS        \\\\\\\\\\
    public VendingMachineView(UserIO io) {
        this.io = io;
    }

///////////        METHODS        \\\\\\\\\\
    public VendingMachineMenu displayMenuAndGetOption(BigDecimal currentBalance) {
        int choice;
        VendingMachineMenu menuPick;

        // list Available Items...
        // List<Item> itemsToDisplay =
        io.println("Current Balance: " + currentBalance);
        io.println("\n\n");
        displayBanner("Main Menu");
        io.println("1. Purchase Product");
        io.println("2. Insert Money");
        io.println("3. Cancel Purchase");
        io.println("4. Exit");

        try {
            choice = io.readInt("Main Menu Option? :", 1, 4);
            switch (choice) {
                case 1:
                    return (PURCHASEITEM);
                case 2:
                    return (ADDCASH);
                case 3:
                    return (CANCEL);
                case 4:
                    return (EXIT);
            }
        } catch (NumberFormatException e) {
            return EXIT;
        }
        return EXIT;

    }

    public void displayAvailableProducts(List<Item> availableItems) {
        //System.out.println("Product Id         Name         Price");
        System.out.printf("%10s  %10s  %10s\n", "Product ID", "   Name   ", "  Price  ");
        for (Item product : availableItems) {
            //System.out.println(product.getItemId() + " " + product.getName() + " " + product.getPrice());
            System.out.printf("%10s  %10s  %10s\n", product.getItemId(), product.getName(), product.getPrice());
        }
    }

    public BigDecimal displayInsertCash(BigDecimal currentBalance) throws VendingMachineExitGetCashMenuException {
        int choice = 4;
        BigDecimal returnValue;

        // list Available Items...
        // List<Item> itemsToDisplay =
        io.println("\nCurrent Balance: " + currentBalance);
        io.println("\n");
        displayBanner("Insert Cash Menu");
        io.println("1. Insert a Dollar");
        io.println("2. Insert a 50 cent piece");
        io.println("3. Insert a Quarter");
        io.println("4. Insert a Dime");
        io.println("5. Insert a Nickel");
        io.println("6. Exit");

        try {
            choice = io.readInt("Insert Cash Option? :.", 1, 6);
            switch (choice) {
                case 1:
                    returnValue = new BigDecimal("1.00");
                    break;
                case 2:
                    returnValue = new BigDecimal("0.50");
                    break;
                case 3:
                    returnValue = new BigDecimal("0.25");
                    break;
                case 4:
                    returnValue = new BigDecimal("0.10");
                    break;
                case 5:
                    returnValue = new BigDecimal("0.05");
                    break;
                case 6:
                    throw new VendingMachineExitGetCashMenuException();
                default:
                    returnValue = new BigDecimal("0.00");
                    break;
            }
        } catch (NumberFormatException e) {
            returnValue = new BigDecimal("0.00");
            io.println("Please enter a value between 1 - 6");
        }

        return returnValue;
    }

    public void displayChange(int quarters, int dimes, int nickels) {
        displayBanner("Please Take Your Change");
        println("Quarters: " + quarters);
        println("Dimes: " + dimes);
        println("Nickels: " + nickels);
    }

    public void displayNoChangeDue() {
        displayBanner("No change due");
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

    public String prompt(String prompt) {
        return io.readString(prompt);
    }

}
