/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.service;

import com.sg.superhero.model.Sighting;
import com.sg.superhero.model.SightingLocationHero;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface SightingServiceLayer {

    public List<SightingLocationHero> getSightingsByDateDetailed(String date);

    public List<SightingLocationHero> getSightingsByLocationDetailed(String locationId);

    public List<SightingLocationHero> getSightingsByHeroDetailed(String heroId);

    public List<SightingLocationHero> getAllSightingsDetailed();

    public List<Sighting> getSightingByLocation(String locationId);

    public List<Sighting> getSightingsByHero(String heroId);

    public List<Sighting> getSightingsByDate(String date);

    public Sighting addSighting(String sightingId, Sighting sighting);

    public Sighting deleteSighting(String sightingId);

    public Sighting getSighting(String sightingId);

    public List<Sighting> getAllSightings();

    public Sighting updateSighting(String sightingId, Sighting sighting);

}
