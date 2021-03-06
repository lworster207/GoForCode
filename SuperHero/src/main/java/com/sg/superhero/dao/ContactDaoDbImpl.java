/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.dao;

import com.sg.superhero.model.Contact;
import com.sg.superhero.service.SuperHeroServiceLayer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class ContactDaoDbImpl implements ContactDao {

    private static SuperHeroServiceLayer service;
    AddressDao addressDao = new AddressDaoDbImpl();

    private static final String SQL_INSERT_ITEM
            = "insert into Contact "
            + "(FirstName, LastName, Phone, Email, AddressId) "
            + "values (?,?,?,?,?)";

    private static final String SQL_DELETE_ITEM
            = "delete from Contact where ContactId = ?";
    private static final String SQL_DELETE_ITEM_WITH_ADDRESS
            = "delete c, a "
            + "from Contact c "
            + "join Address a on c.AddressId = a.AddressId "
            + " where ContactId = ?";

    private static final String SQL_SELECT_ITEM
            = "select * from Contact where ContactId = ?";

    private static final String SQL_UPDATE_ITEM
            = "update Contact set "
            + "FirstName = ?, "
            + "LastName = ?, "
            + "Phone = ?, "
            + "Email = ?, "
            + "AddressId = ? "
            + "where ContactId = ?";

    private static final String SQL_SELECT_ALL_ITEMS
            = "select * from Contact";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Contact addContact(Integer contactId, Contact contact) {

        jdbcTemplate.update(SQL_INSERT_ITEM,
                contact.getFirstName(),
                contact.getLastName(),
                contact.getPhone(),
                contact.getEmail(),
                contact.getAddressId());

        // query the database for the id that was just assigned to the new
        // row in the database
        Integer newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        // set the new id value on the item object and return it
        contact.setContactId(newId);

        return contact;
    }

    @Override
    public Contact deleteContact(Integer contactId) {
        Contact removedContact = null;

        if (contactId != null) {
            removedContact = getContact(contactId);
            removedContact.setContactId(null);
            updateContact(contactId, removedContact);
            try {
                jdbcTemplate.update(SQL_DELETE_ITEM, contactId);
            } catch (DataIntegrityViolationException e) {
                // shared contact. cannot delete.
            }
        }

        return removedContact;
    }

    @Override
    public Contact deleteContactWithAddress(Integer contactId) {
        Contact removedContact = getContact(contactId);
        jdbcTemplate.update(SQL_DELETE_ITEM_WITH_ADDRESS, contactId);
        return removedContact;
    }

    @Override
    public Contact updateContact(Integer contactId, Contact contact) {
        jdbcTemplate.update(SQL_UPDATE_ITEM,
                contact.getFirstName(),
                contact.getLastName(),
                contact.getPhone(),
                contact.getEmail(),
                contact.getAddressId(),
                contactId);
        return getContact(contactId);
    }

    @Override
    public Contact getContact(Integer contactId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_ITEM,
                    new ContactMapper(), contactId);
        } catch (EmptyResultDataAccessException ex) {
            // there were no results for the given item id - we just
            // want to return null in this case
            return null;
        }
    }

    @Override
    public List<Contact> getAllContacts() {
        try {
            return jdbcTemplate.query(SQL_SELECT_ALL_ITEMS,
                    new ContactMapper());
        } catch (EmptyResultDataAccessException ex) {
            // there were no results for the given item id - we just
            // want to return null in this case
            return null;
        }
    }

    private static final class ContactMapper implements RowMapper<Contact> {

        public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {

            Contact contact = new Contact();
            contact.setContactId(rs.getInt("ContactId"));
            contact.setFirstName(rs.getString("FirstName"));
            contact.setLastName(rs.getString("LastName"));
            contact.setPhone(rs.getString("Phone"));
            contact.setEmail(rs.getString("Email"));
            contact.setAddressId(rs.getInt("AddressId"));

            return contact;
        }

    }
}
