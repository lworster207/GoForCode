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

    public Address addAddress(Integer addressId, Address address);

    public Address deleteAddress(Integer addressId);

    public Address updateAddress(Integer addressId, Address address);

    public Address getAddress(Integer addressId);

    public Address findAddress(Address address);

    public List<Address> getAllAddresses();

}
