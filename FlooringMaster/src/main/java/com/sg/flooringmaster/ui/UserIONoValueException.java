/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmaster.ui;

/**
 *
 * @author apprentice
 */
public class UserIONoValueException extends Exception {

    /**
     * Creates a new instance of <code>UserIONoValueException</code> without
     * detail message.
     */
    public UserIONoValueException() {
    }

    /**
     * Constructs an instance of <code>UserIONoValueException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public UserIONoValueException(String msg) {
        super(msg);
    }
}
