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
public class SameFirstLastTest {

    private SameFirstLast sameFirstLast = new SameFirstLast();

    public SameFirstLastTest() {
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
    public void testZeroLength() {
        int[] testZeroData = {};

        assertFalse(sameFirstLast.sameFirstLast(testZeroData));

        int[] testData = {1, 2, 3, 2, 1};

        assertTrue(sameFirstLast.sameFirstLast(testData));

        testData[0] = 2;
        assertFalse(sameFirstLast.sameFirstLast(testData));

    }

}
