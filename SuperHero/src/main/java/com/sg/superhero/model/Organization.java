/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.model;

import java.util.Objects;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author apprentice
 */
public class Organization {

    private String organizationId;
    @NotEmpty(message = "You must supply a value for Organization Name.")
    @Length(max = 50, message = "Organization Name must be no more than 30 characters in length.")
    private String orgName;
    private String orgDescription;
    private String contactId;
    private String addressId;
    private String selected;

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgDescription() {
        return orgDescription;
    }

    public void setOrgDescription(String orgDescription) {
        this.orgDescription = orgDescription;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
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
        hash = 17 * hash + Objects.hashCode(this.orgName);
        hash = 17 * hash + Objects.hashCode(this.orgDescription);
        hash = 17 * hash + Objects.hashCode(this.contactId);
        hash = 17 * hash + Objects.hashCode(this.addressId);
        hash = 17 * hash + Objects.hashCode(this.selected);
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
        if (!Objects.equals(this.contactId, other.contactId)) {
            return false;
        }
        if (!Objects.equals(this.addressId, other.addressId)) {
            return false;
        }
        if (!Objects.equals(this.selected, other.selected)) {
            return false;
        }
        return true;
    }

}
