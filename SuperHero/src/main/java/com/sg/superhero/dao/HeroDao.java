/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.dao;

import com.sg.superhero.model.Hero;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface HeroDao {

    public Hero addHero(String heroId, Hero hero);

    public Hero deleteHero(String heroId);

    public Hero updateHero(String heroId, Hero hero);

    public Hero getHero(String heroId);

    public List<Hero> getAllHeroes();
}
