/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.section04functionalunittests;

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
public class SpeedingTest {

    int nonSpeedMax = 60;
    int speedingMin = 61;
    int speedingMax = 80;

    public SpeedingTest() {
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
     * Test of caughtSpeeding method, of class Speeding.
     */
    @Test
    public void testCaughtSpeedingNotBirthday() {

        Speeding speeding = new Speeding();

        int speed;
        Boolean isBirthday = false;

        speed = nonSpeedMax;
        Assert.assertEquals(0, speeding.caughtSpeeding(speed, isBirthday));

        speed = speedingMin;
        Assert.assertEquals(1, speeding.caughtSpeeding(speed, isBirthday));

        speed = speedingMax;
        Assert.assertEquals(1, speeding.caughtSpeeding(speed, isBirthday));

        speed = speedingMax + 1;
        Assert.assertEquals(2, speeding.caughtSpeeding(speed, isBirthday));

    }

    public void testCaughtSpeedingOnBirthday() {
        Speeding speeding = new Speeding();

        int speed;
        Boolean isBirthday = true;

        speed = nonSpeedMax + 5;
        Assert.assertEquals(0, speeding.caughtSpeeding(speed, isBirthday));

        speed = speedingMin + 5;
        Assert.assertEquals(1, speeding.caughtSpeeding(speed, isBirthday));

        speed = speedingMax + 5;
        Assert.assertEquals(1, speeding.caughtSpeeding(speed, isBirthday));

        speed = speedingMax + 5 + 1;
        Assert.assertEquals(2, speeding.caughtSpeeding(speed, isBirthday));

    }

}
