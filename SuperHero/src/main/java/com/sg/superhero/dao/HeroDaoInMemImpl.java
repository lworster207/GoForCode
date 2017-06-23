/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.dao;

import com.sg.superhero.model.Address;
import com.sg.superhero.model.Contact;
import com.sg.superhero.model.Hero;
import com.sg.superhero.model.HeroPower;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public class HeroDaoInMemImpl implements HeroDao {

    public Map<String, Hero> heroes = new HashMap<>();
    public Integer nextId = 1;

    public int getNextId() {
        nextId++;
        return nextId;
    }

    @Override
    public Hero addHero(String heroId, Hero hero) {
        heroes.put(heroId, hero);
        return heroes.get(heroId);
    }

    @Override
    public Hero addHero(Hero hero, Contact contact, Address address) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Hero deleteHero(String heroId) {
        Hero removedHero = heroes.get(heroId);
        heroes.remove(heroId);
        return (removedHero);
    }

    @Override
    public Hero updateHero(String heroId, Hero hero) {
        heroes.get(heroId).setContactId(hero.getContactId());
        heroes.get(heroId).setDescription(hero.getDescription());
        return heroes.get(heroId);
    }

    @Override
    public Hero getHero(String heroId) {
        return heroes.get(heroId);
    }

    @Override
    public List<Hero> getAllHeroes() {
        Collection<Hero> c = heroes.values();
        return new ArrayList(c);
    }

    @Override
    public List<HeroPower> getAllHeroesAndPowers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
