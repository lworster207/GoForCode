/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.section03unittests;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author apprentice
 */
public class MischievousChildrenTest {

    private MischievousChildren charlieBrown = new MischievousChildren();

    public MischievousChildrenTest() {
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

    @Test
    public void testTrueTrue() {
        Boolean aSmile = true;
        Boolean bSmile = true;

        assertTrue(charlieBrown.areWeInTrouble(aSmile, bSmile));

    }

    @Test
    public void testTrueFalse() {
        Boolean aSmile = true;
        Boolean bSmile = false;

        assertFalse(charlieBrown.areWeInTrouble(aSmile, bSmile));

    }

    @Test
    public void testFalseTrue() {
        Boolean aSmile = false;
        Boolean bSmile = true;

        assertFalse(charlieBrown.areWeInTrouble(aSmile, bSmile));

    }

    @Test
    public void testFalseFalse() {
        Boolean aSmile = false;
        Boolean bSmile = false;

        assertTrue(charlieBrown.areWeInTrouble(aSmile, bSmile));

    }

}
