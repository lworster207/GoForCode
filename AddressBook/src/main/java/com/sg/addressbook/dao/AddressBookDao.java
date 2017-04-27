/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.addressbook.dao;

import com.sg.addressbook.dto.Address;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface AddressBookDao {

    // need to be able to add an address to the address book
    public Address addAddress(Address address) throws AddressBookDaoException;

    public Address deleteAddress(Address address) throws AddressBookDaoException;

    public Address getAddressByLastName(String lastName);

    public int getAddressCount();

    public List<Address> getAddresses();

    public void initAddresses();

}
