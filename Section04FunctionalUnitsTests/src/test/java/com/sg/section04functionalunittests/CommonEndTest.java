/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.section04functionalunittests;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author apprentice
 */
public class CommonEndTest {

    public CommonEndTest() {
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
     * Test of commonEnd method, of class CommonEnd.
     */
    @Test
    public void testCommonEndBeginning() {
        System.out.println("commonEndBeginning");
        int[] a = {1, 3, 5};
        int[] b = {1, 3, 4};
        CommonEnd instance = new CommonEnd();
        boolean expResult = true;
        boolean result = instance.commonEnd(a, b);
        assertEquals(expResult, result);

        b[0] = 2;
        expResult = false;
        result = instance.commonEnd(a, b);
        assertEquals(expResult, result);

    }

    @Test
    public void testCommonEndEnding() {
        System.out.println("commonEndEnding");
        int[] a = {1, 3, 5};
        int[] b = {2, 3, 5};
        CommonEnd instance = new CommonEnd();
        boolean expResult = true;
        boolean result = instance.commonEnd(a, b);
        assertEquals(expResult, result);

        b[2] = 2;
        expResult = false;
        result = instance.commonEnd(a, b);
        assertEquals(expResult, result);

    }

}
