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

    public List<SightingLocationHero> getSightingsByLocationDetailed(Integer locationId);

    public List<SightingLocationHero> getSightingsByHeroDetailed(Integer heroId);

    public List<SightingLocationHero> getAllSightingsDetailed();

    public List<Sighting> getSightingByLocation(Integer locationId);

    public List<Sighting> getSightingsByHero(Integer heroId);

    public List<Sighting> getSightingsByDate(String date);

    public Sighting addSighting(Integer sightingId, Sighting sighting);

    public Sighting deleteSighting(Integer sightingId);

    public Sighting getSighting(Integer sightingId);

    public List<Sighting> getAllSightings();

    public Sighting updateSighting(Integer sightingId, Sighting sighting);

}
