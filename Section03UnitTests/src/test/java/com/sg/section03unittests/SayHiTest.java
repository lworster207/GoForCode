/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.section03unittests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author apprentice
 */
public class SayHiTest {

    private SayHi sayHi = new SayHi();

    public SayHiTest() {
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
    public void testBob() {
        // TODO review the generated test code and remove the default call to fail.
        String expectedResult = "Hello Bob!";

        Assert.assertEquals(expectedResult, sayHi.sayHi("Bob"));

    }

    @Test
    public void testAlice() {
        // TODO review the generated test code and remove the default call to fail.
        String expectedResult = "Hello Alice!";

        Assert.assertEquals(expectedResult, sayHi.sayHi("Alice"));

    }

    @Test
    public void testX() {
        // TODO review the generated test code and remove the default call to fail.
        String expectedResult = "Hello X!";

        Assert.assertEquals(expectedResult, sayHi.sayHi("X"));

    }

}
