/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.dao;

import com.sg.superhero.model.Sighting;
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
public class SightingsDaoDbImpl implements SightingsDao {

    private static final String SQL_INSERT_SIGHTING
            = "insert into Sighting "
            + "(locationId, heroId, Date) "
            + "values (?,?,?)";

    private static final String SQL_DELETE_SIGHTING
            = "delete from Sighting where SightingId = ?";

    private static final String SQL_SELECT_SIGHTING
            = "select * from Sighting where SightingId = ?";

    private static final String SQL_UPDATE_SIGHTING
            = "update Sighting set "
            + "locationId = ?, "
            + "heroId = ?, "
            + "AddressId = ?, "
            + "Date = ? "
            + "where SightingId = ?";

    private static final String SQL_SELECT_ALL_SIGHTINGS
            = "select * from Sighting";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Sighting addSighting(String sightingId, Sighting sighting) {
        jdbcTemplate.update(SQL_INSERT_SIGHTING,
                sighting.getLocationId(),
                sighting.getHeroId(),
                sighting.getDate());

        Integer newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        sighting.setSightingId(newId.toString());
        return sighting;

    }

    @Override
    public Sighting deleteSighting(String sightingId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Sighting updateSighting(String sightingId, Sighting sighting) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Sighting getSighting(String sightingId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Sighting> getAllSightings() {
        return jdbcTemplate.query(SQL_SELECT_ALL_SIGHTINGS,
                new SightingMapper());
    }

    public static final class SightingMapper implements RowMapper<Sighting> {

        public Sighting mapRow(ResultSet rs, int rowNum) throws SQLException {

            Sighting sighting = new Sighting();
            sighting.setLocationId(rs.getString("LocationId"));
            sighting.setHeroId(rs.getString("HeroId"));
            sighting.setDate(rs.getString("Date"));
            return sighting;
        }

    }

}
