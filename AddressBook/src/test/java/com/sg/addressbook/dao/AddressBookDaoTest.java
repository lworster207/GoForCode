/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.addressbook.dao;

import com.sg.addressbook.dto.Address;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author apprentice
 */
public class AddressBookDaoTest {

    private AddressBookDao dao = new AddressBookDaoImpl();

    public AddressBookDaoTest() {

    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws Exception {
        List<Address> addresses = dao.getAddresses();
        for (Address curAddress : addresses) {
            dao.deleteAddress(curAddress);
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addAddress method, of class AddressBookDao.
     */
    @Test
    public void testAddAddress() throws Exception {
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
    public void testDeleteAddress() throws Exception {
        Address newAddress = new Address("First", "Last", "street", "city", "state", "postcode");
        Address newAddress2 = new Address("First2", "Last2", "street2", "city2", "state2", "postcode2");

        dao.addAddress(newAddress);
        dao.addAddress(newAddress2);

        dao.deleteAddress(newAddress);
        assertEquals(1, dao.getAddressCount());
        assertNull(dao.getAddressByLastName(newAddress.getLastName()));

        dao.deleteAddress(newAddress2);
        assertNull(dao.getAddressByLastName(newAddress2.getLastName()));
        assertEquals(0, dao.getAddressCount());

    }

    /**
     * Test of getAddressByLastName method, of class AddressBookDao.
     */
    @Test
    public void testGetAddressByLastName() throws Exception {
        Address newAddress = new Address("First", "Last", "street", "city", "state", "postcode");
        Address retAddress;

        dao.addAddress(newAddress);
        retAddress = dao.getAddressByLastName(newAddress.getLastName());
        assertEquals(retAddress, newAddress);
    }

    /**
     * Test of getAddressCount method, of class AddressBookDao.
     */
    @Test
    public void testGetAddressCount() throws Exception {
        Address newAddress = new Address("First", "Last", "street", "city", "state", "postcode");

        assertEquals(0, dao.getAddressCount());

        dao.addAddress(newAddress);
        assertEquals(1, dao.getAddressCount());

    }

    /**
     * Test of getAddresses method, of class AddressBookDao.
     */
    @Test
    public void testGetAddresses() throws Exception {
        Address newAddress = new Address("First", "Last", "street", "city", "state", "postcode");

        dao.addAddress(newAddress);
        assertEquals(1, dao.getAddresses().size());
    }

}
