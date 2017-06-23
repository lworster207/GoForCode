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
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

/**
 *
 * @author apprentice
 */
public class HeroDaoTest {

    HeroDao dao;
    ContactDao contactDao;
    AddressDao addressDao;

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
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("heroDao", HeroDao.class);
        contactDao = ctx.getBean("contactDao", ContactDao.class);
        addressDao = ctx.getBean("addressDao", AddressDao.class);
        /*
        List<Hero> heroList = dao.getAllHeroes();
        for (Hero hero : heroList) {
            dao.deleteHero(hero.getHeroId());
        }
         */
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
        contact.setAddressId(address.getAddressId());

        Hero hero = new Hero("heroName", null, "super hero description");

        Hero result = dao.addHero("1", hero);
        Hero expected = dao.getHero(result.getHeroId());
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
        contact.setAddressId(address.getAddressId());

        Hero hero = new Hero("heroName", null, "super hero description");
        hero.setHeroId("1");

        Hero result = dao.addHero("1", hero);
        Hero expected = dao.getHero(result.getHeroId());
        //assertEquals(expected, result);

        dao.deleteHero(result.getHeroId());
        try {
            expected = dao.getHero(result.getHeroId());
            assertEquals(1, 0);
        } catch (EmptyResultDataAccessException e) {
            assertEquals(1, 1);
        }

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
        contact.setAddressId(address.getAddressId());

        Hero hero = new Hero("heroName", null, "super hero description");

        hero.setHeroId("1");

        Hero result = dao.addHero("1", hero);
        Hero expected = dao.getHero(result.getHeroId());
        assertEquals(expected, result);

        result.setDescription("new super hero description");
        expected = dao.updateHero(result.getHeroId(), result);
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
        contact.setAddressId(address.getAddressId());

        Hero hero = new Hero("heroName", null, "super hero description");

        hero.setHeroId("1");

        Hero result = dao.addHero("1", hero);
        Hero expected = dao.getHero(result.getHeroId());
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
        contact.setAddressId(address.getAddressId());

        Hero hero = new Hero("heroName", contact.getContactId(), "super hero description");

        hero.setHeroId("1");
        List<Hero> allHeroes = dao.getAllHeroes();

        Hero result = dao.addHero("1", hero);

        hero.setHeroId("2");
        Hero result2 = dao.addHero("2", hero);
        List<Hero> allHeroes2 = dao.getAllHeroes();
        assertEquals(allHeroes.size() + 2, allHeroes2.size());
    }

}
