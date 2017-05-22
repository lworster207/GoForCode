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
public class FlooringMasterDaoPersistenceException extends Exception {

    /**
     * Creates a new instance of
     * <code>FlooringMasterDaoPersistenceException</code> without detail
     * message.
     */
    public FlooringMasterDaoPersistenceException() {
    }

    /**
     * Constructs an instance of
     * <code>FlooringMasterDaoPersistenceException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public FlooringMasterDaoPersistenceException(String msg) {
        super(msg);
    }
}
