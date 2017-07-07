/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.dao;

import com.sg.superhero.model.Hero;
import com.sg.superhero.model.Organization;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface OrganizationDao {

    public Organization addOrganization(Integer organizationId, Organization organization);

    public Organization deleteOrganization(Integer organizationId);

    public Organization updateOrganization(Integer organizationId, Organization organization);

    public Organization getOrganization(Integer organizationId);

    public List<Organization> getAllOrganizations();

    public List<Organization> getAllOrganizationsByHero(Integer heroId);

    public List<Hero> getAllHerosByOrganization(Integer organizationId);

}
