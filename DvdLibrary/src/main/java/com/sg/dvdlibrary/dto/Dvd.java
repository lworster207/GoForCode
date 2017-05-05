/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dto;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author apprentice
 */
public class Dvd {

    // properties
    public String title;
    public LocalDate releaseDate;
    public String mpaaRating;
    public String director;
    public String studio;
    public String userNote;

    // contructors
    public Dvd(String title, LocalDate releaseDate, String mpaaRating, String director, String studio, String userNote) {
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
        return releaseDate.toString();
    }

    public LocalDate getReleaseDateByDate() {
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.title);
        hash = 17 * hash + Objects.hashCode(this.releaseDate);
        hash = 17 * hash + Objects.hashCode(this.mpaaRating);
        hash = 17 * hash + Objects.hashCode(this.director);
        hash = 17 * hash + Objects.hashCode(this.studio);
        hash = 17 * hash + Objects.hashCode(this.userNote);
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
        final Dvd other = (Dvd) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.releaseDate, other.releaseDate)) {
            return false;
        }
        if (!Objects.equals(this.mpaaRating, other.mpaaRating)) {
            return false;
        }
        if (!Objects.equals(this.director, other.director)) {
            return false;
        }
        if (!Objects.equals(this.studio, other.studio)) {
            return false;
        }
        if (!Objects.equals(this.userNote, other.userNote)) {
            return false;
        }
        return true;
    }

}
