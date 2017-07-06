/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.service;

import com.sg.superhero.dao.AddressDao;
import com.sg.superhero.dao.OrgMemberDao;
import com.sg.superhero.dao.OrganizationDao;
import com.sg.superhero.model.Address;
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
    public Organization addOrganization(String organizationId, Organization organization
    ) {
        return organizationDao.addOrganization(organizationId, organization);
    }

    @Override
    public Organization deleteOrganization(String organizationId, String addressId) {

        orgMemberDao.deleteMembersByOrganization(organizationId);
        addressDao.deleteAddress(addressId);
        return organizationDao.deleteOrganization(organizationId);

    }

    @Override
    public Organization updateOrganization(String organizationId, Organization organization,
            Address address
    ) {
        if (address == null) {
            // need to add check to see if was previously set in case the address was deleted.
            organization.setAddressId(null);
        } else {

            if (organization.getAddressId() == null) {
                organization.setAddressId(addressDao.addAddress("0", address).getAddressId());
            } else {
                address.setAddressId(organization.getAddressId());
                addressDao.updateAddress(address.getAddressId(), address);
            }
        }

        return organizationDao.updateOrganization(organizationId, organization);
    }

    @Override
    public List<Organization> updateOrganizationsForHeroByOrganizationIds(String heroId, List<String> organizationIds
    ) {
        return orgMemberDao.updateOrganizationsForHeroByOrganizationIds(heroId, organizationIds);
    }

    @Override
    public List<Hero> getHerosByOrganization(String organizationId
    ) {
        return orgMemberDao.getHerosByOrganization(organizationId);
    }

    @Override
    public List<Organization> getOrganizationsByHero(String heroId
    ) {
        return orgMemberDao.getOrganizationsByHero(heroId);
    }

    @Override
    public Organization getOrganization(String organizationId
    ) {
        return organizationDao.getOrganization(organizationId);
    }

    @Override
    public List<Organization> getAllOrganizations() {
        return organizationDao.getAllOrganizations();
    }

}
