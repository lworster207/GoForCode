/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.ui;

import com.sg.vendingmachine.Change;
import com.sg.vendingmachine.VendingMachineMenu;
import static com.sg.vendingmachine.VendingMachineMenu.ADDCASH;
import static com.sg.vendingmachine.VendingMachineMenu.CANCEL;
import static com.sg.vendingmachine.VendingMachineMenu.EXIT;
import static com.sg.vendingmachine.VendingMachineMenu.PURCHASEITEM;
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
        System.out.printf("%10s  %10s  %10s %10s\n", "Product ID", "   Name   ", "  Price  ", "  Inventory  ");
        for (Item product : availableItems) {
            //System.out.println(product.getItemId() + " " + product.getName() + " " + product.getPrice());
            System.out.printf("%10s  %10s  %10s %10d\n", product.getItemId(), product.getName(), product.getPrice(), product.getQuantity());
        }
    }

    public String displayAvailableProductsAndGetItemId(List<Item> availableItems) {
        //System.out.println("Product Id         Name         Price");
        List<Item> selectedItem;
        String choice;
        boolean keepGoing = true;
        String retVal = null;

        while (keepGoing) {
            displayAvailableProducts(availableItems);
            choice = io.readString("Enter product ID: ");
            /* availableItems
                    .stream()
                    .filter(selectedItem->selectedItem.getItemId().equals(choice))
                    .collect(Collectors.toList());
             */

            for (Item item : availableItems) {
                if (item.getItemId().toUpperCase().equals(choice.toUpperCase())) {
                    keepGoing = false;
                    retVal = item.getItemId();
                }
            }
            if (keepGoing) {
                displayErrorMessage(choice + " is not a valid product Id.");
            }
        }
        return retVal;

    }

    public BigDecimal displayInsertCash(BigDecimal currentBalance) {
        int choice = 4;

        boolean keepEnteringCash = true;
        BigDecimal runningBalance = currentBalance;

        // list Available Items...
        // List<Item> itemsToDisplay =
        do {
            io.println("\nCurrent Balance: " + runningBalance.toString());
            io.println("\n");
            displayBanner("Insert Cash Menu");
            io.println("1. Insert a Dollar");
            io.println("2. Insert a 50 cent piece");
            io.println("3. Insert a Quarter");
            io.println("4. Insert a Dime");
            io.println("5. Insert a Nickel");
            io.println("6. Exit");

            choice = io.readInt("Insert Cash Option? :.", 1, 6);
            switch (choice) {
                case 1:
                    runningBalance = runningBalance.add(new BigDecimal("1.00"));
                    break;
                case 2:
                    runningBalance = runningBalance.add(new BigDecimal("0.50"));
                    break;
                case 3:
                    runningBalance = runningBalance.add(new BigDecimal("0.25"));
                    break;
                case 4:
                    runningBalance = runningBalance.add(new BigDecimal("0.10"));
                    break;
                case 5:
                    runningBalance = runningBalance.add(new BigDecimal("0.05"));
                    break;
                case 6:
                    keepEnteringCash = false;
                default:
                    break;
            }
        } while (keepEnteringCash);

        return runningBalance;
    }

    public void displayDispenseItem(Item item) {

        io.println("Dispensing " + item.getName() + "...");

    }

    public void displayChange(Change change) {
        if (change.changeIsDue()) {
            displayBanner("Please Take Your Change");
            println("Quarters: " + change.quarters);
            println("Dimes: " + change.dimes);
            println("Nickels: " + change.nickels);
        } else {
            displayBanner("No change due.");
        }
    }

    public void displaySoldOutMessage() {
        io.println("The vending machine is Sold Out!. Sorry for the inconvenience.");
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

    public String prompt(String prompt) {
        return io.readString(prompt);
    }

}
