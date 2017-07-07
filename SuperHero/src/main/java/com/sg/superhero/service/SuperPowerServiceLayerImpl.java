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
    public SuperPower getSuperPower(Integer superPowerId
    ) {
        return superpowerDao.getSuperPower(superPowerId);
    }

    @Override
    public SuperPower addSuperPower(Integer superPowerId, SuperPower superPower
    ) {
        return superpowerDao.addSuperPower(superPowerId, superPower);
    }

    @Override
    public SuperPower deleteSuperPower(Integer superPowerId
    ) {
        return superpowerDao.deleteSuperPower(superPowerId);
    }

    @Override
    public SuperPower updateSuperPower(Integer superPowerId, SuperPower superPower
    ) {
        return superpowerDao.updateSuperPower(superPowerId, superPower);
    }

    @Override
    public List<SuperPower> getAllSuperPowers() {
        return superpowerDao.getAllSuperPowers();
    }

    @Override
    public List<SuperPower> getSuperPowersByHero(Integer heroId
    ) {
        return superpowerDao.getSuperPowersByHero(heroId);
    }

    @Override
    public List<SuperPower> updateSuperpowersForHero(Integer heroId, List<SuperPower> superPowers
    ) {
        return heroSuperPowerDao.updateSuperpowersForHero(heroId, superPowers);
    }

    @Override
    public List<SuperPower> updateSuperpowersForHeroBySuperpowerIds(Integer heroId, List<Integer> superPowerIds
    ) {
        return heroSuperPowerDao.updateSuperpowersForHeroBySuperpowerIds(heroId, superPowerIds);
    }

}
