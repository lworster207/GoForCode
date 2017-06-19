/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.dao;

import com.sg.superhero.model.Address;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author apprentice
 */
public class AddressDaoTest {

    AddressDao dao = new AddressDaoInMemImpl();

    public AddressDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        List<Address> addressList = dao.getAllAddresses();
        for (Address address : addressList) {
            dao.deleteAddress(address.getAddressId());
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addAddress method, of class AddressDao.
     */
    @Test
    public void testAddAddress() {
        System.out.println("addAddress");
        String addressId = "1";
        Address address = new Address();
        address.setAddressId(addressId);
        address.setStreetAddress("123 Elm Street");
        address.setCity("Portland");
        address.setStateProvince("Maine");
        address.setPostCode("04101");

        Address result = dao.addAddress(addressId, address);
        assertEquals(address, result);

    }

    /**
     * Test of deleteAddress method, of class AddressDao.
     */
    @Test
    public void testDeleteAddress() {
        System.out.println("deleteAddress");
        String addressId = "1";
        Address address = new Address();
        address.setAddressId(addressId);
        address.setStreetAddress("123 Elm Street");
        address.setCity("Portland");
        address.setStateProvince("Maine");
        address.setPostCode("04101");

        Address result = dao.addAddress(addressId, address);
        assertEquals(address, result);

        result = dao.deleteAddress(addressId);
        assertEquals(address, result);

        result = dao.getAddress(addressId);
        assertEquals(null, result);

    }

    /**
     * Test of updateAddress method, of class AddressDao.
     */
    @Test
    public void testUpdateAddress() {
        System.out.println("updateAddress");
        String addressId = "1";
        Address address = new Address();
        address.setAddressId(addressId);
        address.setStreetAddress("123 Elm Street");
        address.setCity("Portland");
        address.setStateProvince("Maine");
        address.setPostCode("04101");

        Address result = dao.addAddress(addressId, address);
        assertEquals(address, result);

        address.setStreetAddress("234 Elm Street");
        result = dao.updateAddress(address);
        assertEquals(address, result);
    }

    /**
     * Test of getAddress method, of class AddressDao.
     */
    @Test
    public void testGetAddress() {
        System.out.println("getAddress");
        String addressId = "1";
        Address address = new Address();
        address.setAddressId(addressId);
        address.setStreetAddress("123 Elm Street");
        address.setCity("Portland");
        address.setStateProvince("Maine");
        address.setPostCode("04101");

        Address result = dao.addAddress(addressId, address);
        assertEquals(address, result);

        result = dao.getAddress(addressId);
        assertEquals(address, result);
    }

    /**
     * Test of findAddress method, of class AddressDao.
     */
    @Test
    public void testFindAddress() {
        System.out.println("findAddress");
        Address address = null;
        Address expResult = null;
        //Address result = dao.findAddress(address);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

}
