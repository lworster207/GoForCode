/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.service;

import com.sg.superhero.model.Address;
import com.sg.superhero.model.Hero;
import com.sg.superhero.model.Organization;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface OrganizationServiceLayer {

    public Organization addOrganization(String organizationId, Organization organization);

    public Organization deleteOrganization(String organizationId, String addressId);

    public Organization updateOrganization(String organizationId, Organization organization, Address address);

    public Organization getOrganization(String organizationId);

    public List<Hero> getHerosByOrganization(String organizationId);

    public List<Organization> getOrganizationsByHero(String heroId);

    public List<Organization> getAllOrganizations();

    public List<Organization> updateOrganizationsForHeroByOrganizationIds(String heroId, List<String> organizationIds);

}
