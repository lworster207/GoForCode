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

    public Change() {
        this.dimes = 0;
        this.nickels = 0;
        this.quarters = 0;
    }

    public void makeChange(BigDecimal amount) {

        BigDecimal oneHundred = new BigDecimal("100");

        Change change = new Change();
        int changeInPennies;
        int quantity;

        if (amount.compareTo(new BigDecimal("0.00")) > 0) {
            // need to make change
            changeInPennies = amount.multiply(oneHundred).intValue();
            // System.out.println("Pennies: " + changeInPennies);
            if (changeInPennies >= 25) {
                this.quarters = changeInPennies / 25;
                changeInPennies -= this.quarters * 25;
            }
            //System.out.println("Pennies: after quarters " + changeInPennies);
            if (changeInPennies >= 10) {
                this.dimes = changeInPennies / 10;
                changeInPennies -= this.dimes * 10;
            }
            //System.out.println("Pennies: after dimes" + changeInPennies);
            if (changeInPennies >= 5) {
                this.nickels = changeInPennies / 5;
                changeInPennies -= this.nickels * 5;
            }
            //System.out.println("Pennies: after nickels " + changeInPennies);
        }
    }

    public void dispenseChange() {

        // change was made ( and dispensed ) - reset change coin counters to zeros.
        this.quarters = 0;
        this.nickels = 0;
        this.dimes = 0;

    }

    public Boolean changeIsDue() {
        // return true if any of the counts are not zero
        return (quarters != 0 || dimes != 0 || nickels != 0);
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
