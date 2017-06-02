/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao.model;

import com.sg.vendingmachinespringmvc.dao.*;

/**
 *
 * @author apprentice
 */
public class VendingMachineNoItemInventoryException extends Exception {

    /**
     * Creates a new instance of
     * <code>VendingMachineItemNotAvailableException</code> without detail
     * message.
     */
    public VendingMachineNoItemInventoryException() {
    }

    /**
     * Constructs an instance of
     * <code>VendingMachineItemNotAvailableException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public VendingMachineNoItemInventoryException(String msg) {
        super(msg);
    }
}
