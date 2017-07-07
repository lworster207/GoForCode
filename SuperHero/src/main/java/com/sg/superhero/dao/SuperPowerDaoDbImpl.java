/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.dao;

import com.sg.superhero.model.SuperPower;
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
public class SuperPowerDaoDbImpl implements SuperPowerDao {

    private static final String SQL_INSERT_ITEM
            = "insert into SuperPower "
            + "(Description) "
            + "values (?)";
    private static final String SQL_DELETE_ITEM
            = "delete from SuperPower where SuperPowerId = ?";

    private static final String SQL_SELECT_ITEM
            = "select * from SuperPower where SuperPowerId = ?";

    private static final String SQL_UPDATE_ITEM
            = "update SuperPower set "
            + "Description = ? "
            + "where SuperPowerId = ?";

    private static final String SQL_SELECT_ALL_ITEMS
            = "select * from SuperPower";

    private static final String SQL_SELECT_SUPERPOWERS_BY_HERO
            = "select s.SuperPowerId, s.Description from SuperPower s "
            + "join HeroSuperPower on s.SuperPowerId = HeroSuperPower.SuperPowerId "
            + "where HeroSuperPower.HeroId = ?";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public SuperPower getSuperPower(Integer superPowerId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_ITEM,
                    new SuperPowerMapper(), superPowerId);
        } catch (EmptyResultDataAccessException ex) {
            // there were no results for the given item id - we just
            // want to return null in this case
            return null;
        }
        //return superPowers.get(superPowerId);
    }

    @Override
    public List<SuperPower> getSuperPowersByHero(Integer heroId) {
        try {
            return jdbcTemplate.query(SQL_SELECT_SUPERPOWERS_BY_HERO,
                    new SuperPowerHeroMapper(), heroId);
        } catch (EmptyResultDataAccessException ex) {
            // there were no results for the given item id - we just
            // want to return null in this case
            return null;
        }
    }

    @Override
    public SuperPower addSuperPower(Integer superPowerId, SuperPower superPower) {
        SuperPower newSuperPower = getSuperPower(superPowerId);
        if (newSuperPower == null) {
            jdbcTemplate.update(SQL_INSERT_ITEM,
                    superPower.getDescription());

            // query the database for the id that was just assigned to the new
            // row in the database
            Integer newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
            // set the new id value on the item object and return it
            superPower.setSuperPowerId(newId);
            newSuperPower = getSuperPower(newId);
        }

        return newSuperPower;

    }

    @Override
    public SuperPower deleteSuperPower(Integer superPowerId) {

        SuperPower removedItem = getSuperPower(superPowerId);
        jdbcTemplate.update(SQL_DELETE_ITEM, superPowerId);
        return removedItem;

    }

    @Override
    public SuperPower updateSuperPower(Integer superPowerId, SuperPower superPower) {

        SuperPower sp = getSuperPower(superPowerId);
        if (sp != null) {
            jdbcTemplate.update(SQL_UPDATE_ITEM, superPower.getDescription(), superPowerId);
        }
        return sp;
    }

    @Override
    public List<SuperPower> getAllSuperPowers() {
        return jdbcTemplate.query(SQL_SELECT_ALL_ITEMS,
                new SuperPowerMapper());

    }

    private static final class SuperPowerMapper implements RowMapper<SuperPower> {

        public SuperPower mapRow(ResultSet rs, int rowNum) throws SQLException {

            SuperPower superPower = new SuperPower();
            superPower.setSuperPowerId(rs.getInt("SuperPowerId"));
            superPower.setDescription(rs.getString("Description"));
            superPower.setSelected("");

            return superPower;
        }

    }

    private static final class SuperPowerHeroMapper implements RowMapper<SuperPower> {

        public SuperPower mapRow(ResultSet rs, int rowNum) throws SQLException {

            SuperPower superPower = new SuperPower();
            superPower.setSuperPowerId(rs.getInt("SuperPowerId"));
            superPower.setDescription(rs.getString("Description"));
            superPower.setSelected("selected");
            return superPower;
        }

    }

}
