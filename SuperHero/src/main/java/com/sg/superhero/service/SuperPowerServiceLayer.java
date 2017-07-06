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

    public SuperPower getSuperPower(String superPowerId);

    public SuperPower addSuperPower(String superPowerId, SuperPower superPower);

    public SuperPower deleteSuperPower(String superPowerId);

    public SuperPower updateSuperPower(String superPowerId, SuperPower superPower);

    public List<SuperPower> getAllSuperPowers();

    public List<SuperPower> getSuperPowersByHero(String heroId);

    public List<SuperPower> updateSuperpowersForHero(String heroId, List<SuperPower> superPowers);

    public List<SuperPower> updateSuperpowersForHeroBySuperpowerIds(String heroId, List<String> superPowerIds);
}
