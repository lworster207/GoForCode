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

/**
 *
 * @author apprentice
 */
public class GreatPartyTest {

    GreatParty party = new GreatParty();

    public GreatPartyTest() {
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

    @org.junit.Test
    public void test30False() {
        // TODO review the generated test code and remove the default call to fail.
        Assert.assertFalse(party.greatParty(30, false));
    }

    @org.junit.Test
    public void test50True() {
        // TODO review the generated test code and remove the default call to fail.
        Assert.assertTrue(party.greatParty(50, false));
    }

    @org.junit.Test
    public void test70True() {
        // TODO review the generated test code and remove the default call to fail.
        Assert.assertTrue(party.greatParty(70, true));
    }

    @org.junit.Test
    public void test39True() {
        // TODO review the generated test code and remove the default call to fail.
        Assert.assertFalse(party.greatParty(39, true));
    }

    @org.junit.Test
    public void test39False() {
        // TODO review the generated test code and remove the default call to fail.
        Assert.assertFalse(party.greatParty(39, false));
    }

    @org.junit.Test
    public void test40True() {
        // TODO review the generated test code and remove the default call to fail.
        Assert.assertTrue(party.greatParty(40, true));
    }

    @org.junit.Test
    public void test40False() {
        // TODO review the generated test code and remove the default call to fail.
        Assert.assertTrue(party.greatParty(40, false));
    }

    @org.junit.Test
    public void test60True() {
        // TODO review the generated test code and remove the default call to fail.
        Assert.assertTrue(party.greatParty(60, true));
    }

    @org.junit.Test
    public void test60False() {
        // TODO review the generated test code and remove the default call to fail.
        Assert.assertTrue(party.greatParty(60, false));
    }

    @org.junit.Test
    public void test61True() {
        // TODO review the generated test code and remove the default call to fail.
        Assert.assertTrue(party.greatParty(61, true));
    }

    @org.junit.Test
    public void test61False() {
        // TODO review the generated test code and remove the default call to fail.
        Assert.assertFalse(party.greatParty(61, false));
    }

}
