/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.controller;

// enums
import com.sg.vendingmachine.Change;
import com.sg.vendingmachine.VendingMachineMenu;
import com.sg.vendingmachine.dao.VendingMachineInsufficientFundsException;
import com.sg.vendingmachine.dao.VendingMachineNoItemInventoryException;
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
            //try {
            availableProducts = getAllItems();

            if (availableProducts.size() <= 0) {
                soldOutMessage();
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
            //   } catch (VendingMachinePersistenceException e) {
            //       view.displayErrorMessage(e.getMessage());
            //       keepGoing = false;
            //   }
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

    public List<Item> getAllItems() {
        List<Item> allItems = null;
        try {
            allItems = service.getAllItems();
        } catch (VendingMachinePersistenceException e) {
            view.println(e.getMessage());
        }
        return allItems;

    }

    public void addCash() {
        // add cash to the user account

        BigDecimal newBalance;

        newBalance = view.displayInsertCash(service.getBalance());
        service.setBalance(newBalance);
    }

    private void cancelPurchase() {
        // calculate change
        Change change = new Change(service.getBalance());
        view.displayChange(change);
    }

    private VendingMachineMenu getMainMenuSelection() {
        // display the options menu and return the user selection

        return view.displayMenuAndGetOption(service.getBalance());
    }

    private void soldOutMessage() {
        view.displaySoldOutMessage();
    }

    private void exitMessage() {
        view.displayExitMessage();
    }

    private void purchaseProduct(List<Item> availableProducts) {
        // see if any money has been entered.
        String itemId;
        Item selectedItem = null;

        itemId = view.displayAvailableProductsAndGetItemId(availableProducts);

        // validate the product id
        try {
            selectedItem = service.getItem(itemId);

            // make sure the item is available
            service.validateAvailability(selectedItem);

            // validate sufficient funds available
            service.validateFunds(selectedItem);

            // item is available and there are enough funds to purchase the product.
            // item is being dispensed
            view.displayDispenseItem(selectedItem);
            service.dispenseItem(selectedItem);

            // adjust cash in the vending machine
            service.setBalance(service.getBalance().subtract(selectedItem.getPrice()));

            // calculate change
            Change change = new Change(service.getBalance());
            // display the change
            view.displayChange(change);

            // reset the balance to zero
            service.setBalance(BigDecimal.ZERO);

        } catch (VendingMachinePersistenceException | VendingMachineInsufficientFundsException | VendingMachineNoItemInventoryException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }
}
