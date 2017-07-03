/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.dao;

import com.sg.superhero.model.Location;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface LocationDao {

    public Location addLocation(String locationId, Location location);

    public Location deleteLocation(String locationId);

    public Location updateLocation(String locationId, Location location);

    public Location getLocation(String locationId);

    public List<Location> getAllLocations();
}
