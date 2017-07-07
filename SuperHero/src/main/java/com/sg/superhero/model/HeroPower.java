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

    Integer heroId;
    String heroName;
    Integer contactId;
    String description;
    String superpower;

    public Integer getHeroId() {
        return heroId;
    }

    public void setHeroId(Integer heroId) {
        this.heroId = heroId;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
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
