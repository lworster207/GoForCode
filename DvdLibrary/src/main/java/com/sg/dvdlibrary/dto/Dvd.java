/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dto;

/**
 *
 * @author apprentice
 */
public class Dvd {

    // properties
    public String title;
    public String releaseDate;
    public String mpaaRating;
    public String director;
    public String studio;
    public String userNote;

    // contructors
    public Dvd(String title, String releaseDate, String mpaaRating, String director, String studio, String userNote) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.mpaaRating = mpaaRating;
        this.director = director;
        this.studio = studio;
        this.userNote = userNote;
    }

    // getters
    // no setters since edits are complete replacements and not done field by field
    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getMpaaRating() {
        return mpaaRating;
    }

    public String getDirector() {
        return director;
    }

    public String getStudio() {
        return studio;
    }

    public String getUserNote() {
        return userNote;
    }

}
