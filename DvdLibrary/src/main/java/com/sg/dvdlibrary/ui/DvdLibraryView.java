/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.ui;

import com.sg.dvdlibrary.dto.Dvd;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class DvdLibraryView {

    private UserIO io;

    public DvdLibraryView(UserIO io) {
        this.io = io;
    }

    public int displayMenuAndGetOption() {
        int choice;

        io.println("\n\n");
        displayBanner("Main Menu");
        io.println("1. Display Dvd Library");
        io.println("2. Find Dvd");
        io.println("3. Add Dvd");
        io.println("4. Remove Dvd");
        io.println("5. Edit Dvd");
        io.println("6. Exit");

        try {
            choice = io.readInt("Please select from the above choices.", 1, 6);
        } catch (NumberFormatException e) {
            choice = 0;
        }
        return choice;

    }

    public Dvd getDvdInfo() {
        // get the Dvd information for all fields and return a new Dvd

        Boolean needValidDate = true;
        String releaseDate;
        LocalDate localDate;

        localDate = null;

        displayBanner("Enter the Dvd information");
        String title = io.readString("Title: ");

        localDate = getReleaseDate();

        String mpaaRating = io.readString("MPAA Rating: ");
        String director = io.readString("Director: ");
        String studio = io.readString("Studio: ");
        String userNote = io.readString("Notes: ");

        return (new Dvd(title, localDate, mpaaRating, director, studio, userNote));
    }

    public LocalDate getReleaseDate() {
        LocalDate ld = null;
        Boolean invalidDate = true;

        while (invalidDate) {
            invalidDate = false;
            ld = io.readLocalDate("Release Date:", 1, 12, 1, 31, 1889, LocalDate.now().getYear());

            switch (ld.getMonthValue()) {
                case 2:  // Feb
                    if (ld.isLeapYear()) {
                        if (ld.getDayOfMonth() > 29) {
                            invalidDate = true;
                        } else if (ld.getDayOfMonth() > 28) {
                            invalidDate = true;
                        }
                    }
                    break;
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    if (ld.getDayOfMonth() > 31) {
                        invalidDate = true;
                    }
                    break;
                default:
                    if (ld.getDayOfMonth() > 30) {
                        invalidDate = true;
                    }
                    break;
            }
        }
        return ld;
    }

    public String getTitle() {

        // ask the user for the Dvd title
        return (io.readString("Please enter the DVD Title: "));

    }

    public String prompt(String prompt) {
        return io.readString(prompt);
    }

    public void displayDvd(Dvd dvd) {

        // display the Dvd fields - one per line
        io.println("Title: " + dvd.getTitle());
        io.println("Release Date: " + dvd.getReleaseDate());
        io.println("MPAA Rating: " + dvd.getMpaaRating());
        io.println("Director: " + dvd.getDirector());
        io.println("Studio: " + dvd.getStudio());
        io.println("Note: " + dvd.getUserNote());
    }

    public void displayDvdSingleLine(Dvd dvd) {
        // display the Dvd fields on a single line
        io.println(dvd.getTitle() + " | " + dvd.getReleaseDate()
                + " | " + dvd.getMpaaRating() + " | " + dvd.getDirector()
                + " | " + dvd.getStudio() + " | " + dvd.getUserNote());
    }

    public void displayDvdList(List<Dvd> allDvds) {

        displayBanner("All DVDs");
        io.println(" Title  |  ReleaseDate  |  MPAA Rating  | Director  | Studio  | Note ");
        for (Dvd dvd : allDvds) {
            displayDvdSingleLine(dvd);
        }
    }

    public void displayBanner(String banner) {
        io.println("===== " + banner + " =====");
    }

    public void print(String message) {
        io.print(message);
    }

    public void println(String message) {
        io.println(message);
    }

    public void displayErrorMessage(String errorMsg) {
        io.println("=== ERROR ===");
        io.print(errorMsg);
    }

}
