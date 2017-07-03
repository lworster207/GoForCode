/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.dao;

import com.sg.superhero.model.Hero;
import com.sg.superhero.model.OrgMember;
import com.sg.superhero.model.Organization;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class OrgMemberDbImpl implements OrgMemberDao {

    private static final String SQL_INSERT_ORGMEMBER
            = "insert into OrgMembers "
            + "(HeroId, OrganizationId) "
            + "values (?,?)";
    private static final String SQL_SELECT_GETALL_ORGMEMBERS
            = "select * from OrgMembers";

    private static final String SQL_SELECT_ORGMEMBER
            = "select * from OrgMembers where OrgMembersId = ?";

    private static final String SQL_DELETE_ORGMEMBERS_BY_ORGANIZATION
            = "delete from OrgMembers where OrganizationId = ?";

    private static final String SQL_DELETE_ORGMEMBERS_BY_HERO
            = "delete from OrgMembers where HeroId = ?";

    private static final String SQL_SELECT_ORGMEMBERS_BY_ORGANIZATION
            = "select * from Hero "
            + "join OrgMembers on Hero.HeroId = OrgMembers.HeroId "
            + "where OrgMembers.OrganizationId = ?";

    private static final String SQL_SELECT_ORGMEMBERS_BY_HERO
            = "select * from Organization org "
            + "join OrgMembers o on o.OrganizationId = org.OrganizationId "
            + "where o.HeroId = ?";

    private static final String SQL_UPDATE_ORGMEMBER
            = "update OrgMembers set "
            + "Name = ?, "
            + "ContactId = ?, "
            + "Description = ? "
            + "where OrgMemberId = ?";

    private static final String SQL_SELECT_ALL_ORGMEMBERS
            = "select * from OrgMembers";

    private static final String SQL_TRUNCATE_ORGMEMBERS
            = "truncate OrgMembers";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void deleteMembersByHero(String heroId) {

        jdbcTemplate.update(SQL_DELETE_ORGMEMBERS_BY_HERO, heroId);
    }

    @Override
    public void deleteMembersByOrganization(String organizationId) {

        //OrgMember removedOrgMember = getOrgMember(organizationId);
        jdbcTemplate.update(SQL_DELETE_ORGMEMBERS_BY_ORGANIZATION, organizationId);
        //return removedOrgMember;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<OrgMember> getAllOrgMembers() {

        return jdbcTemplate.query(SQL_SELECT_GETALL_ORGMEMBERS, new OrgMemberMapper());
    }

    @Override
    public List<Hero> getHerosByOrganization(String organizationId) {
        return jdbcTemplate.query(SQL_SELECT_ORGMEMBERS_BY_ORGANIZATION,
                new HeroDaoDbImpl.HeroMapper(), organizationId);
    }

    @Override
    public List<Organization> getOrganizationsByHero(String heroId) {
        return jdbcTemplate.query(SQL_SELECT_ORGMEMBERS_BY_HERO,
                new OrganizationDaoDbImpl.OrganizationMapper(), heroId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Organization> updateOrganizationsForHeroByOrganizationIds(String heroId, List<String> organizationIds) {
        deleteMembersByHero(heroId);
        for (String organizationId : organizationIds) {
            addOrgMemberForHero(heroId, organizationId);
        }
        return getOrganizationsByHero(heroId);
    }

    private void addOrgMemberForHero(String heroId, String organizationId) {
        jdbcTemplate.update(SQL_INSERT_ORGMEMBER,
                heroId, organizationId);
    }

    @Override
    public void truncateOrgMembers() {

        jdbcTemplate.execute(SQL_TRUNCATE_ORGMEMBERS);
    }

    public static final class OrgMemberMapper implements RowMapper<OrgMember> {

        public OrgMember mapRow(ResultSet rs, int rowNum) throws SQLException {

            OrgMember orgMember = new OrgMember();
            orgMember.setHeroId(rs.getString("HeroId"));
            orgMember.setOrganizationId(rs.getString("OrganizationId"));
            return orgMember;
        }

    }
}
