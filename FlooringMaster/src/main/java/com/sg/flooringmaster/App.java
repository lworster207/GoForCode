/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmaster;

import com.sg.flooringmaster.controller.FlooringMasterController;
import com.sg.flooringmaster.dao.FlooringMasterDao;
import com.sg.flooringmaster.dao.FlooringMasterDaoFileImpl;
import com.sg.flooringmaster.service.FlooringMasterServiceLayer;
import com.sg.flooringmaster.service.FlooringMasterServiceLayerImpl;
import com.sg.flooringmaster.ui.FlooringMasterView;
import com.sg.flooringmaster.ui.UserIO;
import com.sg.flooringmaster.ui.UserIOConsoleImpl;

/**
 *
 * @author apprentice
 */
public class App {

    public static void main(String[] args) {
        UserIO myIo = new UserIOConsoleImpl();
        FlooringMasterView myView = new FlooringMasterView(myIo);

        FlooringMasterDao myDao = new FlooringMasterDaoFileImpl();
        // Instantiate the Audit DAO
        //    FlooringMasterAuditDao myAuditDao = new FlooringMasterAuditDaoFileImpl();
        // Instantiate the Service Layer and wire the DAO and Audit DAO into it
        FlooringMasterServiceLayer myService = new FlooringMasterServiceLayerImpl();
        // FlooringMasterServiceLayer myService = new FlooringMasterServiceLayerImpl(myDao);
        // Instantiate the Controller and wire the Service Layer into it
        FlooringMasterController controller = new FlooringMasterController(myService, myView);

        controller.run();
    }
}
