/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.service;

import com.sg.superhero.dao.SightingsDao;
import com.sg.superhero.model.Sighting;
import com.sg.superhero.model.SightingLocationHero;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author apprentice
 */
public class SightingServiceLayerImpl implements SightingServiceLayer {

    @Inject
    SightingsDao sightingDao;

    @Override
    public List<SightingLocationHero> getSightingsByDateDetailed(String date) {
        return sightingDao.getSightingsByDateDetailed(date);
    }

    @Override
    public List<SightingLocationHero> getSightingsByLocationDetailed(String locationId) {
        return sightingDao.getSightingsByLocationDetailed(locationId);
    }

    @Override
    public List<SightingLocationHero> getSightingsByHeroDetailed(String heroId) {
        return sightingDao.getSightingsByHeroDetailed(heroId);
    }

    @Override
    public List<SightingLocationHero> getAllSightingsDetailed() {
        return sightingDao.getAllSightingsDetailed();
    }

    @Override
    public List<Sighting> getSightingByLocation(String locationId) {
        return sightingDao.getSightingByLocation(locationId);
    }

    @Override
    public List<Sighting> getSightingsByHero(String heroId) {
        return sightingDao.getSightingsByHero(heroId);
    }

    @Override
    public List<Sighting> getSightingsByDate(String date) {
        return sightingDao.getSightingsByDate(date);
    }

    @Override
    public Sighting addSighting(String sightingId, Sighting sighting) {
        return sightingDao.addSighting(sightingId, sighting);
    }

    @Override
    public Sighting deleteSighting(String sightingId) {
        Sighting sighting = getSighting(sightingId);
        sightingDao.deleteSighting(sightingId);
        return sighting;
    }

    @Override
    public Sighting getSighting(String sightingId) {
        return sightingDao.getSighting(sightingId);
    }

    @Override
    public List<Sighting> getAllSightings() {
        return sightingDao.getAllSightings();
    }

    @Override
    public Sighting updateSighting(String sightingId, Sighting sighting) {
        return sightingDao.updateSighting(sightingId, sighting);
    }

}
