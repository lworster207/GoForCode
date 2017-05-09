/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.controller;

// enums
import com.sg.vendingmachine.ChangeNoChangeDueException;
import com.sg.vendingmachine.VendingMachineMenu;
import com.sg.vendingmachine.dao.VendingMachineExitGetCashMenuException;
import com.sg.vendingmachine.dao.VendingMachineInsufficientFundsException;
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

        List<Item> availableProducts;

        while (keepGoing) {
            try {
                availableProducts = getAllAvailableItems();

                if (availableProducts.size() <= 0) {
                    view.displayBanner("The vending machine is Sold Out!. Sorry for the inconvenience.");
                    keepGoing = false;
                } else {
                    view.displayAvailableProducts(availableProducts);

                    switch (getMainMenuSelection()) {
                        case EXIT:
                            // returnChange();
                            cancelPurchase();
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
                }
            } catch (VendingMachinePersistenceException e) {
                view.displayErrorMessage(e.getMessage());
                keepGoing = false;
            }
        }
    }

    public List<Item> getAllAvailableItems() {
        List<Item> availableItems = null;
        try {
            availableItems = service.getAllAvailableItems();
        } catch (VendingMachinePersistenceException e) {
            view.println(e.getMessage());
        }
        return availableItems;

    }

    public void addCash() throws VendingMachinePersistenceException {
        // add cash to the user account

        BigDecimal cashToAdd;
        Boolean addMoreCash = true;

        while (addMoreCash) {
            try {
                cashToAdd = view.displayInsertCash(service.getBalance());
                service.setBalance(service.getBalance().add(cashToAdd));
            } catch (VendingMachineExitGetCashMenuException e) {
                addMoreCash = false;
            }
        }
    }

    private void cancelPurchase() throws VendingMachinePersistenceException {
        // calculate change
        try {
            service.makeChange(service.getBalance());
            view.displayChange(service.getQuarters(), service.getDimes(), service.getNickels());
            service.dispenseChange();
        } catch (ChangeNoChangeDueException e) {
            view.displayNoChangeDue();
        }

    }

    private VendingMachineMenu getMainMenuSelection() throws VendingMachinePersistenceException {
        // display the options menu and return the user selection

        return view.displayMenuAndGetOption(service.getBalance());
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
        Item selectedItem = null;

        view.displayAvailableProducts(availableProducts);
        itemId = view.prompt("Enter Product ID");

        // validate the product id
        try {
            selectedItem = service.getItem(itemId);

            // validate sufficient funds available
            service.validateFunds(selectedItem);

            // there are enough funds to purchase the product.
            // item is being dispensed
            view.println("\nDispensing Item : " + selectedItem.getName() + "...");
            service.dispenseItem(selectedItem);

            // adjust the users cash balance
            service.setBalance(service.getBalance().subtract(selectedItem.getPrice()));

            // calculate change
            try {
                service.makeChange(service.getBalance());
                view.displayChange(service.getQuarters(), service.getDimes(), service.getNickels());
                // dispense the change ( reset the change coin counters to zero
                service.dispenseChange();
            } catch (ChangeNoChangeDueException e) {
                view.displayNoChangeDue();
            }

        } catch (VendingMachinePersistenceException | VendingMachineInsufficientFundsException e) {
            view.displayErrorMessage(e.getMessage());
            return;
        }
    }
}
