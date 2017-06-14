/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.addressbookspringmvc.dao;

import com.sg.addressbookspringmvc.model.Address;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class AddressBookDaoTest {

    AddressBookDao dao;

    public AddressBookDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        ApplicationContext ctx
                = new ClassPathXmlApplicationContext(
                        "test-applicationContext.xml");
        dao = ctx.getBean("addressBookDao", AddressBookDao.class);

        // remove all of the Contacts
        List<Address> addresses = dao.getAddresses();
        for (Address currentAddress : addresses) {
            dao.deleteAddress(currentAddress);
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addAddress method, of class AddressBookDao.
     */
    @Test
    public void testAddAddress() {
        System.out.println("addAddress");
        Address newAddress = new Address("First", "Last", "street", "city", "state", "postcode");
        Address expResult;

        expResult = dao.addAddress(newAddress);
        assertEquals(expResult, newAddress);

    }

    /**
     * Test of deleteAddress method, of class AddressBookDao.
     */
    @Test
    public void testDeleteAddress() {
        Address newAddress = new Address("First", "Last", "street", "city", "state", "postcode");
        Address newAddress2 = new Address("First2", "Last2", "street2", "city2", "state2", "postcode2");

        dao.addAddress(newAddress);
        dao.addAddress(newAddress2);

        dao.deleteAddress(newAddress);
        assertEquals(1, dao.getAddressCount());

    }

    @Test
    public void testDeleteAddressById() {
        Address newAddress = new Address("First", "Last", "street", "city", "state", "postcode");
        Address newAddress2 = new Address("First2", "Last2", "street2", "city2", "state2", "postcode2");
        Address expAddress;
        Address resultAddress;

        expAddress = dao.addAddress(newAddress);
        resultAddress = dao.getAddressById(expAddress.getAddressId());
        assertEquals(expAddress, resultAddress);

        dao.deleteAddressById(expAddress.getAddressId());
        resultAddress = dao.getAddressById(expAddress.getAddressId());

        assertEquals(null, resultAddress);

    }

    /**
     * Test of getAddressCount method, of class AddressBookDao.
     */
    @Test
    public void testGetAddressCount() {
        Address newAddress = new Address("First", "Last", "street", "city", "state", "postcode");

        assertEquals(0, dao.getAddressCount());

        dao.addAddress(newAddress);
        assertEquals(1, dao.getAddressCount());

    }

    /**
     * Test of getAddresses method, of class AddressBookDao.
     */
    @Test
    public void testGetAddresses() {
        Address newAddress = new Address("First", "Last", "street", "city", "state", "postcode");

        dao.addAddress(newAddress);
        assertEquals(1, dao.getAddresses().size());
    }

}
