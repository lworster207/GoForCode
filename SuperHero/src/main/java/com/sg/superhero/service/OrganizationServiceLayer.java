/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.service;

import com.sg.superhero.model.Hero;
import com.sg.superhero.model.Organization;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface OrganizationServiceLayer {

    public Organization addOrganization(Integer organizationId, Organization organization);

    public Organization deleteOrganization(Integer organizationId);

    public Organization updateOrganization(Integer organizationId, Organization organization);

    public Organization getOrganization(Integer organizationId);

    public List<Hero> getHerosByOrganization(Integer organizationId);

    public List<Organization> getOrganizationsByHero(Integer heroId);

    public List<Organization> getAllOrganizations();

    public List<Organization> updateOrganizationsForHeroByOrganizationIds(Integer heroId, List<Integer> organizationIds);

}
