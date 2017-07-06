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

    public Contact addContact(String contactId, Contact contact, Address address);

    public Contact deleteContact(String contactId, String addressId);

    public Contact deleteContactWithAddress(String contactId);

    public Contact updateContact(String contactId, Contact contact, Address address);

    public Contact getContact(String contactId);

    public List<Contact> getAllContacts();

    public Address addAddress(String addressId, Address address);

    public Address deleteAddress(String addressId);

    public Address updateAddress(String addressId, Address address);

    public Address getAddress(String addressId);

    public Address findAddress(Address address);

    public List<Address> getAllAddresses();

}
