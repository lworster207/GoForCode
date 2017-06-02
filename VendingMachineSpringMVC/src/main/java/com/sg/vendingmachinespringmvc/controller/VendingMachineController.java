/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.controller;

import com.sg.vendingmachinespringmvc.dao.ItemListDao;
import com.sg.vendingmachinespringmvc.dao.model.VendingMachinePersistenceException;
import com.sg.vendingmachinespringmvc.model.Item;
import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author apprentice
 */
public class VendingMachineController {

    ItemListDao dao;

    @Inject
    public VendingMachineController(ItemListDao dao) {
        this.dao = dao;
        try {
            dao.addItem("1", new Item("1", "Doritos", new BigDecimal("1.25"), 5));
            dao.addItem("2", new Item("2", "Lays", new BigDecimal("1.25"), 7));
            dao.addItem("3", new Item("3", "Snickers", new BigDecimal("1.50"), 2));
            dao.addItem("4", new Item("4", "Milky Way", new BigDecimal("1.25"), 1));
            dao.addItem("5", new Item("5", "Three Musketeers", new BigDecimal("1.50"), 6));
            dao.addItem("6", new Item("6", "Lays BBQ", new BigDecimal("1.25"), 3));
            dao.addItem("7", new Item("7", "M&M Plain", new BigDecimal("1.75"), 10));
            dao.addItem("8", new Item("8", "Milk Duds", new BigDecimal("1.65"), 4));
            dao.addItem("9", new Item("9", "M&M Peanut", new BigDecimal("1.75"), 7));
        } catch (VendingMachinePersistenceException e) {

        }
    }

    @RequestMapping(value = "/displayVendingMachine", method = RequestMethod.GET)
    public String displayVendingMachine(Model model) {
        try {
            List<Item> itemList = dao.getAllItems();
            // Put the List of Contacts on the Model
            model.addAttribute("itemList", itemList);
        } catch (VendingMachinePersistenceException e) {

        }

        return "index";
    }

    @RequestMapping(value = "/purchaseItem", method = RequestMethod.GET)
    public String purchaseItem(HttpServletRequest request,
            Model model) {
        return "index";
    }

    @RequestMapping(value = "/addMoney", method = RequestMethod.GET)
    public String addMoney(HttpServletRequest request,
            Model model) {
        return "index";
    }

    @RequestMapping(value = "/changeReturn", method = RequestMethod.GET)
    public String changeReturn(HttpServletRequest request,
            Model model) {
        return "vendingMachine";
    }

}
