/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.section03unittests;

import java.util.Objects;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author apprentice
 */
public class FrontTimesTest {

    private FrontTimes front = new FrontTimes();

    public FrontTimesTest() {
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
    public void testSingle() {

        String string = "Abc";

        Assert.assertEquals("Abc", front.frontTimes(string, 1));

        string = "Ab";
        Assert.assertEquals("Ab", front.frontTimes(string, 1));

        string = "Abcd";
        Assert.assertEquals("Abc", front.frontTimes(string, 1));

    }

    @Test
    public void testNull() {

        String string = null;

        assertNull(front.frontTimes(string, 1));

    }

    @Test
    public void testMultiple() {

        String string = "Abc";

        Assert.assertEquals("AbcAbc", front.frontTimes(string, 2));

        string = "Ab";
        Assert.assertEquals("AbAb", front.frontTimes(string, 2));

        string = "Abcd";
        Assert.assertEquals("AbcAbc", front.frontTimes(string, 2));

    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.front);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FrontTimesTest other = (FrontTimesTest) obj;
        if (!Objects.equals(this.front, other.front)) {
            return false;
        }
        return true;
    }

}
