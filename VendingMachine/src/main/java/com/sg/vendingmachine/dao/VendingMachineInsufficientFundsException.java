/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

/**
 *
 * @author apprentice
 */
public class VendingMachineInsufficientFundsException extends Exception {

    /**
     * Creates a new instance of
     * <code>VendingMachineInsufficientFundsException</code> without detail
     * message.
     */
    public VendingMachineInsufficientFundsException() {
    }

    public VendingMachineInsufficientFundsException(String msg) {
        super(msg);
    }

    public VendingMachineInsufficientFundsException(String message, Throwable cause) {
        super(message, cause);
    }
}
