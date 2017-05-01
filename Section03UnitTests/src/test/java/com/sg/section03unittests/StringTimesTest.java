/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.section03unittests;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author apprentice
 */
public class StringTimesTest {

    private StringTimes stringTimes = new StringTimes();

    public StringTimesTest() {
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
    public void testNullString() {

        String string = null;
        int iterations = 1;

        assertNull(stringTimes.stringTimes(string, iterations));

    }

    @Test
    public void testNegativeIterations() {
        String string = "Ok";
        int iterations = -1;

        assertEquals(string, stringTimes.stringTimes(string, iterations));

    }

    @Test
    public void testZero() {
        String string = "Ok";
        int iterations = 0;

        assertEquals(string, stringTimes.stringTimes(string, iterations));

    }

    @Test
    public void testOne() {
        String string = "Ok";
        int iterations = 1;

        assertEquals(string, stringTimes.stringTimes(string, iterations));

    }

    @Test
    public void testMultiple() {
        String string = "Ok";
        int iterations = 2;

        assertEquals("OkOk", stringTimes.stringTimes(string, iterations));

    }

}
