/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.addressbookspringmvc.dao;

import com.sg.addressbookspringmvc.model.Address;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public class AddressBookDaoInMemImpl implements AddressBookDao {

    private Map<Long, Address> addresses = new HashMap<>();
    private static long addressIdCounter = 0;

    @Override
    public Address addAddress(Address address) {

        // assign the current counter values as the contactid
        address.setAddressId(addressIdCounter);
        // increment the counter so it is ready for use for the
        // next contact
        addressIdCounter++;
        addresses.put(address.getAddressId(), address);
        return address;
    }

    @Override
    public Address deleteAddress(Address address) {

        Address deletedAddress = addresses.remove(address.getAddressId());

        return deletedAddress;
    }

    @Override
    public Address deleteAddressById(long id) {
        Address deletedAddress = addresses.remove(id);

        return deletedAddress;
    }

    @Override
    public Address getAddressById(long addressId) {
        return addresses.get(addressId);
    }

    @Override
    public int getAddressCount() {

        return addresses.size();
    }

    @Override
    public List<Address> getAddresses() {
        // return ( ( List<address> ) addresses) );
        Collection<Address> c = addresses.values();
        return new ArrayList(c);
    }

}
