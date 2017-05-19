/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmaster.dao;

/**
 *
 * @author apprentice
 */
public class FlooringMasterNoOrdersForDateException extends Exception {

    /**
     * Creates a new instance of
     * <code>FlooringMasterNoOrdersForDateException</code> without detail
     * message.
     */
    public FlooringMasterNoOrdersForDateException() {
    }

    /**
     * Constructs an instance of
     * <code>FlooringMasterNoOrdersForDateException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public FlooringMasterNoOrdersForDateException(String msg) {
        super(msg);
    }
}
