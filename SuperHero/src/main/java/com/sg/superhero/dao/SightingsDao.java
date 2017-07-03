/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.dao;

import com.sg.superhero.model.Sighting;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface SightingsDao {

    public Sighting addSighting(String sightingId, Sighting sighting);

    public Sighting deleteSighting(String sightingId);

    public Sighting updateSighting(String sightingId, Sighting sighting);

    public Sighting getSighting(String sightingId);

    public List<Sighting> getAllSightings();
}
