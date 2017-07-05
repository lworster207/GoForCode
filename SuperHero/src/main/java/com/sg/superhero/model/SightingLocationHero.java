/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author apprentice
 */
public class SightingLocationHero {

    String heroId;
    String heroName;
    String sightingId;
    String sightingDate;
    String locationId;
    String locationName;
    String locationDescription;

    public String getHeroId() {
        return heroId;
    }

    public void setHeroId(String heroId) {
        this.heroId = heroId;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public String getSightingId() {
        return sightingId;
    }

    public void setSightingId(String sightingId) {
        this.sightingId = sightingId;
    }

    public String getSightingDate() {
        return sightingDate;
    }

    public void setSightingDate(String sightingDate) {
        this.sightingDate = sightingDate;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }

    public static final class SightingLocationHeroMapper implements RowMapper<SightingLocationHero> {

        public SightingLocationHero mapRow(ResultSet rs, int rowNum) throws SQLException {
            SightingLocationHero slh = new SightingLocationHero();

            slh.setSightingId(rs.getString("SightingId"));
            slh.setLocationId(rs.getString("LocationId"));
            slh.setLocationName(rs.getString("Name"));
            slh.setLocationDescription(rs.getString("Description"));
            slh.setHeroId(rs.getString("HeroId"));
            slh.setHeroName(rs.getString("HeroName"));
            slh.setSightingDate(rs.getString("Date"));

            return slh;
        }

    }
}
