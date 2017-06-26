/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.dao;

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
public class OrganizationDaoDbImpl implements OrganizationDao {

    private static final String SQL_INSERT_ITEM
            = "insert into Organization "
            + "(Name, ContactId, AddressId, Description) "
            + "values (?,?,?,?)";

    private static final String SQL_DELETE_ITEM
            = "delete from Organization where OrganizationId = ?";

    private static final String SQL_SELECT_ITEM
            = "select * from Organization where OrganizationId = ?";

    private static final String SQL_UPDATE_ITEM
            = "update Organization set "
            + "Name = ?, "
            + "ContactId = ?, "
            + "Description = ? "
            + "where OrganizationId = ?";

    private static final String SQL_SELECT_ALL_ITEMS
            = "select * from Organization";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Organization addOrganization(String organizationId, Organization organization) {
        jdbcTemplate.update(SQL_INSERT_ITEM,
                organization.getOrgName(), organization.getContactId(), organization.getAddressId(), organization.getOrgDescription());

        // query the database for the id that was just assigned to the new
        // row in the database
        Integer newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        // set the new id value on the item object and return it
        organization.setOrganizationId(newId.toString());

        return organization;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Organization deleteOrganization(String organizationId) {
        Organization removedOrg = getOrganization(organizationId);
        jdbcTemplate.update(SQL_DELETE_ITEM, organizationId);
        return removedOrg;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Organization updateOrganization(String organizationId, Organization organization) {
        jdbcTemplate.update(SQL_UPDATE_ITEM,
                organization.getOrgName(), organization.getContactId(), organization.getOrgDescription(), organizationId);
        return getOrganization(organizationId);
    }

    @Override
    public Organization getOrganization(String organizationId) {
        return jdbcTemplate.queryForObject(SQL_SELECT_ITEM,
                new OrganizationMapper(), organizationId);
    }

    @Override
    public List<Organization> getAllOrganizations() {
        List<Organization> orgList = jdbcTemplate.query(SQL_SELECT_ALL_ITEMS,
                new OrganizationMapper());
        return orgList;
    }

    private static final class OrganizationMapper implements RowMapper<Organization> {

        public Organization mapRow(ResultSet rs, int rowNum) throws SQLException {

            Organization organization = new Organization();
            organization.setOrganizationId(rs.getString("OrganizationId"));
            organization.setContactId(rs.getString("ContactId"));
            organization.setAddressId(rs.getString("AddressId"));
            organization.setOrgName(rs.getString("Name"));
            organization.setOrgDescription(rs.getString("Description"));
            return organization;

        }

    }
}
