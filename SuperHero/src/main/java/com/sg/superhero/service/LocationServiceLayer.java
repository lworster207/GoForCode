/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.service;

import com.sg.superhero.model.Address;
import com.sg.superhero.model.Location;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface LocationServiceLayer {

    public Location addLocation(Integer locationId, Location location, Address address);

    public Location updateLocation(Integer locationId, Location location, Address address);

    public Location getLocation(Integer locationId);

    public Location deleteLocation(Integer locationId);

    public List<Location> getAllLocations();

}
