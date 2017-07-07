/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.service;

import com.sg.superhero.dao.AddressDao;
import com.sg.superhero.dao.LocationDao;
import com.sg.superhero.model.Address;
import com.sg.superhero.model.Location;
import java.util.List;
import javax.inject.Inject;
import org.springframework.dao.DataIntegrityViolationException;

/**
 *
 * @author apprentice
 */
public class LocationServiceLayerImpl implements LocationServiceLayer {

    @Inject
    LocationDao locationDao;

    @Inject
    AddressDao addressDao;

    @Override
    public Location addLocation(Integer locationId, Location location, Address address) {
        String addressId;

        if (address == null) {
            addressId = null;
            location.setAddressId(null);
        } else {
            addressDao.addAddress(0, address);
            location.setAddressId(address.getAddressId());
        }
        return locationDao.addLocation(locationId, location);
    }

    @Override
    public Location deleteLocation(Integer locationId) throws DataIntegrityViolationException {
        return locationDao.deleteLocation(locationId);
    }

    @Override
    public Location updateLocation(Integer locationId, Location location, Address address) {
        if (address == null) {
            location.setAddressId(null);
        } else {
            if (address.getAddressId() > 0) {
                addressDao.updateAddress(address.getAddressId(), address);
            } else {
                addressDao.addAddress(0, address);
                location.setAddressId(address.getAddressId());
            }
        }
        return locationDao.updateLocation(location.getLocationId(), location);
    }

    @Override
    public Location getLocation(Integer locationId) {
        return locationDao.getLocation(locationId);
    }

    @Override
    public List<Location> getAllLocations() {
        return locationDao.getAllLocations();
    }

}
