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
public class TaxRateNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>TaxRateNotFoundException</code> without
     * detail message.
     */
    public TaxRateNotFoundException() {
    }

    /**
     * Constructs an instance of <code>TaxRateNotFoundException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public TaxRateNotFoundException(String msg) {
        super(msg);
    }
}
