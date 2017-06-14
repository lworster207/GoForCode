/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.addressbookspringmvc.dao;

import com.sg.addressbookspringmvc.model.Address;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class AddressBookDaoDbImpl implements AddressBookDao {

    private static final String SQL_INSERT_ADDRESS
            = "insert into AddressBook "
            + "(first_name, last_name, street, city, state, post_code) "
            + "values (?, ?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_ADDRESS
            = "delete from AddressBook where addressbook_id = ?";
    private static final String SQL_SELECT_ADDRESS
            = "select * from AddressBook where addressbook_id = ?";
    private static final String SQL_UPDATE_CONTACT
            = "update AddressBook set "
            + "first_name = ?, last_name = ?, street = ?, "
            + "city = ?, state = ?, post_code = ? "
            + "where addressbook_id = ?";
    private static final String SQL_SELECT_ALL_ADDRESSES
            = "select * from AddressBook";

    private static final String SQL_GET_ADDRESS_COUNT
            = "select count(*) from AddressBook";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Address addAddress(Address address) {
        jdbcTemplate.update(SQL_INSERT_ADDRESS,
                address.getFirstName(),
                address.getLastName(),
                address.getStreet(),
                address.getCity(),
                address.getState(),
                address.getPostCode());

        // query the database for the id that was just assigned to the new
        // row in the database
        int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Integer.class);

        address.setAddressId(newId);
        return address;
    }

    @Override
    public Address deleteAddress(Address address) {
        jdbcTemplate.update(SQL_DELETE_ADDRESS, address.getAddressId());
        return address;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Address deleteAddressById(long id) {
        Address address = getAddressById(id);
        deleteAddress(address);
        return (address);

    }

    @Override
    public Address getAddressById(long id) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_ADDRESS,
                    new AddressMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            // there were no results for the given contact id - we just
            // want to return null in this case
            return null;
        }
    }

    @Override
    public int getAddressCount() {
        int count = jdbcTemplate.queryForObject(SQL_GET_ADDRESS_COUNT, Integer.class);
        return count;
    }

    @Override
    public List<Address> getAddresses() {
        return jdbcTemplate.query(SQL_SELECT_ALL_ADDRESSES,
                new AddressMapper());
    }

    private static final class AddressMapper implements RowMapper<Address> {

        public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
            Address address = new Address();
            address.setAddressId(rs.getLong("addressbook_id"));
            address.setFirstName(rs.getString("first_name"));
            address.setLastName(rs.getString("last_name"));
            address.setStreet(rs.getString("street"));
            address.setCity(rs.getString("city"));
            address.setPostCode(rs.getString("post_code"));
            return address;
        }
    }
}
