/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.dao.model.VendingMachinePersistenceException;
import com.sg.vendingmachinespringmvc.model.Item;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author apprentice
 */
public class ItemListDaoInMemImpl implements ItemListDao {

    public Map<String, Item> items = new HashMap<>();

    @Override
    public String getName(Item item) {
        return item.getName();
    }

    @Override
    public int getQuantity(Item item) {
        return item.getQuantity();
    }

    @Override
    public BigDecimal getPrice(Item item) {
        return item.getPrice();
    }

    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {

        // loadItems();
        return (new ArrayList<>(items.values()));
    }

    @Override
    public Item getItem(String itemId)
            throws VendingMachinePersistenceException {
        //loadItems();
        return items.get(itemId);
    }

    @Override
    public void setQuantity(Item item, int quantity) throws VendingMachinePersistenceException {
        items.get(item.getItemId()).quantity = quantity;
        //writeItems();
    }

    @Override
    public List<Item> getAllAvailableItems()
            throws VendingMachinePersistenceException {

        //loadItems();
        return (items.values().stream().filter(item -> item.getQuantity() > 0).collect(Collectors.toList()));
    }

    @Override
    public Item addItem(String itemId, Item item)
            throws VendingMachinePersistenceException {
        Item existingItem = items.get(itemId);
        if (existingItem == null) {
            Item newItem = items.put(itemId, item);
            //writeItems();
        }
        return items.get(itemId);
    }

    @Override
    public Item removeItem(String itemId)
            throws VendingMachinePersistenceException {
        Item removedItem = items.remove(itemId);
        //writeItems();
        return removedItem;
    }

}
