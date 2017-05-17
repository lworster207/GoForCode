/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmaster.service;

import com.sg.flooringmaster.dao.FlooringMasterDao;
import com.sg.flooringmaster.dao.FlooringMasterDaoFileImpl;
import com.sg.flooringmaster.dto.Order;
import java.time.LocalDate;

/**
 *
 * @author apprentice
 */
public class FlooringMasterServiceLayerImpl implements FlooringMasterServiceLayer {

    FlooringMasterDao dao = new FlooringMasterDaoFileImpl();

    @Override
    public Order getOrder(LocalDate ld, int orderNumber) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order addOrder(Order order) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order removeOrder(LocalDate ld, int orderNumber) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void getOrdersByDate(String date) {
        dao.getOrdersByDate(date);
    }

    @Override
    public void saveAllOrders() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean validateOrder(Order order) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
