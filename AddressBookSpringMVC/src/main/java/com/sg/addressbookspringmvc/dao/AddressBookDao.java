/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.addressbookspringmvc.dao;

import com.sg.addressbookspringmvc.model.Address;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface AddressBookDao {

    // need to be able to add an address to the address book
    public Address addAddress(Address address);

    public Address deleteAddress(Address address);

    public Address deleteAddressById(long id);

    public Address getAddressById(long id);

    public int getAddressCount();

    public List<Address> getAddresses();
}
