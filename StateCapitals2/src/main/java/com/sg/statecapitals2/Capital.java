/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.statecapitals2;

/**
 *
 * @author apprentice
 */
public class Capital {

    String name;
    int population;
    double squareMiles;

    // constructor
    public Capital(String name, int population, double squareMiles) {
        this.name = name;
        this.population = population;
        this.squareMiles = squareMiles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public double getSquareMiles() {
        return squareMiles;
    }

    public void setSquareMiles(double squareMiles) {
        this.squareMiles = squareMiles;
    }

}
