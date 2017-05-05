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
import com.sg.vendingmachine.dto.Item;
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
    public VendingMachineMenu displayMenuAndGetOption() {
        int choice;
        VendingMachineMenu menuPick;

        // list Available Items...
        // List<Item> itemsToDisplay =
        io.println("\n\n");
        displayBanner("Main Menu");
        io.println("1. Purchase Product");
        io.println("2. Insert Money");
        io.println("3. Cancel Purchase");
        io.println("4. Exit");

        try {
            choice = io.readInt("Please select from the above choices.", 1, 5);
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

    public void displayAvailableProducts(List<Item> availableProducts) {
        System.out.println("Product Id         Name         Price");
        for (Item product : availableProducts) {
            System.out.println(product.getProductId() + " " + product.getName() + " " + product.getPrice());
        }
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
        io.print(errorMsg);
    }

    public String prompt(String prompt) {
        return io.readString(prompt);
    }

}
