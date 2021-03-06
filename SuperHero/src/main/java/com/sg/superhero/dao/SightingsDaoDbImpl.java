/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.dao;

import com.sg.superhero.model.Sighting;
import com.sg.superhero.model.SightingLocationHero;
import com.sg.superhero.model.SightingLocationHero.SightingLocationHeroMapper;
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
            + "Date = ? "
            + "where SightingId = ?";

    private static final String SQL_SELECT_ALL_SIGHTINGS
            = "select * from Sighting "
            + "order by Date desc";

    private static final String SQL_SELECT_ALL_SIGHTINGS_BY_LOCATION
            = "select * from Sighting "
            + "where SightingId = ? "
            + "order by Date desc";

    private static final String SQL_SELECT_ALL_SIGHTINGS_BY_HERO
            = "select * from Sighting "
            + "where HeroId = ? "
            + "order by Date desc";
    private static final String SQL_SELECT_ALL_SIGHTINGS_BY_DATE
            = "select * from Sighting "
            + "where Date = ? "
            + "order by Date desc";

    private static final String SQL_SELECT_ALL_SIGHTINGS_DETAIL_BY_LOCATION
            = "select h.HeroId, h.Name as `HeroName`, s.SightingId, s.Date, l.LocationId, l.Name,l.Description from Sighting s "
            + "join Hero h on h.HeroId = s.HeroId "
            + "join Location l on l.LocationId = s.LocationId "
            + "where l.LocationId = ? "
            + "order by Date desc";

    private static final String SQL_SELECT_ALL_SIGHTINGS_DETAIL_BY_HERO
            = "select h.HeroId, h.Name as `HeroName`, s.SightingId, s.Date, l.LocationId, l.Name,l.Description from Sighting s "
            + "join Hero h on h.HeroId = s.HeroId "
            + "join Location l on l.LocationId = s.LocationId "
            + "where h.HeroId = ? "
            + "order by Date desc";

    private static final String SQL_SELECT_ALL_SIGHTINGS_DETAIL_BY_DATE
            = "select h.HeroId, h.Name as `HeroName`, s.SightingId, s.Date, l.LocationId, l.Name,l.Description from Sighting s "
            + "join Hero h on h.HeroId = s.HeroId "
            + "join Location l on l.LocationId = s.LocationId "
            + "where s.Date = ? "
            + "order by Date desc";

    private static final String SQL_SELECT_ALL_SIGHTINGS_DETAIL_STRING
            = "select h.HeroId, h.Name as `HeroName`, s.SightingId, s.Date, l.LocationId, l.Name,l.Description from Sighting s "
            + "join Hero h on h.HeroId = s.HeroId "
            + "join Location l on l.LocationId = s.LocationId "
            + "order by Date desc";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Sighting addSighting(Integer sightingId, Sighting sighting) {
        jdbcTemplate.update(SQL_INSERT_SIGHTING,
                sighting.getLocationId(),
                sighting.getHeroId(),
                sighting.getDate());

        Integer newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        sighting.setSightingId(newId);
        return sighting;

    }

    @Override
    public Sighting deleteSighting(Integer sightingId) {
        Sighting removedSighting = getSighting(sightingId);
        //removedSighting.setHeroId(null);
        //removedSighting.setLocationId(null);
        //updateSighting(sightingId, removedSighting);

        jdbcTemplate.update(SQL_DELETE_SIGHTING, sightingId);
        return removedSighting;
    }

    @Override
    public Sighting updateSighting(Integer sightingId, Sighting sighting) {
        jdbcTemplate.update(SQL_UPDATE_SIGHTING,
                sighting.getLocationId(),
                sighting.getHeroId(),
                sighting.getDate(),
                sighting.getSightingId());
        return getSighting(sightingId);
    }

    @Override
    public Sighting getSighting(Integer sightingId) {

        return jdbcTemplate.queryForObject(SQL_SELECT_SIGHTING,
                new SightingMapper(), sightingId);

    }

    @Override
    public List<Sighting> getAllSightings() {
        return jdbcTemplate.query(SQL_SELECT_ALL_SIGHTINGS,
                new SightingMapper());
    }

    @Override
    public List<SightingLocationHero> getAllSightingsDetailed() {
        return jdbcTemplate.query(SQL_SELECT_ALL_SIGHTINGS_DETAIL_STRING,
                new SightingLocationHeroMapper());
    }

    @Override
    public List<SightingLocationHero> getSightingsByDateDetailed(String date) {
        return jdbcTemplate.query(SQL_SELECT_ALL_SIGHTINGS_DETAIL_BY_DATE,
                new SightingLocationHeroMapper(), date);
    }

    @Override
    public List<SightingLocationHero> getSightingsByHeroDetailed(Integer heroId) {
        return jdbcTemplate.query(SQL_SELECT_ALL_SIGHTINGS_DETAIL_BY_HERO,
                new SightingLocationHeroMapper(), heroId);
    }

    @Override
    public List<SightingLocationHero> getSightingsByLocationDetailed(Integer locationId) {
        return jdbcTemplate.query(SQL_SELECT_ALL_SIGHTINGS_DETAIL_BY_LOCATION,
                new SightingLocationHeroMapper(), locationId);
    }

    @Override
    public List<Sighting> getSightingByLocation(Integer locationId) {
        return jdbcTemplate.query(SQL_SELECT_ALL_SIGHTINGS_BY_LOCATION,
                new SightingMapper(), locationId);
    }

    @Override
    public List<Sighting> getSightingsByHero(Integer heroId) {
        return jdbcTemplate.query(SQL_SELECT_ALL_SIGHTINGS_BY_HERO,
                new SightingMapper(), heroId);
    }

    @Override
    public List<Sighting> getSightingsByDate(String date) {
        return jdbcTemplate.query(SQL_SELECT_ALL_SIGHTINGS_BY_DATE,
                new SightingMapper(), date);
    }

    public static final class SightingMapper implements RowMapper<Sighting> {

        public Sighting mapRow(ResultSet rs, int rowNum) throws SQLException {

            Sighting sighting = new Sighting();
            sighting.setSightingId(rs.getInt("SightingId"));
            sighting.setLocationId(rs.getInt("LocationId"));
            sighting.setHeroId(rs.getInt("HeroId"));
            sighting.setDate(rs.getString("Date"));
            return sighting;
        }

    }

}
