/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.service;

import com.sg.superhero.model.SuperPower;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface SuperPowerServiceLayer {

    public SuperPower getSuperPower(Integer superPowerId);

    public SuperPower addSuperPower(Integer superPowerId, SuperPower superPower);

    public SuperPower deleteSuperPower(Integer superPowerId);

    public SuperPower updateSuperPower(Integer superPowerId, SuperPower superPower);

    public List<SuperPower> getAllSuperPowers();

    public List<SuperPower> getSuperPowersByHero(Integer heroId);

    public List<SuperPower> updateSuperpowersForHero(Integer heroId, List<SuperPower> superPowers);

    public List<SuperPower> updateSuperpowersForHeroBySuperpowerIds(Integer heroId, List<Integer> superPowerIds);
}
