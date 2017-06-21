/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.dao;

import com.sg.supercontact.dao.ContactDao;
import com.sg.superhero.model.Hero;
import com.sg.superhero.model.HeroPower;
import com.sg.superhero.model.SuperPower;
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
public class HeroDaoDbImpl implements HeroDao {

    private static SuperPowerDao spDao = new SuperPowerDaoDbImpl();
    private static ContactDao contactDao = new ContactDaoDbImpl();

    private static final String SQL_INSERT_ITEM
            = "insert into Hero "
            + "(Name, Description) "
            + "values (?,?)";

    private static final String SQL_DELETE_ITEM
            = "delete from Hero where HeroId = ?";

    private static final String SQL_SELECT_ITEM
            = "select * from Hero where HeroId = ?";

    private static final String SQL_UPDATE_ITEM
            = "update Hero set "
            + "Description = ? "
            + "where HeroId = ?";

    private static final String SQL_SELECT_ALL_ITEMS
            = "select * from Hero";

    private static final String SQL_SELECT_ALL_AND_SUPERPOWERS
            = "SELECT h.HeroId, h.ContactId, h.Name as HeroName,h.Description as HeroDescription,sp.Description as SuperPower FROM Hero h "
            + "join HeroSuperPower on HeroSuperPower.HeroId = h.HeroId "
            + "join SuperPower sp on sp.SuperPowerId=HeroSuperPower.SuperPowerId";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Hero addHero(String heroId, Hero hero) {
        jdbcTemplate.update(SQL_INSERT_ITEM,
                hero.getHeroName(), hero.getDescription());

        // query the database for the id that was just assigned to the new
        // row in the database
        Integer newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        // set the new id value on the item object and return it
        hero.setHeroId(newId.toString());

        return hero;

    }

    @Override
    public Hero deleteHero(String heroId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Hero updateHero(String heroId, Hero hero) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Hero getHero(String heroId) {
        return jdbcTemplate.queryForObject(SQL_SELECT_ITEM,
                new HeroMapper(), heroId);
    }

    @Override
    public List<Hero> getAllHeroes() {
        return jdbcTemplate.query(SQL_SELECT_ALL_ITEMS,
                new HeroMapper());
    }

    @Override
    public List<HeroPower> getAllHeroesAndPowers() {
        return jdbcTemplate.query(SQL_SELECT_ALL_AND_SUPERPOWERS,
                new HeroPowerMapper());
    }

    private static final class HeroPowerMapper implements RowMapper<HeroPower> {

        public HeroPower mapRow(ResultSet rs, int rowNum) throws SQLException {

            HeroPower hp = new HeroPower();
            hp.setHeroId(rs.getString("HeroId"));
            hp.setHeroName(rs.getString("HeroName"));
            hp.setHeroDescription(rs.getString("HeroDescription"));
            hp.setSuperPower(rs.getString("SuperPower"));
            return hp;
        }

    }

    private static final class HeroMapper implements RowMapper<Hero> {

        public Hero mapRow(ResultSet rs, int rowNum) throws SQLException {

            SuperPower sp = new SuperPower();
            Hero hero = new Hero();
            hero.setHeroId(rs.getString("HeroId"));
            hero.setHeroName(rs.getString("Name"));
            hero.setDescription(rs.getString("Description"));
            return hero;
        }

    }
}
