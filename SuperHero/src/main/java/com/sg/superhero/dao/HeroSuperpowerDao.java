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
public interface HeroSuperpowerDao {

    public List<Hero> getHerosWithSuperpower(String superpowerId);

    public List<SuperPower> getSuperpowersByHero(String heroId);

    public List<SuperPower> updateSuperpowersForHero(String heroId, List<SuperPower> superPowers);

    public List<SuperPower> updateSuperpowersForHeroBySuperpowerIds(String heroId, List<String> superPowerIds);

    void deleteByHero(String heroId);

    void truncateHeroSuperPower();

}
