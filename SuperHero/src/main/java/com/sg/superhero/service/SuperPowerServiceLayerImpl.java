/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.service;

import com.sg.superhero.dao.HeroSuperpowerDao;
import com.sg.superhero.dao.SuperPowerDao;
import com.sg.superhero.model.SuperPower;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author apprentice
 */
public class SuperPowerServiceLayerImpl implements SuperPowerServiceLayer {

    @Inject
    SuperPowerDao superpowerDao;

    @Inject
    HeroSuperpowerDao heroSuperPowerDao;

    @Override
    public SuperPower getSuperPower(String superPowerId
    ) {
        return superpowerDao.getSuperPower(superPowerId);
    }

    @Override
    public SuperPower addSuperPower(String superPowerId, SuperPower superPower
    ) {
        return superpowerDao.addSuperPower(superPowerId, superPower);
    }

    @Override
    public SuperPower deleteSuperPower(String superPowerId
    ) {
        return superpowerDao.deleteSuperPower(superPowerId);
    }

    @Override
    public SuperPower updateSuperPower(String superPowerId, SuperPower superPower
    ) {
        return superpowerDao.updateSuperPower(superPowerId, superPower);
    }

    @Override
    public List<SuperPower> getAllSuperPowers() {
        return superpowerDao.getAllSuperPowers();
    }

    @Override
    public List<SuperPower> getSuperPowersByHero(String heroId
    ) {
        return superpowerDao.getSuperPowersByHero(heroId);
    }

    @Override
    public List<SuperPower> updateSuperpowersForHero(String heroId, List<SuperPower> superPowers
    ) {
        return heroSuperPowerDao.updateSuperpowersForHero(heroId, superPowers);
    }

    @Override
    public List<SuperPower> updateSuperpowersForHeroBySuperpowerIds(String heroId, List<String> superPowerIds
    ) {
        return heroSuperPowerDao.updateSuperpowersForHeroBySuperpowerIds(heroId, superPowerIds);
    }

}
