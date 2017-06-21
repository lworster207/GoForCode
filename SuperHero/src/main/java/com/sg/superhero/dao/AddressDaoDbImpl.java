/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.dao;

import com.sg.superhero.model.Address;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author apprentice
 */
public class AddressDaoDbImpl implements AddressDao {

    private static final String SQL_INSERT_ADDRESS
            = "insert into Address "
            + "(StreetAddress, City, State, PostCode) "
            + "values (?, ?, ?, ?)";

    private static final String SQL_DELETE_ADDRESS
            = "delete from Address where AddressId = ?";

    private static final String SQL_SELECT_ADDRESS
            = "select * from Address where AddressId = ?";

    private static final String SQL_UPDATE_ADDRESS
            = "update Address set "
            + "StreetAddress = ? "
            + "City = ? "
            + "State = ? "
            + "PostCode = ? "
            + "where AddressId = ?";

    private static final String SQL_SELECT_ALL_ADDRESSES
            = "select * from Address";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Address addAddress(String addressId, Address address) {

        jdbcTemplate.update(SQL_INSERT_ADDRESS,
                address.getStreetAddress(),
                address.getCity(),
                address.getStateProvince(),
                address.getPostCode());

        // query the database for the id that was just assigned to the new
        // row in the database
        Integer newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        // set the new id value on the item object and return it
        address.setAddressId(newId.toString());
        return getAddress(newId.toString());

    }

    @Override
    public Address deleteAddress(String addressId) {
        Address removedItem = getAddress(addressId);
        jdbcTemplate.update(SQL_DELETE_ADDRESS, addressId);
        return removedItem;
    }

    @Override
    public Address updateAddress(String addressId, Address address) {
        jdbcTemplate.update(SQL_UPDATE_ADDRESS,
                address.getStreetAddress(),
                address.getCity(),
                address.getStateProvince(),
                address.getPostCode(),
                addressId);

        return getAddress(addressId);
    }

    @Override
    public Address getAddress(String addressId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_ADDRESS,
                    new AddressMapper(), addressId);
        } catch (EmptyResultDataAccessException ex) {
            // there were no results for the given item id - we just
            // want to return null in this case
            return null;
        }
    }

    @Override
    public Address findAddress(Address address) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Address> getAllAddresses() {
        return jdbcTemplate.query(SQL_SELECT_ALL_ADDRESSES,
                new AddressMapper());
    }

    private static final class AddressMapper implements RowMapper<Address> {

        public Address mapRow(ResultSet rs, int rowNum) throws SQLException {

            Address address = new Address();
            address.setAddressId(rs.getString("AddressId"));
            address.setStreetAddress(rs.getString("StreetAddress"));
            address.setCity(rs.getString("City"));
            address.setStateProvince(rs.getString("State"));
            address.setPostCode(rs.getString("PostCode"));
            return address;
        }

    }
}
