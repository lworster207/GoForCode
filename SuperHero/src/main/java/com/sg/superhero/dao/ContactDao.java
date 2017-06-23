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

    public Contact addContact(String contactId, Contact contact);

    public Contact deleteContact(String contactId);

    public Contact updateContact(String contactId, Contact contact);

    public Contact getContact(String contactId);

    public List<Contact> getAllContacts();
}
