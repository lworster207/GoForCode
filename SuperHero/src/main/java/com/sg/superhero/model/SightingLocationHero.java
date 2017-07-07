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

    Integer heroId;
    String heroName;
    Integer sightingId;
    String sightingDate;
    Integer locationId;
    String locationName;
    String locationDescription;

    public Integer getHeroId() {
        return heroId;
    }

    public void setHeroId(Integer heroId) {
        this.heroId = heroId;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public Integer getSightingId() {
        return sightingId;
    }

    public void setSightingId(Integer sightingId) {
        this.sightingId = sightingId;
    }

    public String getSightingDate() {
        return sightingDate;
    }

    public void setSightingDate(String sightingDate) {
        this.sightingDate = sightingDate;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
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

            slh.setSightingId(rs.getInt("SightingId"));
            slh.setLocationId(rs.getInt("LocationId"));
            slh.setLocationName(rs.getString("Name"));
            slh.setLocationDescription(rs.getString("Description"));
            slh.setHeroId(rs.getInt("HeroId"));
            slh.setHeroName(rs.getString("HeroName"));
            slh.setSightingDate(rs.getString("Date"));

            return slh;
        }

    }
}
