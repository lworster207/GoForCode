/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.section04functionalunittests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author apprentice
 */
public class DoubleXTest {

    public DoubleXTest() {
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
     * Test of doubleX method, of class DoubleX.
     */
    @Test
    public void testDoubleX() {
        String testTrueData = "yyyzxxhhh";
        String testFalseData = "xgxxdjjxx";
        String testNoXsData = "jhksdhsgsg";

        DoubleX doubleX = new DoubleX();

        Assert.assertTrue(doubleX.doubleX(testTrueData));

        Assert.assertFalse(doubleX.doubleX(testFalseData));

        Assert.assertFalse(doubleX.doubleX(testNoXsData));
    }

    @Test
    public void testDoubleXBeginningOfString() {
        String testTrueData = "xxyyyzxxhhh";
        String testFalseData = "xgxxdjjxx";

        DoubleX doubleX = new DoubleX();

        assertTrue(doubleX.doubleX(testTrueData));

        assertFalse(doubleX.doubleX(testFalseData));

    }

    @Test
    public void testDoubleXEndOfString() {
        String testTrueData = "ydyyzjhhhxx";
        String testFalseData = "gdjjx";

        DoubleX doubleX = new DoubleX();

        assertTrue(doubleX.doubleX(testTrueData));

        assertFalse(doubleX.doubleX(testFalseData));
    }

}
