/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibraryspringmvc.dao;

import com.sg.dvdlibraryspringmvc.dto.Dvd;
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

    public List<Dvd> getMoviesForThePastNumberOfYears(int numberOfYears) throws DvdLibraryDaoException;

    public List<Dvd> getMoviesByMaaRating(String mpaaRating) throws DvdLibraryDaoException;

    public List<Dvd> getMoviesByDirector(String director) throws DvdLibraryDaoException;

    public List<Dvd> getMoviesByStudio(String studio) throws DvdLibraryDaoException;

}
