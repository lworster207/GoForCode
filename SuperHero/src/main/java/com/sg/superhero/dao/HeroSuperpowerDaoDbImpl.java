/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.dao;

import com.sg.superhero.model.Hero;
import com.sg.superhero.model.SuperPower;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class HeroSuperpowerDaoDbImpl implements HeroSuperpowerDao {

    private static final String SQL_DELETE_BY_HERO
            = "delete from HeroSuperPower where HeroId = ?";

    private static final String SQL_SELECT_HEROS_BY_SUPERPOWER
            = "select * from Hero "
            + "join HeroSuperPower on HeroSuperPower.HeroId = Hero.HeroId "
            + "where HeroSuperPower.SuperPowerId = ?";

    private static final String SQL_SELECT_SUPERPOWERS_BY_HERO
            = "select * from SuperPower "
            + "join HeroSuperPower on HeroSuperPower.SuperPowerId = SuperPower.HeroId "
            + "where HeroSuperPower.HeroId = ?";

    private static final String SQL_SELECT_ALL_ITEMS
            = "select * from HeroSuperPower";

    @Override
    public List<Hero> getHerosWithSuperpower(String superpowerId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SuperPower> getSuperpowersByHero(String heroId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteByHero(String heroId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addSuperpowersForHero(List<SuperPower> superPowers) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
