/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.dao;

import com.sg.superhero.model.Hero;
import com.sg.superhero.model.Organization;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class OrganizationDaoDbImpl implements OrganizationDao {

    private static final String SQL_INSERT_ORGANIZATION
            = "insert into Organization "
            + "(Name, ContactId, StreetAddress, City, State, PostCode, Description) "
            + "values (?,?,?,?,?,?,?)";

    private static final String SQL_DELETE_ORGANIZATION
            = "delete from Organization where OrganizationId = ?";

    private static final String SQL_SELECT_ORGANIZATION
            = "select * from Organization where OrganizationId = ?";

    private static final String SQL_UPDATE_ORGANIZATION
            = "update Organization set "
            + "Name = ?, "
            + "ContactId = ?, "
            + "StreetAddress = ?, "
            + "City = ?, "
            + "State = ?, "
            + "PostCode = ?, "
            + "Description = ? "
            + "where OrganizationId = ?";

    private static final String SQL_SELECT_ALL_ORGANIZATIONS
            = "select * from Organization";

    private static final String SQL_SELECT_ALL_ORGANIZATIONS_BY_HERO
            = "select * from OrgMembers "
            + "where HeroId = ?";

    private static final String SQL_SELECT_ALL_HEROS_BY_ORGANIZATION
            = "select * from OrgMembers "
            + "where OrganizationId = ?";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Organization addOrganization(Integer organizationId, Organization organization) {
        jdbcTemplate.update(SQL_INSERT_ORGANIZATION,
                organization.getOrgName(), organization.getContactId(), organization.getStreetAddress(), organization.getCity(), organization.getStateProvince(), organization.getPostCode(), organization.getOrgDescription());

        // query the database for the id that was just assigned to the new
        // row in the database
        Integer newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        // set the new id value on the item object and return it
        organization.setOrganizationId(newId);

        return organization;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Organization deleteOrganization(Integer organizationId) {
        Organization removedOrg = getOrganization(organizationId);
        jdbcTemplate.update(SQL_DELETE_ORGANIZATION, organizationId);
        return removedOrg;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Organization updateOrganization(Integer organizationId, Organization organization) {
        jdbcTemplate.update(SQL_UPDATE_ORGANIZATION,
                organization.getOrgName(), organization.getContactId(), organization.getStreetAddress(), organization.getCity(), organization.getStateProvince(), organization.getPostCode(), organization.getOrgDescription(), organizationId);
        return getOrganization(organizationId);
    }

    @Override
    public Organization getOrganization(Integer organizationId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_ORGANIZATION,
                    new OrganizationMapper(), organizationId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Organization> getAllOrganizations() {
        List<Organization> orgList = jdbcTemplate.query(SQL_SELECT_ALL_ORGANIZATIONS,
                new OrganizationMapper());
        return orgList;
    }

    @Override
    public List<Organization> getAllOrganizationsByHero(Integer heroId) {
        List<Organization> orgList = jdbcTemplate.query(SQL_SELECT_ALL_ORGANIZATIONS_BY_HERO,
                new OrganizationMapper(), heroId);
        return orgList;
    }

    @Override
    public List<Hero> getAllHerosByOrganization(Integer organizationId) {
        List<Hero> heroList = jdbcTemplate.query(SQL_SELECT_ALL_HEROS_BY_ORGANIZATION,
                new HeroDaoDbImpl.HeroMapper(), organizationId);
        return heroList;

    }

    public static final class OrganizationMapper implements RowMapper<Organization> {

        public Organization mapRow(ResultSet rs, int rowNum) throws SQLException {

            Organization organization = new Organization();
            organization.setOrganizationId(rs.getInt("OrganizationId"));
            organization.setContactId(rs.getInt("ContactId"));
            organization.setStreetAddress(rs.getString("StreetAddress"));
            organization.setCity(rs.getString("City"));
            organization.setStateProvince(rs.getString("State"));
            organization.setPostCode(rs.getString("PostCode"));
            organization.setOrgName(rs.getString("Name"));
            organization.setOrgDescription(rs.getString("Description"));
            return organization;

        }

    }

    private static final class HeroMapper implements RowMapper<Hero> {

        public Hero mapRow(ResultSet rs, int rowNum) throws SQLException {

            Hero hero = new Hero();
            hero.setHeroId(rs.getInt("HeroId"));
            hero.setContactId(rs.getInt("ContactId"));
            hero.setHeroName(rs.getString("Name"));
            hero.setDescription(rs.getString("Description"));
            return hero;
        }

    }
}
