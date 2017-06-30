/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.dao;

import com.sg.superhero.model.Hero;
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
public class HeroSuperpowerDaoDbImpl implements HeroSuperpowerDao {

    private static final String SQL_INSERT_HEROSUPERPOWER
            = "insert into HeroSuperPower "
            + "(HeroId, SuperpowerId) "
            + "values (?,?)";

    private static final String SQL_DELETE_BY_HERO
            = "delete from HeroSuperPower where HeroId = ?";

    private static final String SQL_SELECT_HEROS_BY_SUPERPOWER
            = "select * from Hero "
            + "join HeroSuperPower on HeroSuperPower.HeroId = Hero.HeroId "
            + "where HeroSuperPower.SuperPowerId = ?";

    private static final String SQL_SELECT_SUPERPOWERS_BY_HERO
            = "select * from SuperPower "
            + "join HeroSuperPower on HeroSuperPower.SuperPowerId = SuperPower.SuperPowerId "
            + "where HeroSuperPower.HeroId = ?";

    private static final String SQL_SELECT_ALL_ITEMS
            = "select * from HeroSuperPower";

    private static final String SQL_TRUNCATE_HEROSUPERPOWER
            = "truncate HeroSuperPower";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Hero> getHerosWithSuperpower(String superpowerId) {
        return jdbcTemplate.query(SQL_SELECT_HEROS_BY_SUPERPOWER,
                new HeroMapper(), superpowerId);
    }

    @Override
    public List<SuperPower> getSuperpowersByHero(String heroId) {
        List<SuperPower> spList = jdbcTemplate.query(SQL_SELECT_SUPERPOWERS_BY_HERO,
                new SuperPowerMapper(), heroId);
        return spList;
    }

    @Override
    public void deleteByHero(String heroId) {

        jdbcTemplate.update(SQL_DELETE_BY_HERO, heroId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addSuperpowersForHero(String heroId, List<SuperPower> superPowers) {
        for (SuperPower superpower : superPowers) {
            addSuperPowerForHero(heroId, superpower.getSuperPowerId());
        }
    }

    private void addSuperPowerForHero(String heroId, String superpowerId) {
        jdbcTemplate.update(SQL_INSERT_HEROSUPERPOWER,
                heroId, superpowerId);
    }

    @Override
    public void truncateHeroSuperPower() {
        jdbcTemplate.execute(SQL_TRUNCATE_HEROSUPERPOWER);

    }

    private static final class SuperPowerMapper implements RowMapper<SuperPower> {

        public SuperPower mapRow(ResultSet rs, int rowNum) throws SQLException {

            SuperPower superPower = new SuperPower();
            superPower.setSuperPowerId(rs.getString("SuperPowerId"));
            superPower.setDescription(rs.getString("Description"));
            superPower.setSelected("selected");
            return superPower;
        }

    }

    public static final class HeroMapper implements RowMapper<Hero> {

        public Hero mapRow(ResultSet rs, int rowNum) throws SQLException {

            Hero hero = new Hero();
            hero.setHeroId(rs.getString("HeroId"));
            hero.setContactId(rs.getString("ContactId"));
            hero.setHeroName(rs.getString("Name"));
            hero.setDescription(rs.getString("Description"));
            return hero;
        }

    }
}
