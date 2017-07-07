/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.service;

import com.sg.superhero.dao.AddressDao;
import com.sg.superhero.dao.OrgMemberDao;
import com.sg.superhero.dao.OrganizationDao;
import com.sg.superhero.model.Hero;
import com.sg.superhero.model.Organization;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author apprentice
 */
public class OrganizationServiceLayerImpl implements OrganizationServiceLayer {

    @Inject
    AddressDao addressDao;

    @Inject
    OrganizationDao organizationDao;

    @Inject
    OrgMemberDao orgMemberDao;

    @Override
    public Organization addOrganization(Integer organizationId, Organization organization
    ) {
        return organizationDao.addOrganization(organizationId, organization);
    }

    @Override
    public Organization deleteOrganization(Integer organizationId) {

        orgMemberDao.deleteMembersByOrganization(organizationId);

        return organizationDao.deleteOrganization(organizationId);

    }

    @Override
    public Organization updateOrganization(Integer organizationId, Organization organization) {
        return organizationDao.updateOrganization(organizationId, organization);
    }

    @Override
    public List<Organization> updateOrganizationsForHeroByOrganizationIds(Integer heroId, List<Integer> organizationIds
    ) {
        return orgMemberDao.updateOrganizationsForHeroByOrganizationIds(heroId, organizationIds);
    }

    @Override
    public List<Hero> getHerosByOrganization(Integer organizationId
    ) {
        return orgMemberDao.getHerosByOrganization(organizationId);
    }

    @Override
    public List<Organization> getOrganizationsByHero(Integer heroId
    ) {
        return orgMemberDao.getOrganizationsByHero(heroId);
    }

    @Override
    public Organization getOrganization(Integer organizationId
    ) {
        return organizationDao.getOrganization(organizationId);
    }

    @Override
    public List<Organization> getAllOrganizations() {
        return organizationDao.getAllOrganizations();
    }

}
