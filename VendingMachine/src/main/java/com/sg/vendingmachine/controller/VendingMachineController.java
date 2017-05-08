/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.VendingMachineMenu;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.service.VendingMachineServiceLayer;
import com.sg.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class VendingMachineController {

    private VendingMachineView view;
    private VendingMachineServiceLayer service;

    public VendingMachineMenu menuPick;

    public VendingMachineController(VendingMachineServiceLayer myDao, VendingMachineView myView) {
        this.view = myView;
        this.service = myDao;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection;

        List<Item> availableProducts;

        availableProducts = getAllAvailableItems();

        if (availableProducts.size() <= 0) {
            view.displayErrorMessage("The vending machine is Sold Out!. Sorry for the inconvenience.");
            keepGoing = false;
        } else {
            view.displayAvailableProducts(availableProducts);
        }
        //try {

        while (keepGoing) {

            switch (getMainMenuSelection()) {
                case EXIT:
                    // returnChange();
                    exitMessage();
                    keepGoing = false;
                    break;
                case PURCHASEITEM:
                    purchaseProduct(availableProducts);
                    break;
                case ADDCASH:
                    addCash();
                    break;
                case CANCEL:
                    cancelPurchase();
                    break;
            }

            availableProducts = getAllAvailableItems();

            if (availableProducts.size() <= 0) {
                view.displayErrorMessage("The vending machine is Sold Out!. Sorry for the inconvenience.");
                keepGoing = false;
            } else {
                view.displayAvailableProducts(availableProducts);
            }
        }
        //exitMessage();
        // } catch (VendingMachineDaoException e) {
        //    view.displayErrorMessage(e.getMessage());
        //  }
    }

    public List<Item> getAllAvailableItems() {
        List<Item> availableItems = null;
        try {
            availableItems = service.getAllAvailableItems();
        } catch (VendingMachinePersistenceException e) {

        }
        return availableItems;

    }

    public void addCash() {
        // add some cash to the user account
        BigDecimal addedCash = new BigDecimal("0.00");
        do {
            addedCash = view.displayInsertCash(service.getBalance());
            service.setBalance(service.getBalance().add(addedCash));
        } while (addedCash.compareTo(new BigDecimal("0.00")) == 1);
    }

    private void cancelPurchase() {
        // calculate change
        service.makeChange(service.getBalance());

        // display the users change
//            if (service.changeIsDue()) {
        view.displayBanner("Please Take Your Change");
        view.println("Quarters: " + service.getQuarters());
        view.println("Dimes: " + service.getDimes());
        view.println("Nickels: " + service.getNickels());
        service.dispenseChange();

    }

    private VendingMachineMenu getMainMenuSelection() {
        // display the options menu and return the user selection

        return view.displayMenuAndGetOption(service.getBalance());
    }

    private void purchaseItem() {

    }

    private void unknownCommand() {
        view.displayBanner("Unknown Command!");
    }

    private void exitMessage() {
        view.displayBanner("Exiting...");
        if (service.changeIsDue()) {
            view.displayBanner("Please Take Your Change");
            view.println("Quarters: " + service.getQuarters());
            view.println("Dimes: " + service.getDimes());
            view.println("Nickels: " + service.getNickels());
            service.dispenseChange();
        }
    }

    private void purchaseProduct(List<Item> availableProducts) {
        // see if any money has been entered.
        String itemId;

        view.displayAvailableProducts(availableProducts);
        itemId = view.prompt("Enter Product ID");

        // validate the product id
        Item selectedItem = service.getItem(itemId);

        if (service.getBalance().compareTo(selectedItem.getPrice()) >= 0) {
            // there are enough funds to purchase the product.

            // item is being dispensed
            view.println("\nDispensing Item : " + selectedItem.getName() + "...");
            service.dispenseItemInLine(selectedItem);

            // adjust the users cash balance
            service.setBalance(service.getBalance().subtract(selectedItem.getPrice()));

            // calculate change
            service.makeChange(service.getBalance());

            // display the users change
            view.println("");
            view.displayBanner("Your Change");
            view.println("Quarters: " + service.getQuarters());
            view.println("Dimes: " + service.getDimes());
            view.println("Nickels: " + service.getNickels());
            view.println("");

            // dispense the change ( reset the change coin counters to zero
            service.dispenseChange();
        } else {
            // insufficient funds
            view.displayErrorMessage("Insufficient Funds for this purchase");
            view.println("Please add more money to purchase this item");

        }
    }
}
