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
public class AbbaTest {

    private Abba abba = new Abba();

    public AbbaTest() {
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
        String a = null;
        String b = "B";
        assertNull(abba.abba(a, b));
    }

    @Test
    public void testStringNull() {
        String a = "A";
        String b = null;
        assertNull(abba.abba(a, b));
    }

    @Test
    public void testStringString() {
        String a = "A";
        String b = "B";
        assertEquals("ABBA", abba.abba(a, b));
    }

}
