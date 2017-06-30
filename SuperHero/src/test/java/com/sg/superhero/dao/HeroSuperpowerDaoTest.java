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
        hspList.add(sp);
        hspList.add(sp2);

        hspDao.addSuperpowersForHero("1", hspList);
        List<SuperPower> heroSuperpowerList = hspDao.getSuperpowersByHero("1");
        assertEquals(heroSuperpowerList.size(), 2);

        hspList.add(sp3);
        hspDao.addSuperpowersForHero("2", hspList);
        heroSuperpowerList = hspDao.getSuperpowersByHero("2");
        assertEquals(heroSuperpowerList.size(), 3);

        Hero hero = new Hero("heroName", null, "super hero description");

        hero.setHeroId("1");

        Hero result = heroDao.addHero("1", hero);

        hero.setHeroId("2");
        Hero result2 = heroDao.addHero("2", hero);

        List<SuperPower> heroList = hspDao.getSuperpowersByHero("1");
        assertEquals(heroList.size(), 2);

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

        hspDao.addSuperpowersForHero("1", hspList);
        List<SuperPower> heroSuperpowerList = hspDao.getSuperpowersByHero("1");
        assertEquals(heroSuperpowerList.size(), 2);

        hspList.add(sp3);
        hspDao.addSuperpowersForHero("2", hspList);
        heroSuperpowerList = hspDao.getSuperpowersByHero("2");
        assertEquals(heroSuperpowerList.size(), 3);
    }

    /**
     * Test of addSuperpowersForHero method, of class HeroSuperpowerDao.
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

        hspDao.addSuperpowersForHero("1", hspList);
        List<SuperPower> heroSuperpowerList = hspDao.getSuperpowersByHero("1");
        assertEquals(heroSuperpowerList.size(), 2);

        hspList.add(sp3);
        hspDao.addSuperpowersForHero("2", hspList);
        heroSuperpowerList = hspDao.getSuperpowersByHero("2");
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

        hspDao.addSuperpowersForHero("1", hspList);
        List<SuperPower> heroSuperpowerList = hspDao.getSuperpowersByHero("1");
        assertEquals(heroSuperpowerList.size(), 2);

        hspList.add(sp3);
        hspDao.addSuperpowersForHero("2", hspList);
        heroSuperpowerList = hspDao.getSuperpowersByHero("2");
        assertEquals(heroSuperpowerList.size(), 3);

        hspDao.deleteByHero("1");
        heroSuperpowerList = hspDao.getSuperpowersByHero("1");
        assertEquals(heroSuperpowerList.size(), 0);
    }
}
