/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmaster;

import com.sg.flooringmaster.controller.FlooringMasterController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class App {

    public static void main(String[] args) {
        //    UserIO myIo = new UserIOConsoleImpl();
        //    FlooringMasterView myView = new FlooringMasterView(myIo);

        //    FlooringMasterDao myDao = new FlooringMasterDaoFileImpl();
        // Instantiate the Audit DAO
        //    FlooringMasterAuditDao myAuditDao = new FlooringMasterAuditDaoFileImpl();
        // Instantiate the Service Layer and wire the DAO and Audit DAO into it
        //    FlooringMasterServiceLayer myService = new FlooringMasterServiceLayerImpl(myDao);
        // FlooringMasterServiceLayer myService = new FlooringMasterServiceLayerImpl(myDao);
        // Instantiate the Controller and wire the Service Layer into it
        //    FlooringMasterController controller = new FlooringMasterController(myService, myView);
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        FlooringMasterController controller = ctx.getBean("controller", FlooringMasterController.class);
        controller.run();
    }
}
