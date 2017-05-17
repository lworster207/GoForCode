/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmaster.controller;

import com.sg.flooringmaster.service.FlooringMasterServiceLayer;
import com.sg.flooringmaster.ui.FlooringMasterView;

/**
 *
 * @author apprentice
 */
public class FlooringMasterController {

    private FlooringMasterView view;
    private FlooringMasterServiceLayer service;

    public FlooringMasterController(FlooringMasterServiceLayer service, FlooringMasterView view) {
        this.view = view;
        this.service = service;

    }

    public void run() {
        boolean keepGoing = true;
        service.getOrdersByDate("051717");
    }
}
