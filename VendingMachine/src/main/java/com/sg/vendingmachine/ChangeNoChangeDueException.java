/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine;

/**
 *
 * @author apprentice
 */
public class ChangeNoChangeDueException extends Exception {

    /**
     * Creates a new instance of <code>ChangeNoChangeDue</code> without detail
     * message.
     */
    public ChangeNoChangeDueException() {
    }

    /**
     * Constructs an instance of <code>ChangeNoChangeDue</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ChangeNoChangeDueException(String msg) {
        super(msg);
    }
}
