/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.dao;

import com.sg.superhero.model.Contact;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface ContactDao {

    public Contact addContact(Integer contactId, Contact contact);

    public Contact deleteContact(Integer contactId);

    public Contact deleteContactWithAddress(Integer contactId);

    public Contact updateContact(Integer contactId, Contact contact);

    public Contact getContact(Integer contactId);

    public List<Contact> getAllContacts();
}
