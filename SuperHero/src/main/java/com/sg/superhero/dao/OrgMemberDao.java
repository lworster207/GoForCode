/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.dao;

import com.sg.superhero.model.Hero;
import com.sg.superhero.model.OrgMember;
import com.sg.superhero.model.Organization;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface OrgMemberDao {

    public void deleteMembersByOrganization(Integer organizationId);

    public void deleteMembersByHero(Integer heroId);

    public List<OrgMember> getAllOrgMembers();

    public List<Hero> getHerosByOrganization(Integer organizationId);

    public List<Organization> getOrganizationsByHero(Integer heroId);

    public void truncateOrgMembers();

    public List<Organization> updateOrganizationsForHeroByOrganizationIds(Integer heroId, List<Integer> organizationIds);

}
