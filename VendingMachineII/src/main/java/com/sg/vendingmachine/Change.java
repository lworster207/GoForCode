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
    public int dimes;
    public int nickels;

    public Change(BigDecimal amount) {

        BigDecimal oneHundred = new BigDecimal("100");

        int changeInPennies;

        if (amount.compareTo(new BigDecimal("0.00")) > 0) {
            // need to make change
            changeInPennies = amount.multiply(oneHundred).intValue();

            if (changeInPennies >= 25) {
                this.quarters = changeInPennies / 25;
                changeInPennies -= this.quarters * 25;
            }

            if (changeInPennies >= 10) {
                this.dimes = changeInPennies / 10;
                changeInPennies -= this.dimes * 10;
            }

            if (changeInPennies >= 5) {
                this.nickels = changeInPennies / 5;
                changeInPennies -= this.nickels * 5;
            }

        }
    }

    public boolean changeIsDue() {
        // return true if any of the counts are not zero
        return (this.quarters != 0 || this.dimes != 0 || this.nickels != 0);
    }

    public int getQuarters() {
        return quarters;
    }

    public int getDimes() {
        return dimes;
    }

    public int getNickels() {
        return nickels;
    }

}
