/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmaster.service;

import com.sg.flooringmaster.dao.FlooringMasterDao;
import com.sg.flooringmaster.dao.FlooringMasterDaoFileImpl;
import com.sg.flooringmaster.dao.OrderDay;
import com.sg.flooringmaster.dto.Order;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class FlooringMasterServiceLayerImpl implements FlooringMasterServiceLayer {

    FlooringMasterDao dao = new FlooringMasterDaoFileImpl();

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
    public OrderDay getOrderDayByDate(String date) {
        return dao.getOrderDayByDate(date);
    }

    @Override
    public List<Order> getOrdersByDate(String date) {
        return dao.getOrdersByDate(date);
    }

    @Override
    public void saveAllOrders() {
        dao.saveAllOrders();

    }

    private boolean validateOrder(Order order) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
