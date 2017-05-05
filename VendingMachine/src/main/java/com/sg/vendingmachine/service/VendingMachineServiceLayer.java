/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface VendingMachineServiceLayer {

    public String getName(Item item);

    public int getQuantity(Item item);

    public BigDecimal getPrice(Item item);

    public List<Item> getAllItems();

    public List<Item> getAllAvailableItems();

    public Item getItem(String itemId);

    public void setQuantity(Item item, int quantity);

    public BigDecimal getBalance();

    public void setBalance();

}
