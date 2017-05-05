/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.VendingMachineMenu;
import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.service.VendingMachineServiceLayer;
import com.sg.vendingmachine.ui.VendingMachineView;
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

        availableProducts = service.getAllAvailableItems();

        if (availableProducts.size() <= 0) {
            view.displayErrorMessage("The vending machine is out of order. Sorry for the inconvenience.");
            keepGoing = false;
        } else {
            view.displayAvailableProducts(availableProducts);
        }
        //try {

        while (keepGoing) {

            switch (view.displayMenuAndGetOption()) {
                case EXIT:
                    // returnChange();
                    exitMessage();
                    keepGoing = false;
                    break;
                case PURCHASEITEM:
                    purchaseProduct(availableProducts);
                    break;

            }
        }
        //exitMessage();
        // } catch (VendingMachineDaoException e) {
        //    view.displayErrorMessage(e.getMessage());
        //  }
    }

    private VendingMachineMenu getMenuSelection() {
        // display the options menu and return the user selection
        return view.displayMenuAndGetOption();
    }

    private purchaseItem() {

    }

    private void unknownCommand() {
        view.displayBanner("Unknown Command!");
    }

    private void exitMessage() {
        view.displayBanner("Exiting...");
    }

    private void purchaseProduct(List<Item> availableProducts) {
        // see if any money has been entered.
        String productId;

        view.displayAvailableProducts(availableProducts);
        productId = view.prompt("Enter Product ID");

    }
}
