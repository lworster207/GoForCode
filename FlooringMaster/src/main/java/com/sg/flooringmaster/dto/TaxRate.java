/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmaster.dto;

import java.math.BigDecimal;

/**
 *
 * @author apprentice
 */
public class TaxRate {

    String state;
    BigDecimal taxRate;

    public TaxRate(String state, BigDecimal taxRate) {
        this.state = state;
        this.taxRate = taxRate;
    }
}
