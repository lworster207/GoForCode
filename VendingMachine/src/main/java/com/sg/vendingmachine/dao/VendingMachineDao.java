/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface VendingMachineDao {

    public String getName(Item item);

    public int getQuantity(Item item);

    public BigDecimal getPrice(Item item);

    public List<Item> getAllItems();

    public Item getItem(String itemId);

    public void setQuantity(Item item, int quantity);

}
