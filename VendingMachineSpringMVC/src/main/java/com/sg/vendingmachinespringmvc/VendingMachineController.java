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
                fundsDao.setBalance(new BigDecimal("0.00"));
            }

        } catch (VendingMachinePersistenceException e) {
            return;
        }

    }

    @RequestMapping(value = "/displayVendingMachine", method = RequestMethod.GET)
    public String displayVendingMachine(Model model) {

        List<Item> items;

        try {
            items = dao.getAllItems();

            model.addAttribute("itemList", items);
            model.addAttribute("balance", fundsDao.getBalance().toString());
            model.addAttribute("message", "");
            return "vendingMachine";
        } catch (VendingMachinePersistenceException e) {
            model.addAttribute("message", "displayVendingMachine - Persistence Exception!");
            return "vendingMachine";
        }
    }

    @RequestMapping(value = "/purchaseItem", method = RequestMethod.POST)
    public String purchaseItem(HttpServletRequest request,
            Model model) {

        String itemId = request.getParameter("item");
        List<Item> itemList;

        try {

            Item item = dao.getItem(itemId);
            if (item == null) {
                itemList = dao.getAllItems();
                model.addAttribute("itemList", itemList);
                model.addAttribute("balance", fundsDao.getBalance().toString());
                model.addAttribute("message", "Please select an item.");
                model.addAttribute("itemId", "");
                model.addAttribute("changeMessage", "");
                return "vendingMachine";
            }
            if (item.getQuantity() == 0) {
                itemList = dao.getAllItems();
                model.addAttribute("itemList", itemList);
                model.addAttribute("balance", fundsDao.getBalance().toString());
                model.addAttribute("message", "Sold Out!!!");
                model.addAttribute("itemId", itemId);
                model.addAttribute("changeMessage", "");
                return "vendingMachine";
            } else if (fundsDao.getBalance().compareTo(item.getPrice()) == -1) {
                // not enough money.
                itemList = dao.getAllItems();
                model.addAttribute("itemList", itemList);
                model.addAttribute("balance", fundsDao.getBalance().toString());
                model.addAttribute("message", "Insufficient funds! " + request.getParameter("make-purchase-message"));
                model.addAttribute("itemId", itemId);
                //model.addAttribute("changeMessage", "");
                return "vendingMachine";
            } else {

                Change change = new Change(fundsDao.getBalance().subtract(item.getPrice()));
                String changeMsg = change.toString();

                dao.setQuantity(item, item.getQuantity() - 1);

                fundsDao.setBalance(new BigDecimal("0.00"));

                itemList = dao.getAllItems();
                model.addAttribute("itemList", itemList);
                model.addAttribute("balance", fundsDao.getBalance().toString());
                model.addAttribute("message", "Thank You!!!");
                model.addAttribute("itemId", "");
                model.addAttribute("changeMessage", changeMsg);
                return "vendingMachine";
            }
        } catch (VendingMachinePersistenceException e) {
            model.addAttribute("message", "error in purchaseItem");
            return "vendingMachine";
        }
    }

    @RequestMapping(value = "/addMoney", method = RequestMethod.POST)
    public String addMoney(HttpServletRequest request,
            Model model) {
        BigDecimal amountToAdd = new BigDecimal(request.getParameter("amountToAdd"));
        fundsDao.addFunds(amountToAdd);
        try {
            List<Item> itemList = dao.getAllItems();
            model.addAttribute("itemList", itemList);
            model.addAttribute("balance", fundsDao.getBalance().toString());
            model.addAttribute("message", request.getParameter("balance-message"));
            model.addAttribute("itemId", request.getParameter("itemId"));
            model.addAttribute("changeMessage", "");
            return "vendingMachine";
        } catch (VendingMachinePersistenceException e) {
            model.addAttribute("message", "addMoney - Persistence Exception!");
            return "vendingMachine";
        }

    }

    @RequestMapping(value = "/changeReturn", method = RequestMethod.POST)
    public String changeReturn(HttpServletRequest request,
            Model model) {
        fundsDao.setBalance(new BigDecimal("0.00"));

        try {
            List<Item> itemList = dao.getAllItems();
            model.addAttribute("itemList", itemList);
            model.addAttribute("balance", fundsDao.getBalance().toString());
            model.addAttribute("changeMessage", request.getParameter("changeMsg"));
            return "vendingMachine";
        } catch (VendingMachinePersistenceException e) {
            model.addAttribute("message", "changeReturn - Persistence Exception!");
            return "vendingMachine";
        }
    }

}
