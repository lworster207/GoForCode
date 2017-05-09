/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.addressbook;

import com.sg.addressbook.controller.AddressBookController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {

        /* UserIO myIo = new UserIOConsoleImpl();
        AddressBookView myView = new AddressBookView(myIo);
        AddressBookDao myDao = new AddressBookDaoImpl();

        AddressBookController controller
                = new AddressBookController(myDao, myView);

         */
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        AddressBookController controller = ctx.getBean("controller", AddressBookController.class);
        controller.run();
    }
}
