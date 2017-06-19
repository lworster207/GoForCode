/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.dao;

import com.sg.superhero.model.Hero;
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

    @Override
    public Hero addHero(String heroId, Hero hero) {
        heroes.put(heroId, hero);
        return heroes.get(heroId);
    }

    @Override
    public Hero deleteHero(String heroId) {
        Hero removedHero = heroes.get(heroId);
        heroes.remove(heroId);
        return (removedHero);
    }

    @Override
    public Hero updateHero(Hero hero) {
        heroes.get(hero.getHeroId()).setContact(hero.getContact());
        heroes.get(hero.getHeroId()).setDescription(hero.getDescription());
        heroes.get(hero.getHeroId()).setSuperpower(hero.getSuperpower());
        return heroes.get(hero.getHeroId());
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

}
