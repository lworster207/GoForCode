/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.dao;

import com.sg.superhero.model.Address;
import com.sg.superhero.model.Contact;
import com.sg.superhero.model.Hero;
import com.sg.superhero.model.SuperPower;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author apprentice
 */
public class HeroDaoTest {

    HeroDao dao = new HeroDaoInMemImpl();

    public HeroDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        List<Hero> heroList = dao.getAllHeroes();
        for (Hero hero : heroList) {
            dao.deleteHero(hero.getHeroId());
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addHero method, of class HeroDao.
     */
    @Test
    public void testAddHero() {
        SuperPower superpower = new SuperPower();
        superpower.setDescription("Invisibility");
        superpower.setSuperPowerId("1");

        Address address = new Address();
        address.setAddressId("1");
        address.setStreetAddress("123 Elm Street");
        address.setCity("Portland");
        address.setStateProvince("Maine");
        address.setPostCode("04101");

        Contact contact = new Contact();
        contact.setContactId("1");
        contact.setFirstName("Peter");
        contact.setLastName("Parker");
        contact.setAddress(address);

        Hero hero = new Hero("heroName", superpower, contact, "super hero description");

        Hero result = dao.addHero("1", hero);
        Hero expected = dao.getHero("1");
        assertEquals(expected, result);
    }

    /**
     * Test of deleteHero method, of class HeroDao.
     */
    @Test
    public void testDeleteHero() {
        SuperPower superpower = new SuperPower();
        superpower.setDescription("Invisibility");
        superpower.setSuperPowerId("1");

        Address address = new Address();
        address.setAddressId("1");
        address.setStreetAddress("123 Elm Street");
        address.setCity("Portland");
        address.setStateProvince("Maine");
        address.setPostCode("04101");

        Contact contact = new Contact();
        contact.setContactId("1");
        contact.setFirstName("Peter");
        contact.setLastName("Parker");
        contact.setAddress(address);

        Hero hero = new Hero("heroName", superpower, contact, "super hero description");
        hero.setHeroId("1");

        Hero result = dao.addHero("1", hero);
        Hero expected = dao.getHero("1");
        assertEquals(expected, result);

        dao.deleteHero("1");
        expected = dao.getHero("1");
        assertEquals(null, expected);

    }

    /**
     * Test of updateHero method, of class HeroDao.
     */
    @Test
    public void testUpdateHero() {
        SuperPower superpower = new SuperPower();
        superpower.setDescription("Invisibility");
        superpower.setSuperPowerId("1");

        Address address = new Address();
        address.setAddressId("1");
        address.setStreetAddress("123 Elm Street");
        address.setCity("Portland");
        address.setStateProvince("Maine");
        address.setPostCode("04101");

        Contact contact = new Contact();
        contact.setContactId("1");
        contact.setFirstName("Peter");
        contact.setLastName("Parker");
        contact.setAddress(address);

        Hero hero = new Hero("heroName", superpower, contact, "super hero description");
        hero.setHeroId("1");

        Hero result = dao.addHero("1", hero);
        Hero expected = dao.getHero("1");
        assertEquals(expected, result);

        result.setDescription("new super hero description");
        expected = dao.updateHero("1", result);
        assertEquals(expected, result);
    }

    /**
     * Test of getHero method, of class HeroDao.
     */
    @Test
    public void testGetHero() {
        SuperPower superpower = new SuperPower();
        superpower.setDescription("Invisibility");
        superpower.setSuperPowerId("1");

        Address address = new Address();
        address.setAddressId("1");
        address.setStreetAddress("123 Elm Street");
        address.setCity("Portland");
        address.setStateProvince("Maine");
        address.setPostCode("04101");

        Contact contact = new Contact();
        contact.setContactId("1");
        contact.setFirstName("Peter");
        contact.setLastName("Parker");
        contact.setAddress(address);

        Hero hero = new Hero("heroName", superpower, contact, "super hero description");
        hero.setHeroId("1");

        Hero result = dao.addHero("1", hero);
        Hero expected = dao.getHero("1");
        assertEquals(expected, result);
    }

    /**
     * Test of getAllHeroes method, of class HeroDao.
     */
    @Test
    public void testGetAllHeroes() {
        SuperPower superpower = new SuperPower();
        superpower.setDescription("Invisibility");
        superpower.setSuperPowerId("1");

        Address address = new Address();
        address.setAddressId("1");
        address.setStreetAddress("123 Elm Street");
        address.setCity("Portland");
        address.setStateProvince("Maine");
        address.setPostCode("04101");

        Contact contact = new Contact();
        contact.setContactId("1");
        contact.setFirstName("Peter");
        contact.setLastName("Parker");
        contact.setAddress(address);

        Hero hero = new Hero("heroName", superpower, contact, "super hero description");
        hero.setHeroId("1");

        Hero result = dao.addHero("1", hero);

        hero.setHeroId("2");
        Hero result2 = dao.addHero("2", hero);
        List<Hero> allHeroes = dao.getAllHeroes();
        assertEquals(allHeroes.size(), 2);
    }

}
