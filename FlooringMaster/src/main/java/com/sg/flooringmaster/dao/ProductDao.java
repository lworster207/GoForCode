/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmaster.dao;

import com.sg.flooringmaster.dto.Product;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface ProductDao {

    public Product getProduct(String name);

    public List<Product> getAllProducts();
}
