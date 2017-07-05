/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.dao;

import com.sg.superhero.model.Location;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class LocationDaoDbImpl implements LocationDao {

    AddressDao addressDao = new AddressDaoDbImpl();

    private static final String SQL_INSERT_LOCATION
            = "insert into Location "
            + "(Name, Description, AddressId, Latitude, Longitude) "
            + "values (?,?,?,?,?)";

    private static final String SQL_DELETE_LOCATION
            = "delete from Location where LocationId = ?";

    private static final String SQL_SELECT_LOCATION
            = "select * from Location where LocationId = ?";

    private static final String SQL_UPDATE_LOCATION
            = "update Location set "
            + "Name = ?, "
            + "Description = ?, "
            + "AddressId = ?, "
            + "Latitude = ?, "
            + "Longitude = ? "
            + "where LocationId = ?";

    private static final String SQL_SELECT_ALL_LOCATIONS
            = "select * from Location";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Location addLocation(String locationId, Location location) {
        jdbcTemplate.update(SQL_INSERT_LOCATION,
                location.getLocationName(),
                location.getLocationDescription(),
                location.getAddressId(),
                location.getLatitude(),
                location.getLongitude());
        Integer newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        location.setLocationId(newId.toString());
        return location;

    }

    @Override
    public Location deleteLocation(String locationId) {
        Location location = getLocation(locationId);
        jdbcTemplate.update(SQL_DELETE_LOCATION, locationId);
        return location;
    }

    @Override
    public Location updateLocation(String locationId, Location location) {
        jdbcTemplate.update(SQL_UPDATE_LOCATION,
                location.getLocationName(),
                location.getLocationDescription(),
                location.getAddressId(),
                location.getLatitude(),
                location.getLongitude(),
                location.getLocationId());
        return getLocation(locationId);
    }

    @Override
    public Location getLocation(String locationId) {
        return jdbcTemplate.queryForObject(SQL_SELECT_LOCATION,
                new LocationMapper(), locationId);
    }

    @Override
    public List<Location> getAllLocations() {
        return jdbcTemplate.query(SQL_SELECT_ALL_LOCATIONS,
                new LocationMapper());
    }

    private static final class LocationMapper implements RowMapper<Location> {

        public Location mapRow(ResultSet rs, int rowNum) throws SQLException {

            Location location = new Location();
            location.setLocationId(rs.getString("LocationId"));
            location.setLocationName(rs.getString("Name"));
            location.setLocationDescription(rs.getString("Description"));
            location.setLatitude(rs.getDouble("Latitude"));
            location.setLongitude(rs.getDouble("Longitude"));
            location.setAddressId(rs.getString("AddressId"));

            return location;
        }

    }
}
