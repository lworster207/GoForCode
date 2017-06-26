/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.dao;

import com.sg.superhero.model.Organization;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public class OrganizationDaoInMemImpl implements OrganizationDao {

    public Map<String, Organization> organizations = new HashMap<>();

    @Override
    public Organization addOrganization(String organizationId, Organization organization) {
        organizations.put(organizationId, organization);
        return organizations.get(organizationId);
    }

    @Override
    public Organization deleteOrganization(String organizationId) {
        Organization removedOrg = organizations.get(organizationId);
        organizations.remove(organizationId);
        return removedOrg;
    }

    @Override
    public Organization updateOrganization(String organizationId, Organization organization) {
        organizations.get(organizationId).setContactId(organization.getContactId());
        organizations.get(organizationId).setOrgDescription(organization.getOrgDescription());
        organizations.get(organizationId).setOrgName(organization.getOrgName());
        return organizations.get(organizationId);
    }

    @Override
    public Organization getOrganization(String organizationId) {
        return organizations.get(organizationId);
    }

    @Override
    public List<Organization> getAllOrganizations() {
        Collection<Organization> c = organizations.values();
        return new ArrayList(c);
    }

}
