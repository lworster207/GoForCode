/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc;

import com.sg.vendingmachinespringmvc.dao.FundsDao;
import com.sg.vendingmachinespringmvc.dao.ItemListDao;
import com.sg.vendingmachinespringmvc.dao.model.VendingMachinePersistenceException;
import com.sg.vendingmachinespringmvc.model.Change;
import com.sg.vendingmachinespringmvc.model.Item;
import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author apprentice
 */
@Controller
public class VendingMachineController {

    ItemListDao dao;
    FundsDao fundsDao;

    @Inject
    public VendingMachineController(ItemListDao dao, FundsDao fundsDao) {
        this.dao = dao;
        this.fundsDao = fundsDao;
        fundsDao.setBalance(new BigDecimal("0.00"));

        /*
        try {
            if (dao.getItem("1") == null) {
                dao.addItem("1", new Item("1", "Doritos", new BigDecimal("1.25"), 5));
                dao.addItem("2", new Item("2", "Lays", new BigDecimal("1.25"), 7));
                dao.addItem("3", new Item("3", "Snickers", new BigDecimal("1.50"), 2));
                dao.addItem("4", new Item("4", "Milky Way", new BigDecimal("1.25"), 1));
                dao.addItem("5", new Item("5", "Three Musketeers", new BigDecimal("1.50"), 6));
                dao.addItem("6", new Item("6", "Lays BBQ", new BigDecimal("1.25"), 3));
                dao.addItem("7", new Item("7", "M&M Plain", new BigDecimal("1.75"), 10));
                dao.addItem("8", new Item("8", "Milk Duds", new BigDecimal("1.65"), 4));
                dao.addItem("9", new Item("9", "M&M Peanut", new BigDecimal("1.75"), 7));
                dao.addItem("10", new Item("10", "M&M Peanut", new BigDecimal("1.75"), 7));
                dao.addItem("11", new Item("11", "M&M Peanut", new BigDecimal("1.75"), 7));
               fundsDao.setBalance(new BigDecimal("0.00"));
            }

        } catch (VendingMachinePersistenceException e) {
            return;
        }
         */
    }

    @RequestMapping(value = "/displayVendingMachine", method = RequestMethod.GET)
    public String displayVendingMachine(Model model) {

        List<Item> itemList;

        try {
            itemList = dao.getAllItems();
        } catch (VendingMachinePersistenceException e) {
            model.addAttribute("message", "displayVendingMachine - Persistence Exception!");
            return "vendingMachine";
        }

        setModelAttributes(model, itemList, fundsDao.getBalance().toString(), "", "", "");

        return "vendingMachine";

    }

    @RequestMapping(value = "/selectItem", method = RequestMethod.POST)
    public String selectItem(HttpServletRequest request,
            Model model) {

        String itemId = request.getParameter("item-form-itemId");
        String balanceMsg = "";
        List<Item> itemList;
        Item item;

        try {
            item = dao.getItem(itemId);
            itemList = dao.getAllItems();
        } catch (VendingMachinePersistenceException e) {
            model.addAttribute("message", "error in selectItem");
            return "vendingMachine";
        }

        if (item.getQuantity() == 0) {
            setModelAttributes(model, itemList, fundsDao.getBalance().toString(), "Sold Out!!!", itemId, "");
            return "vendingMachine";
        } else if (fundsDao.getBalance().compareTo(item.getPrice()) == -1) {
            balanceMsg = "Please deposit $" + item.getPrice().subtract(fundsDao.getBalance()).toString();

            // not enough money.
            setModelAttributes(model, itemList, fundsDao.getBalance().toString(), balanceMsg, itemId, "");
            return "vendingMachine";
        } else {
            setModelAttributes(model, itemList, fundsDao.getBalance().toString(), item.getName(), itemId, "");
            return "vendingMachine";
        }
    }

    @RequestMapping(value = "/purchaseItem", method = RequestMethod.POST)
    public String purchaseItem(HttpServletRequest request,
            Model model) {

        String itemId = request.getParameter("item");
        List<Item> itemList;
        Item item;

        try {
            item = dao.getItem(itemId);
            itemList = dao.getAllItems();
        } catch (VendingMachinePersistenceException e) {
            model.addAttribute("message", "error in purchaseItem");
            return "vendingMachine";
        }

        if (item == null) {
            setModelAttributes(model, itemList, fundsDao.getBalance().toString(), "Please select an item.", "", "");
            return "vendingMachine";
        } else if (item.getQuantity() == 0) {
            setModelAttributes(model, itemList, fundsDao.getBalance().toString(), "Sold Out!!!", itemId, "");
            return "vendingMachine";
        } else if (fundsDao.getBalance().compareTo(item.getPrice()) == -1) {
            // not enough money.
            setModelAttributes(model, itemList, fundsDao.getBalance().toString(), "Insufficient funds! " + request.getParameter("make-purchase-message"), itemId, "");
            return "vendingMachine";
        } else {
            // vend the item
            Change change = new Change(fundsDao.getBalance().subtract(item.getPrice()));

            try {
                dao.setQuantity(item, item.getQuantity() - 1);
                itemList = dao.getAllItems();
            } catch (VendingMachinePersistenceException e) {
                model.addAttribute("message", "error in purchaseItem");
                return "vendingMachine";
            }

            fundsDao.setBalance(new BigDecimal("0.00"));
            setModelAttributes(model, itemList, fundsDao.getBalance().toString(), "Thank You!!!", "", change.toString());

            return "vendingMachine";
        }
    }

    @RequestMapping(value = "/addMoney", method = RequestMethod.POST)
    public String addMoney(HttpServletRequest request,
            Model model) {
        BigDecimal amountToAdd = new BigDecimal(request.getParameter("amountToAdd"));
        List<Item> itemList;
        Item item;
        String balanceMsg = "";

        try {
            itemList = dao.getAllItems();
            item = dao.getItem(request.getParameter("itemId"));
        } catch (VendingMachinePersistenceException e) {
            model.addAttribute("message", "addMoney - Persistence Exception!");
            return "vendingMachine";
        }
        fundsDao.addFunds(amountToAdd);
        if (item != null) {
            if (fundsDao.getBalance().compareTo(item.getPrice()) == -1) {
                balanceMsg = "Please deposit $" + (item.getPrice().subtract(fundsDao.getBalance())).toString();
            }
        }

        setModelAttributes(model, itemList, fundsDao.getBalance().toString(), balanceMsg, request.getParameter("itemId"), "");

        return "vendingMachine";

    }

    @RequestMapping(value = "/changeReturn", method = RequestMethod.POST)
    public String changeReturn(HttpServletRequest request,
            Model model) {
        List<Item> itemList;

        try {
            itemList = dao.getAllItems();
        } catch (VendingMachinePersistenceException e) {
            model.addAttribute("message", "changeReturn - Persistence Exception!");
            return "vendingMachine";
        }

        Change change = new Change(fundsDao.getBalance());
        String changeMsg = change.toString();
        fundsDao.setBalance(new BigDecimal("0.00"));

        setModelAttributes(model, itemList, fundsDao.getBalance().toString(), "", "", changeMsg);

        return "vendingMachine";
    }

    private void setModelAttributes(Model model, List<Item> itemList, String balance, String message, String itemId, String changeMsg) {
        model.addAttribute("itemList", itemList);
        model.addAttribute("balance", balance);
        model.addAttribute("message", message);
        model.addAttribute("itemId", itemId);
        model.addAttribute("changeMessage", changeMsg);
    }

}
