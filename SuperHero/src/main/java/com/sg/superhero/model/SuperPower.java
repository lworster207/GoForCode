/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.model;

import java.util.Objects;

/**
 *
 * @author apprentice
 */
public class SuperPower {

    private String superPowerId;
    private String description;
    private String selected;

    public String getSuperPowerId() {
        return superPowerId;
    }

    public void setSuperPowerId(String superPowerId) {
        this.superPowerId = superPowerId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.description);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SuperPower other = (SuperPower) obj;
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return true;
    }

}
