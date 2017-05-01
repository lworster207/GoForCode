/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.Dvd;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface DvdLibraryDao {

    public Dvd addDvd(Dvd dvd) throws DvdLibraryDaoException;

    public Dvd deleteDvd(Dvd dvd) throws DvdLibraryDaoException;

    public Dvd getDvdByTitle(String title) throws DvdLibraryDaoException;

    public List<Dvd> getAllDvds() throws DvdLibraryDaoException;

    public int getCount() throws DvdLibraryDaoException;

}
