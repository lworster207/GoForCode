/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.service;

import com.sg.superhero.model.Address;
import com.sg.superhero.model.Contact;
import com.sg.superhero.model.Hero;
import com.sg.superhero.model.HeroPower;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface HeroServiceLayer {

    public Hero addHero(String heroId, Hero hero);

    public Hero addHero(Hero hero, Contact contact, Address address);

    public Hero deleteHero(String heroId, String contactId);

    public Hero updateHero(String heroId, Hero hero, Contact contact, Address address);

    public Hero getHero(String heroId);

    public List<Hero> getAllHeroes();

    public List<HeroPower> getAllHeroesAndPowers();

}