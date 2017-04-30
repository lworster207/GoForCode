/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.controller;

import com.sg.dvdlibrary.dao.DvdLibraryDao;
import com.sg.dvdlibrary.dao.DvdLibraryDaoException;
import com.sg.dvdlibrary.dto.Dvd;
import com.sg.dvdlibrary.ui.DvdLibraryView;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class DvdLibraryController {

// properties
    DvdLibraryDao dao;
    DvdLibraryView view;

    public final int DISPLAY_ALL = 1;
    public final int FIND_DVD = 2;
    public final int ADD_DVD = 3;
    public final int REMOVE_DVD = 4;
    public final int EDIT_DVD = 5;
    public final int EXIT = 6;

// constuctors
    public DvdLibraryController(DvdLibraryDao dao, DvdLibraryView view) {
        this.dao = dao;
        this.view = view;
    }

// methods
    public void run() {
        boolean keepGoing = true;
        int menuSelection;

        // initialize the dvdVault on startup
        // doing this here, avoids having to load the dvds with every option.
        dao.initDvds();

        try {
            while (keepGoing) {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case ADD_DVD:
                        addDvd();
                        break;
                    case REMOVE_DVD:
                        removeDvd();
                        break;
                    case FIND_DVD:
                        showDvd();
                        break;
                    case DISPLAY_ALL:
                        showDvdList();
                        break;
                    case EDIT_DVD:
                        editDvd();
                        break;
                    case EXIT:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

            }
            exitMessage();
        } catch (DvdLibraryDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection() {
        // display the options menu and return the user selection
        return view.displayMenuAndGetOption();
    }

    public void addDvd() throws DvdLibraryDaoException {
        Dvd newDvd;

        do {
            // get the information for the new Dvd
            newDvd = view.getDvdInfo();
            // add it to the library
            dao.addDvd(newDvd);
            // continue as long as the user wants
        } while (view.prompt("Add another DVD? ( Y or N : )").equalsIgnoreCase("y"));

    }

    public void removeDvd() throws DvdLibraryDaoException {

        Dvd dvdToRemove;
        String title;

        // see if there Dvds to remove
        if (dao.getCount() == 0) {
            // no DVDs to remove.
            view.prompt("There are no DVDs to Remove.  Press Enter to continue.");
        } else {
            do {
                // get the dvd to remove
                dvdToRemove = findDvd("Remove DVD");

                if (dvdToRemove != null) {
                    // display the dvd information on a single line
                    view.displayDvdSingleLine(dvdToRemove);

                    // confirm the removal
                    String confirmDelete = view.prompt("Remove this DVD? ( Y or N )");
                    if (confirmDelete.toLowerCase().equals("y")) {
                        dao.deleteDvd(dvdToRemove);
                        view.displayBanner("The DVD has been removed.");
                        view.prompt("Press enter to continue");
                    }
                }
                // continue removing Dvds while there are Dvds AND the user wants to
            } while (dao.getCount() > 0 && view.prompt("Remove another DVD? ( Y or N : )").equalsIgnoreCase("y"));
        }

    }

    public void showDvd() {
        // show the information for a single DVD
        Dvd dvdToShow;

        // get the Dvd to show
        dvdToShow = findDvd("Find DVD");

        if (dvdToShow != null) {
            // display the Dvd info
            view.print("\n");
            view.displayDvd(dvdToShow);

        }
    }

    public Dvd findDvd(String bannerString) {
        // display a message
        view.displayBanner(bannerString);

        // get the title to look for
        String title = view.getTitle();

        // get the Dvd if found, returns null if not
        Dvd dvd = dao.getDvdByTitle(title);

        if (dvd == null) {
            // did not find that title
            view.prompt("No DVD found with Title: " + title + "\nPress Enter to continue");
        }

        // dvd is null if not found
        return dvd;

    }

    public void editDvd() throws DvdLibraryDaoException {

        Dvd dvd;

        // see if there are any DVDs to edit
        if (dao.getCount() == 0) {
            // no DVDs to edit.
            view.prompt("There are no DVDs to Edit.  Press Enter to continue.");
        } else {
            do {
                // get the Dvd to edit
                dvd = findDvd("EDIT DVD");

                if (dvd != null) {
                    // found the DVD to edit

                    // show the Dvd fields
                    view.displayDvd(dvd);

                    // get the new information for this Dvd
                    view.println("Please enter new information for this DVD");
                    Dvd newDvd = view.getDvdInfo();

                    // delete the original
                    dao.deleteDvd(dvd);

                    // "replace" with the new
                    dao.addDvd(newDvd);
                    view.prompt("The new information has been saved.\nPress Enter to continue.");
                }
                // continue editng Dvds as long as the user wants
            } while (view.prompt("Edit another DVD? ( Y or N : )").equalsIgnoreCase("y"));

        }

    }

    public void showDvdList() {
        // diplay the entire list in the DvdLibrary

        // get the List
        List<Dvd> dvdList = dao.getAllDvds();

        // display the List
        view.displayDvdList(dvdList);
    }

    private void unknownCommand() {
        view.displayBanner("Unknown Command!");
    }

    private void exitMessage() {
        view.displayBanner("Exiting...");
    }

// getters and setters
}
