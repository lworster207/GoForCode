/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.dao;

import com.sg.superhero.model.SuperPower;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public class SuperPowerDaoInMemImpl implements SuperPowerDao {

    public Map<String, SuperPower> superPowers = new HashMap<>();

    @Override
    public SuperPower getSuperPower(String superPowerId) {
        return superPowers.get(superPowerId);
    }

    @Override
    public SuperPower addSuperPower(String superPowerId, SuperPower superPower) {
        SuperPower newSp;
        newSp = superPowers.put(superPowerId, superPower);
        return newSp;
    }

    @Override
    public SuperPower deleteSuperPower(String superPowerId) {
        SuperPower removedSp;
        removedSp = superPowers.get(superPowerId);
        superPowers.remove(superPowerId);
        return removedSp;
    }

    @Override
    public SuperPower updateSuperPower(String superPowerId, SuperPower superPower) {
        SuperPower sp = superPowers.get(superPowerId);
        if (sp != null) {
            sp.setDescription(superPower.getDescription());
        }
        return sp;
    }

    @Override
    public List<SuperPower> getAllSuperPowers() {
        Collection<SuperPower> c = superPowers.values();
        return new ArrayList(c);
    }

    @Override
    public List<SuperPower> getSuperPowersByHero(String heroId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
