/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmaster.dao;

import com.sg.flooringmaster.dto.TaxRate;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author apprentice
 */
public class TaxRateDaoTest {

    TaxRateDao taxratedao = new TaxRateDaoFileImpl();

    public TaxRateDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getTaxRate method, of class TaxRateDao.
     */
    @Test
    public void testGetTaxRate() {
    }

    /**
     * Test of getAllTaxRates method, of class TaxRateDao.
     */
    @Test
    public void testGetAllTaxRates() {
    }

    public class TaxRateDaoImpl implements TaxRateDao {

        public TaxRate getTaxRate(String state) {
            return null;
        }

        public List<TaxRate> getAllTaxRates() {
            return null;
        }
    }

}
