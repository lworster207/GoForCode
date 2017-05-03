/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.Dvd;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class DvdLibraryDaoFileImpl implements DvdLibraryDao {

    private Map<String, Dvd> dvdVault = new HashMap<>();

    public static final String DVD_FILE = "dvdlibrary.txt";
    public static final String DELIMITER = "::";

    @Override
    public Dvd addDvd(Dvd dvd) throws DvdLibraryDaoException {
        // file may not exist.
        try {
            loadDvds();
        } catch (DvdLibraryDaoException e) {

        }
        // add a Dvd to the dvdVault
        dvdVault.put(dvd.getTitle(), dvd);
        saveDvds();
        return (dvd);
    }

    @Override
    public Dvd deleteDvd(Dvd dvd) throws DvdLibraryDaoException {
        loadDvds();
        // remove a Dvd from the dvdVault.
        Dvd deletedDvd = dvdVault.remove(dvd.getTitle());
        saveDvds();
        return deletedDvd;
    }

    @Override
    public Dvd getDvdByTitle(String title) throws DvdLibraryDaoException {
        loadDvds();
        // returns the Dvd with title or null, if it does not exist.
        if (dvdVault.containsKey(title)) {
            return dvdVault.get(title);
        } else {
            return null;
        }
    }

    @Override
    public List<Dvd> getAllDvds() throws DvdLibraryDaoException {
        // return a List of all the Dvds in the dvdVault
        loadDvds();
        return (new ArrayList<>(dvdVault.values()));
    }

    public void loadDvds() throws DvdLibraryDaoException {
        // read the DVD_FILE and store in dvdVault.

        Scanner scanner;
        Dvd currentDvd;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(DVD_FILE)));
        } catch (FileNotFoundException e) {
            throw new DvdLibraryDaoException(
                    "-_- Failed to load dvd data from " + DVD_FILE, e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentTokens holds a Dvd record  - ( no pun intended )

        String[] currentTokens;

        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // break up the line into tokens
            currentTokens = currentLine.split(DELIMITER);
            // Create a new Dvd object and put it into the map of dvds
            // The Dvd title is used as the map key, which is currentTokens[0]

            currentDvd = new Dvd(currentTokens[0], LocalDate.parse(currentTokens[1]), currentTokens[2], currentTokens[3], currentTokens[4], currentTokens[5]);

            // Put currentDvd into the map using title as the key
            /*
            Would performance be slightly better by avoiding the getTitle() call?
                dvdVault.put(currentTokens[0],currentDvd);
             */
            dvdVault.put(currentDvd.getTitle(), currentDvd);
        }
        // close scanner
        scanner.close();

    }

    public void saveDvds() throws DvdLibraryDaoException {

        // save the dvdVault list to file.
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(DVD_FILE));
        } catch (IOException e) {
            throw new DvdLibraryDaoException(
                    "Error saving dvd data to " + DVD_FILE, e);
        }

        // Write out the Dvd objects to the dvd library file.
        // get the List of all Dvds
        List<Dvd> dvdList = this.getAllDvds();
        for (Dvd currentDvd : dvdList) {
            // write the Dvd fields to the delimited file
            out.println(currentDvd.getTitle() + DELIMITER
                    + currentDvd.getReleaseDate() + DELIMITER
                    + currentDvd.getMpaaRating() + DELIMITER
                    + currentDvd.getDirector() + DELIMITER
                    + currentDvd.getStudio() + DELIMITER
                    + currentDvd.userNote);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }

    @Override
    public int getCount() throws DvdLibraryDaoException {
        loadDvds();
        return dvdVault.size();
    }

}
