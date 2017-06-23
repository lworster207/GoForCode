/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.dao;

import com.sg.superhero.model.Contact;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

/**
 *
 * @author apprentice
 */
public class ContactDaoTest {

    ContactDao contactDao;
    AddressDao addressDao;

    public ContactDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        contactDao = ctx.getBean("contactDao", ContactDao.class);
        addressDao = ctx.getBean("addressDao", AddressDao.class);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addContact method, of class ContactDao.
     */
    @Test
    public void testAddContact() {
        Contact contact = new Contact();
        contact.setContactId("1");
        contact.setFirstName("Peter");
        contact.setLastName("Parker");
        contact.setAddressId(null);
        contact.setEmail("test@email.com");
        contact.setPhone("1112223333");
        Contact newContact = contactDao.addContact("1", contact);
        assertEquals(newContact, contact);
    }

    /**
     * Test of deleteContact method, of class ContactDao.
     */
    @Test
    public void testDeleteContact() {
        Contact contact = new Contact();
        contact.setContactId("1");
        contact.setFirstName("Peter");
        contact.setLastName("Parker");
        contact.setAddressId(null);
        contact.setEmail("test@email.com");
        contact.setPhone("1112223333");
        Contact newContact = contactDao.addContact("1", contact);
        //assertEquals(newContact, contact);
        Contact result = contactDao.deleteContact(newContact.getContactId());
        //assertEquals(result, newContact);
        try {
            contact = contactDao.getContact(newContact.getContactId());
            assertEquals(contact, null);
        } catch (EmptyResultDataAccessException e) {
            assertEquals(1, 1);
        }
    }

    /**
     * Test of updateContact method, of class ContactDao.
     */
    @Test
    public void testUpdateContact() {
    }

    /**
     * Test of getContact method, of class ContactDao.
     */
    @Test
    public void testGetContact() {

    }

    /**
     * Test of getAllContacts method, of class ContactDao.
     */
    @Test
    public void testGetAllContacts() {
    }

}
