/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmaster.service;

import com.sg.flooringmaster.dao.FlooringMasterDao;
import com.sg.flooringmaster.dao.FlooringMasterDaoFileImpl;
import com.sg.flooringmaster.dao.FlooringMasterNoOrdersForDateException;
import com.sg.flooringmaster.dao.OrderDay;
import com.sg.flooringmaster.dao.ProductDao;
import com.sg.flooringmaster.dao.ProductDaoFileImpl;
import com.sg.flooringmaster.dao.TaxRateDao;
import com.sg.flooringmaster.dao.TaxRateDaoFileImpl;
import com.sg.flooringmaster.dao.TaxRateNotFoundException;
import com.sg.flooringmaster.dto.Order;
import com.sg.flooringmaster.dto.Product;
import com.sg.flooringmaster.dto.ProductNotFoundException;
import com.sg.flooringmaster.dto.TaxRate;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class FlooringMasterServiceLayerImpl implements FlooringMasterServiceLayer {

    FlooringMasterDao dao = new FlooringMasterDaoFileImpl();
    ProductDao productDao = new ProductDaoFileImpl();
    TaxRateDao taxRateDao = new TaxRateDaoFileImpl();

    @Override
    public Order getOrder(String ld, String orderNumber) {
        return dao.getOrder(ld, orderNumber);
    }

    @Override
    public Order addOrder(String orderDate, Order order) {

        return dao.addOrder(orderDate, order);

    }

    @Override
    public Order removeOrder(String ld, String orderNumber) {
        return dao.removeOrder(ld, orderNumber);
        //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrderDay getOrderDayByDate(String date) throws FlooringMasterNoOrdersForDateException {
        return dao.getOrderDayByDate(date);
    }

    @Override
    public List<Order> getOrdersByDate(String date) throws FlooringMasterNoOrdersForDateException {
        return dao.getOrdersByDate(date);
    }

    @Override
    public void saveAllOrders() {
        dao.saveAllOrders();

    }

    public boolean validateOrder(Order order) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    @Override
    public Product getProduct(String prodType) throws ProductNotFoundException {
        return productDao.getProduct(prodType);
    }

    @Override
    public List<TaxRate> getAllTaxRates() {
        return taxRateDao.getAllTaxRates();
    }

    @Override
    public TaxRate getTaxRate(String state) throws TaxRateNotFoundException {
        return taxRateDao.getTaxRate(state);
    }

    @Override
    public int getNextOrderNumber() {
        return dao.getNextOrderNumber();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
