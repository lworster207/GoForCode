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

    private static final String SQL_SELECT_ORGMEMBER
            = "select * from OrgMembers where OrgMembersId = ?";

    private static final String SQL_DELETE_ORGMEMBERS_BY_ORGANIZATION
            = "delete from OrgMembers where OrganizationId = ?";

    private static final String SQL_DELETE_ORGMEMBERS_BY_HERO
            = "delete from OrgMembers where HeroId = ?";

    private static final String SQL_SELECT_ORGMEMBERS_BY_ORGANIZATION
            = "select * from Hero h "
            + "join OrgMembers o on h.HeroId = o.HeroId "
            + "where o.OrganizationId = ?";

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
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public OrgMember addOrgMember(String orgMemberId, OrgMember orgMember) {
        jdbcTemplate.update(SQL_INSERT_ORGMEMBER,
                orgMember.getHeroId(), orgMember.getOrganizationId());

        // query the database for the id that was just assigned to the new
        // row in the database
        Integer newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        // set the new id value on the item object and return it
        orgMember.setOrgMemberId(newId.toString());

        return orgMember;
    }

    @Override
    public OrgMember deleteOrgMember(String orgMemberId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteMembersByOrganization(String organizationId) {

        //OrgMember removedOrgMember = getOrgMember(organizationId);
        jdbcTemplate.update(SQL_DELETE_ORGMEMBERS_BY_ORGANIZATION, organizationId);
        //return removedOrgMember;
    }

    @Override
    public OrgMember updateOrgMember(String orgMemberId, OrgMember orgMember) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrgMember getOrgMember(String orgMemberId) {

        return jdbcTemplate.queryForObject(SQL_SELECT_ORGMEMBER,
                new OrgMemberMapper(), orgMemberId);

    }

    @Override
    public List<OrgMember> getAllOrgMembers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Hero> getHerosByOrganization(String organizationId) {
        return jdbcTemplate.query(SQL_SELECT_ORGMEMBERS_BY_ORGANIZATION,
                new HeroDaoDbImpl.HeroMapper());
    }

    @Override
    public List<Organization> getOrganizationsByHero(String heroId) {
        return jdbcTemplate.query(SQL_SELECT_ORGMEMBERS_BY_ORGANIZATION,
                new OrganizationDaoDbImpl.OrganizationMapper());
    }

    @Override
    public void truncateOrgMembers() {

        jdbcTemplate.execute(SQL_TRUNCATE_ORGMEMBERS);
    }

    public static final class OrgMemberMapper implements RowMapper<OrgMember> {

        public OrgMember mapRow(ResultSet rs, int rowNum) throws SQLException {

            OrgMember orgMember = new OrgMember();
            orgMember.setOrgMemberId(rs.getString("OrgMembersId"));
            orgMember.setHeroId(rs.getString("HeroId"));
            orgMember.setOrganizationId(rs.getString("OrganizationId"));
            return orgMember;
        }

    }
}
