/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.dao;

import com.sg.superhero.model.Organization;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface OrganizationDao {

    public Organization addOrganization(String organizationId, Organization organization);

    public Organization deleteOrganization(String organizationId);

    public Organization updateOrganization(String organizationId, Organization organization);

    public Organization getOrganization(String organizationId);

    public List<Organization> getAllOrganizations();

}
