/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.service;

import com.sg.superhero.model.Address;
import com.sg.superhero.model.Contact;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface SuperHeroServiceLayer {

    public Contact addContact(Integer contactId, Contact contact, Address address);

    public Contact deleteContact(Integer contactId, Integer addressId);

    public Contact deleteContactWithAddress(Integer contactId);

    public Contact updateContact(Integer contactId, Contact contact, Address address);

    public Contact getContact(Integer contactId);

    public List<Contact> getAllContacts();

    public Address addAddress(Integer addressId, Address address);

    public Address deleteAddress(Integer addressId);

    public Address updateAddress(Integer addressId, Address address);

    public Address getAddress(Integer addressId);

    public Address findAddress(Address address);

    public List<Address> getAllAddresses();

}
