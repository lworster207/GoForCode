/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmaster.dao;

import com.sg.flooringmaster.dto.TaxRate;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface TaxRateDao {

    public TaxRate getTaxRate(String state);

    public List<TaxRate> getAllTaxRates();

}
