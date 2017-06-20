/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.dao;

import com.sg.superhero.model.SuperPower;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface SuperPowerDao {

    SuperPower getSuperPower(String superPowerId);

    SuperPower addSuperPower(String superPowerId, SuperPower superPower);

    SuperPower deleteSuperPower(String superPowerId);

    SuperPower updateSuperPower(String superPowerId, SuperPower superPower);

    List<SuperPower> getAllSuperPowers();
}
