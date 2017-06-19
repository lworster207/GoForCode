/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.dao;

import com.sg.superhero.model.Address;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface AddressDao {

    public Address addAddress(String addressId, Address address);

    public Address deleteAddress(String addressId);

    public Address updateAddress(Address address);

    public Address getAddress(String addressId);

    public Address findAddress(Address address);

    public List<Address> getAllAddresses();

}
