/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine;

import java.math.BigDecimal;

/**
 *
 * @author apprentice
 */
public class Change {

    public int quarters;
    private int dimes;
    private int nickels;
    private int pennies;

    public void makeChange(BigDecimal amount) {
        this.quarters = 3;
        this.dimes = 2;
        this.nickels = 1;
        this.pennies = 3;
    }

}
