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
public class CanHazTableTest {

    private CanHazTable canGetTable = new CanHazTable();

    public CanHazTableTest() {
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
    public void testInStyle() {

        Integer yourStyle = 8;
        Integer dateStyle = 5;

        Assert.assertEquals(2, canGetTable.canHazTable(yourStyle, dateStyle));

        yourStyle = 5;
        dateStyle = 8;
        Assert.assertEquals(2, canGetTable.canHazTable(yourStyle, dateStyle));

    }

    @Test
    public void testNotInStyle() {

        Integer yourStyle = 8;
        Integer dateStyle = 2;
        Assert.assertEquals(0, canGetTable.canHazTable(yourStyle, dateStyle));

        yourStyle = 2;
        dateStyle = 8;
        Assert.assertEquals(0, canGetTable.canHazTable(yourStyle, dateStyle));

    }

    @Test
    public void testMaybeInStyle() {

        Integer yourStyle = 5;
        Integer dateStyle = 5;
        Assert.assertEquals(1, canGetTable.canHazTable(yourStyle, dateStyle));

        yourStyle = 3;
        dateStyle = 7;
        Assert.assertEquals(1, canGetTable.canHazTable(yourStyle, dateStyle));

    }

}
