/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.dao;

import com.sg.superhero.model.Hero;
import com.sg.superhero.model.SuperPower;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class HeroSuperpowerDaoTest {

    HeroSuperpowerDao hspDao;
    HeroDao heroDao;
    SuperPowerDao superpowerDao;

    public HeroSuperpowerDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        hspDao = ctx.getBean("heroSuperpowerDao", HeroSuperpowerDao.class);
        heroDao = ctx.getBean("heroDao", HeroDao.class);
        superpowerDao = ctx.getBean("superpowerDao", SuperPowerDao.class);
        hspDao.truncateHeroSuperPower();

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getHerosWithSuperpower method, of class HeroSuperpowerDao.
     */
    @Test
    public void testGetHerosWithSuperpower() {
        List<SuperPower> hspList = new ArrayList<SuperPower>();
        SuperPower sp = new SuperPower();
        sp.setSuperPowerId("1");
        sp.setDescription("1 description");
        SuperPower sp2 = new SuperPower();
        sp2.setSuperPowerId("2");
        sp2.setDescription("2 description");
        SuperPower sp3 = new SuperPower();
        sp3.setSuperPowerId("3");
        sp3.setDescription("3 description");

        superpowerDao.addSuperPower(sp.getSuperPowerId(), sp);
        superpowerDao.addSuperPower(sp2.getSuperPowerId(), sp2);
        superpowerDao.addSuperPower(sp3.getSuperPowerId(), sp3);

        hspList.add(sp);
        hspList.add(sp2);

        Hero hero = new Hero("heroName", null, "super hero description");
        Hero hero2 = new Hero("heroName", null, "super hero description");
        Hero hero3 = new Hero("heroName", null, "super hero description");

        hero = heroDao.addHero("1", hero);
        hero2 = heroDao.addHero("2", hero2);
        hero3 = heroDao.addHero("3", hero3);

        hspDao.updateSuperpowersForHero(hero.getHeroId(), hspList);
        List<SuperPower> heroSuperpowerList = hspDao.getSuperpowersByHero(hero.getHeroId());
        assertEquals(heroSuperpowerList.size(), 2);

        hspList.add(sp3);
        hspDao.updateSuperpowersForHero(hero2.getHeroId(), hspList);
        heroSuperpowerList = hspDao.getSuperpowersByHero(hero2.getHeroId());
        assertEquals(heroSuperpowerList.size(), 3);

    }

    /**
     * Test of getSuperpowersByHero method, of class HeroSuperpowerDao.
     */
    @Test
    public void testGetSuperpowersByHero() {
        List<SuperPower> hspList = new ArrayList<SuperPower>();
        SuperPower sp = new SuperPower();
        sp.setSuperPowerId("1");
        sp.setDescription("1 description");
        SuperPower sp2 = new SuperPower();
        sp2.setSuperPowerId("2");
        sp2.setDescription("2 description");
        SuperPower sp3 = new SuperPower();
        sp3.setSuperPowerId("3");
        sp3.setDescription("3 description");
        hspList.add(sp);
        hspList.add(sp2);

        superpowerDao.addSuperPower(sp.getSuperPowerId(), sp);
        superpowerDao.addSuperPower(sp2.getSuperPowerId(), sp2);
        superpowerDao.addSuperPower(sp3.getSuperPowerId(), sp3);

        Hero hero = new Hero("heroName", null, "super hero description");
        Hero hero2 = new Hero("heroName", null, "super hero description");
        Hero hero3 = new Hero("heroName", null, "super hero description");

        hero = heroDao.addHero("1", hero);
        hero2 = heroDao.addHero("2", hero2);
        hero3 = heroDao.addHero("3", hero3);

        hspDao.updateSuperpowersForHero(hero.getHeroId(), hspList);
        List<SuperPower> heroSuperpowerList = hspDao.getSuperpowersByHero(hero.getHeroId());
        assertEquals(heroSuperpowerList.size(), 2);

        hspList.add(sp3);
        hspDao.updateSuperpowersForHero(hero2.getHeroId(), hspList);
        heroSuperpowerList = hspDao.getSuperpowersByHero(hero2.getHeroId());
        assertEquals(heroSuperpowerList.size(), 3);
    }

    /**
     * Test of updateSuperpowersForHero method, of class HeroSuperpowerDao.
     */
    @Test
    public void testAddSuperpowersForHero() {
        List<SuperPower> hspList = new ArrayList<SuperPower>();
        SuperPower sp = new SuperPower();
        sp.setSuperPowerId("1");
        sp.setDescription("1 description");
        SuperPower sp2 = new SuperPower();
        sp2.setSuperPowerId("2");
        sp2.setDescription("2 description");
        SuperPower sp3 = new SuperPower();
        sp3.setSuperPowerId("3");
        sp3.setDescription("3 description");
        hspList.add(sp);
        hspList.add(sp2);

        superpowerDao.addSuperPower(sp.getSuperPowerId(), sp);
        superpowerDao.addSuperPower(sp2.getSuperPowerId(), sp2);
        superpowerDao.addSuperPower(sp3.getSuperPowerId(), sp3);

        Hero hero = new Hero("heroName", null, "super hero description");
        Hero hero2 = new Hero("heroName", null, "super hero description");
        Hero hero3 = new Hero("heroName", null, "super hero description");

        hero = heroDao.addHero("1", hero);
        hero2 = heroDao.addHero("2", hero2);
        hero3 = heroDao.addHero("3", hero3);

        hspDao.updateSuperpowersForHero(hero.getHeroId(), hspList);
        List<SuperPower> heroSuperpowerList = hspDao.getSuperpowersByHero(hero.getHeroId());
        assertEquals(heroSuperpowerList.size(), 2);

        hspList.add(sp3);
        hspDao.updateSuperpowersForHero(hero2.getHeroId(), hspList);
        heroSuperpowerList = hspDao.getSuperpowersByHero(hero2.getHeroId());
        assertEquals(heroSuperpowerList.size(), 3);

    }

    @Test
    public void testDeleteByHero() {
        List<SuperPower> hspList = new ArrayList<SuperPower>();
        SuperPower sp = new SuperPower();
        sp.setSuperPowerId("1");
        sp.setDescription("1 description");
        SuperPower sp2 = new SuperPower();
        sp2.setSuperPowerId("2");
        sp2.setDescription("2 description");
        SuperPower sp3 = new SuperPower();
        sp3.setSuperPowerId("3");
        sp3.setDescription("3 description");
        hspList.add(sp);
        hspList.add(sp2);

        superpowerDao.addSuperPower(sp.getSuperPowerId(), sp);
        superpowerDao.addSuperPower(sp2.getSuperPowerId(), sp2);
        superpowerDao.addSuperPower(sp3.getSuperPowerId(), sp3);

        Hero hero = new Hero("heroName", null, "super hero description");
        Hero hero2 = new Hero("heroName", null, "super hero description");
        Hero hero3 = new Hero("heroName", null, "super hero description");

        hero = heroDao.addHero("1", hero);
        hero2 = heroDao.addHero("2", hero2);
        hero3 = heroDao.addHero("3", hero3);

        hspDao.updateSuperpowersForHero(hero.getHeroId(), hspList);
        List<SuperPower> heroSuperpowerList = hspDao.getSuperpowersByHero(hero.getHeroId());
        assertEquals(heroSuperpowerList.size(), 2);

        hspList.add(sp3);
        hspDao.updateSuperpowersForHero(hero2.getHeroId(), hspList);
        heroSuperpowerList = hspDao.getSuperpowersByHero(hero2.getHeroId());
        assertEquals(heroSuperpowerList.size(), 3);

        hspDao.deleteByHero(hero.getHeroId());
        heroSuperpowerList = hspDao.getSuperpowersByHero(hero.getHeroId());
        assertEquals(heroSuperpowerList.size(), 0);
    }
}
