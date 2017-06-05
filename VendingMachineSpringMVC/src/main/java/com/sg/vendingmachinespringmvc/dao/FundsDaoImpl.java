/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import java.math.BigDecimal;

/**
 *
 * @author apprentice
 */
public class FundsDaoImpl implements FundsDao {

    BigDecimal balance;

    @Override
    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public BigDecimal setBalance(BigDecimal balance) {
        this.balance = balance;
        return this.balance;
    }

    @Override
    public BigDecimal addFunds(BigDecimal amountToAdd) {
        return setBalance(balance.add(amountToAdd));
    }

}
