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

    String orgMemberId;
    String heroId;
    String organizationId;

    public String getOrgMemberId() {
        return orgMemberId;
    }

    public void setOrgMemberId(String orgMemberId) {
        this.orgMemberId = orgMemberId;
    }

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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.heroId);
        hash = 67 * hash + Objects.hashCode(this.organizationId);
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
        if (!Objects.equals(this.heroId, other.heroId)) {
            return false;
        }
        if (!Objects.equals(this.organizationId, other.organizationId)) {
            return false;
        }
        return true;
    }

}
