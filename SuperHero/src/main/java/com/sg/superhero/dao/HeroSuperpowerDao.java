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

    public List<Hero> getHerosWithSuperpower(Integer superpowerId);

    public List<SuperPower> getSuperpowersByHero(Integer heroId);

    public List<SuperPower> updateSuperpowersForHero(Integer heroId, List<SuperPower> superPowers);

    public List<SuperPower> updateSuperpowersForHeroBySuperpowerIds(Integer heroId, List<Integer> superPowerIds);

    void deleteByHero(Integer heroId);

    void truncateHeroSuperPower();

}
