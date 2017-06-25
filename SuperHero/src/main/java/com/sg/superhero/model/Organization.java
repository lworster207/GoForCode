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
public class Organization {

    private String organizationId;
    private String orgName;
    private String orgDescription;
    private String contactId;

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getName() {
        return orgName;
    }

    public void setName(String name) {
        this.orgName = name;
    }

    public String getDescription() {
        return orgDescription;
    }

    public void setDescription(String description) {
        this.orgDescription = description;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.orgName);
        hash = 29 * hash + Objects.hashCode(this.orgDescription);
        hash = 29 * hash + Objects.hashCode(this.contactId);
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
        final Organization other = (Organization) obj;
        if (!Objects.equals(this.orgName, other.orgName)) {
            return false;
        }
        if (!Objects.equals(this.orgDescription, other.orgDescription)) {
            return false;
        }
        return true;
    }

}
