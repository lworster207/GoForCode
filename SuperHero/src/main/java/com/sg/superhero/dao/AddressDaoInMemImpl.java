/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.dao;

import com.sg.superhero.model.Address;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public class AddressDaoInMemImpl implements AddressDao {

    public Map<String, Address> addresses = new HashMap<>();

    @Override
    public Address addAddress(String addressId, Address address) {
        addresses.put(addressId, address);
        return addresses.get(addressId);
    }

    @Override
    public Address deleteAddress(String addressId) {
        Address removedAddress = addresses.get(addressId);
        addresses.remove(addressId);
        return (removedAddress);

    }

    @Override
    public Address updateAddress(String addressId, Address address) {
        addresses.get(addressId).setCity(address.getCity());
        addresses.get(addressId).setPostCode(address.getPostCode());
        addresses.get(addressId).setStateProvince(address.getStateProvince());
        addresses.get(addressId).setStreetAddress(address.getStreetAddress());
        return addresses.get(addressId);
    }

    @Override
    public Address getAddress(String addressId) {
        return addresses.get(addressId);
    }

    @Override
    public List<Address> getAllAddresses() {
        Collection<Address> c = addresses.values();
        return new ArrayList(c);
    }

    @Override
    public Address findAddress(Address address) {
        //  return addresses.values().stream().filter().collect(Collectors.toList());
        //  return (items.values().stream().filter(item -> item.getQuantity() > 0).collect(Collectors.toList()));
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
