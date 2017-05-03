/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.Dvd;
import java.time.LocalDate;
import java.util.List;
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
public class DvdLibraryDaoTest {

    private DvdLibraryDao dao = new DvdLibraryDaoFileImpl();

    public DvdLibraryDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws Exception {
        List<Dvd> dvdList = dao.getAllDvds();
        for (Dvd dvd : dvdList) {
            dao.deleteDvd(dvd);
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addDvd method, of class DvdLibraryDao.
     */
    @Test
    public void testAddDvd() throws Exception {

        LocalDate ld = LocalDate.now();
        Dvd newDvd = new Dvd("title", ld, "mpaa", "director", "Studio", "note");

        Dvd expResult = dao.addDvd(newDvd);
        assertEquals(newDvd, expResult);
    }

    /**
     * Test of deleteDvd method, of class DvdLibraryDao.
     */
    @Test
    public void testDeleteDvd() throws Exception {
        Dvd expResult = null;
        LocalDate ld = LocalDate.now();
        Dvd newDvd = new Dvd("title", ld, "mpaa", "director", "Studio", "note");

        Dvd result = dao.addDvd(newDvd);

        dao.deleteDvd(newDvd);

        expResult = dao.getDvdByTitle(newDvd.getTitle());

        assertNull(expResult);
    }

    /**
     * Test of getDvdByTitle method, of class DvdLibraryDao.
     */
    @Test
    public void testGetDvdByTitle() throws Exception {
        Dvd expResult = null;
        LocalDate ld = LocalDate.now();
        Dvd newDvd = new Dvd("title", ld, "mpaa", "director", "Studio", "note");

        Dvd result = dao.addDvd(newDvd);
        expResult = dao.getDvdByTitle(newDvd.getTitle());
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllDvds method, of class DvdLibraryDao.
     */
    @Test
    public void testGetAllDvds() throws Exception {
        LocalDate ld = LocalDate.now();
        Dvd newDvd = new Dvd("title", ld, "mpaa", "director", "Studio", "note");
        newDvd = dao.addDvd(newDvd);
        assertEquals(1, dao.getCount());
    }

    /**
     * Test of getCount method, of class DvdLibraryDao.
     */
    @Test
    public void testGetCount() throws Exception {
        LocalDate ld = LocalDate.now();
        Dvd newDvd = new Dvd("title", ld, "mpaa", "director", "Studio", "note");
        newDvd = dao.addDvd(newDvd);
        assertEquals(1, dao.getCount());

    }

}
