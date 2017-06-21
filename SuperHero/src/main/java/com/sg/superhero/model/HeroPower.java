/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.model;

/**
 *
 * @author apprentice
 */
public class HeroPower {

    String heroId;
    String heroName;
    String contactId;
    String description;
    String superpower;

    public String getHeroId() {
        return heroId;
    }

    public void setHeroId(String heroId) {
        this.heroId = heroId;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getHeroDescription() {
        return description;
    }

    public void setHeroDescription(String heroDescription) {
        this.description = heroDescription;
    }

    public String getSuperPower() {
        return superpower;
    }

    public void setSuperPower(String superPowerDescription) {
        this.superpower = superPowerDescription;
    }

}
