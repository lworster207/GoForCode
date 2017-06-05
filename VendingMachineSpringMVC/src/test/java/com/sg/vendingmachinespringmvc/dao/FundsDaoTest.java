/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import java.math.BigDecimal;
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
public class FundsDaoTest {

    private FundsDao dao;

    public FundsDaoTest() {
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
        dao = ctx.getBean("fundsDao", FundsDao.class);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getBalance method, of class FundsDao.
     */
    @Test
    public void testGetBalance() {
        BigDecimal expected = dao.setBalance(new BigDecimal("0.00"));
        assertEquals(expected, new BigDecimal("0.00"));

    }

    /**
     * Test of setBalance method, of class FundsDao.
     */
    @Test
    public void testSetBalance() {
        BigDecimal expected = dao.setBalance(new BigDecimal("5.25"));
        assertEquals(expected, new BigDecimal("5.25"));
    }

    /**
     * Test of addFunds method, of class FundsDao.
     */
    @Test
    public void testAddFunds() {
        BigDecimal expected = new BigDecimal("10.00");

        dao.setBalance(new BigDecimal("4.25"));
        dao.addFunds(new BigDecimal("5.75"));
        assertEquals(expected, dao.getBalance());
    }

}
