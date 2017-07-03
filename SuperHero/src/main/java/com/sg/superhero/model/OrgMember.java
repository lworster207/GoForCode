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
public class OrgMember {

    String heroId;
    String organizationId;
    String selected;

    public String getHeroId() {
        return heroId;
    }

    public void setHeroId(String heroId) {
        this.heroId = heroId;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.organizationId);
        hash = 53 * hash + Objects.hashCode(this.selected);
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
        final OrgMember other = (OrgMember) obj;
        if (!Objects.equals(this.organizationId, other.organizationId)) {
            return false;
        }
        if (!Objects.equals(this.selected, other.selected)) {
            return false;
        }
        return true;
    }

}
