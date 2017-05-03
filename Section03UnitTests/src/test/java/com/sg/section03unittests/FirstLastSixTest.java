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
public class FirstLastSixTest {

    private FirstLastSix firstLastSix = new FirstLastSix();

    public FirstLastSixTest() {
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
    public void testFirstSixMultiElement() {
        int[] testData = {6, 4, 5};

        assertTrue(firstLastSix.firstLast6(testData));

        testData[0] = 3;
        assertFalse(firstLastSix.firstLast6(testData));

        testData[2] = 6;
        assertTrue(firstLastSix.firstLast6(testData));

        testData[2] = 4;
        assertFalse(firstLastSix.firstLast6(testData));
    }

    @Test
    public void testFirstSixSingleElement() {
        int[] testData = {6};

        assertTrue(firstLastSix.firstLast6(testData));

        testData[0] = 3;
        assertFalse(firstLastSix.firstLast6(testData));

    }
}
