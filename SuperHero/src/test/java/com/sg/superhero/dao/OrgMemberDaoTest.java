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
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

/**
 *
 * @author apprentice
 */
public class OrgMemberDaoTest {

    HeroDao heroDao;
    OrganizationDao organizationDao;
    OrgMemberDao orgMemberDao;

    public OrgMemberDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        heroDao = ctx.getBean("heroDao", HeroDao.class);
        organizationDao = ctx.getBean("organizationDao", OrganizationDao.class);
        orgMemberDao = ctx.getBean("orgMemberDao", OrgMemberDao.class);
        //orgMemberDao = new OrgMemberDbImpl();

        orgMemberDao.truncateOrgMembers();

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addOrgMember method, of class OrgMemberDao.
     */
    @Test
    public void testAddOrgMember() {
        OrgMember orgMember = new OrgMember();
        orgMember.setOrgMemberId("1");
        orgMember.setHeroId("2");
        orgMember.setOrganizationId("1");
        orgMember = orgMemberDao.addOrgMember("1", orgMember);

        OrgMember expected = orgMemberDao.getOrgMember(orgMember.getOrgMemberId());
        assertEquals(orgMember, expected);

    }

    /**
     * Test of deleteMembersByOrganization method, of class OrgMemberDao.
     */
    @Test
    public void testDeleteMembersByOrganization() {
        OrgMember orgMember = new OrgMember();
        orgMember.setOrgMemberId("1");
        orgMember.setHeroId("2");
        orgMember.setOrganizationId("1");
        orgMember = orgMemberDao.addOrgMember("1", orgMember);

        OrgMember orgMember2 = new OrgMember();
        orgMember2.setOrgMemberId("2");
        orgMember2.setHeroId("2");
        orgMember2.setOrganizationId("2");
        orgMember2 = orgMemberDao.addOrgMember("2", orgMember2);

        OrgMember orgMember3 = new OrgMember();
        orgMember3.setOrgMemberId("2");
        orgMember3.setHeroId("2");
        orgMember3.setOrganizationId("2");
        orgMember3 = orgMemberDao.addOrgMember("2", orgMember3);

        orgMemberDao.deleteMembersByOrganization("2");
        OrgMember expected = orgMemberDao.getOrgMember(orgMember.getOrgMemberId());

        assertEquals(expected, orgMember);

        try {
            expected = orgMemberDao.getOrgMember(orgMember2.getOrgMemberId());
            fail();
        } catch (EmptyResultDataAccessException e) {
            assertEquals(1, 1);
        }

        try {
            expected = orgMemberDao.getOrgMember(orgMember3.getOrgMemberId());
            fail();
        } catch (EmptyResultDataAccessException e) {
            assertEquals(1, 1);
        }
    }

    /**
     * Test of updateOrgMember method, of class OrgMemberDao.
     */
    @Test
    public void testUpdateOrgMember() {
    }

    /**
     * Test of getOrgMember method, of class OrgMemberDao.
     */
    @Test
    public void testGetOrgMember() {
    }

    /**
     * Test of getAllOrgMembers method, of class OrgMemberDao.
     */
    @Test
    public void testGetAllOrgMembers() {
    }

    /**
     * Test of getHerosByOrganization method, of class OrgMemberDao.
     */
    @Test
    public void testGetHerosByOrganization() {
        OrgMember orgMember = new OrgMember();
        orgMember.setOrgMemberId("1");
        orgMember.setHeroId("2");
        orgMember.setOrganizationId("1");
        orgMember = orgMemberDao.addOrgMember("1", orgMember);

        OrgMember orgMember2 = new OrgMember();
        orgMember2.setOrgMemberId("2");
        orgMember2.setHeroId("2");
        orgMember2.setOrganizationId("2");
        orgMember2 = orgMemberDao.addOrgMember("2", orgMember2);

        OrgMember orgMember3 = new OrgMember();
        orgMember3.setOrgMemberId("2");
        orgMember3.setHeroId("3");
        orgMember3.setOrganizationId("2");
        orgMember3 = orgMemberDao.addOrgMember("2", orgMember3);

        // org 1 should return 1 member
        List<Hero> heroList = orgMemberDao.getHerosByOrganization("1");
        assertEquals(heroList.size(), 1);

        heroList = orgMemberDao.getHerosByOrganization("2");
        assertEquals(heroList.size(), 2);

        // org 2 should return 2 members
    }

    /**
     * Test of getOrganizationsByHero method, of class OrgMemberDao.
     */
    @Test
    public void testGetOrganizationsByHero() {
        OrgMember orgMember = new OrgMember();
        orgMember.setOrgMemberId("1");
        orgMember.setHeroId("2");
        orgMember.setOrganizationId("1");
        orgMember = orgMemberDao.addOrgMember("1", orgMember);

        OrgMember orgMember2 = new OrgMember();
        orgMember2.setOrgMemberId("2");
        orgMember2.setHeroId("2");
        orgMember2.setOrganizationId("2");
        orgMember2 = orgMemberDao.addOrgMember("2", orgMember2);

        OrgMember orgMember3 = new OrgMember();
        orgMember3.setOrgMemberId("2");
        orgMember3.setHeroId("3");
        orgMember3.setOrganizationId("2");
        orgMember3 = orgMemberDao.addOrgMember("2", orgMember3);

        List<Organization> orgList = orgMemberDao.getOrganizationsByHero("2");
        assertEquals(orgList.size(), 2);

        orgList = orgMemberDao.getOrganizationsByHero("3");
        assertEquals(orgList.size(), 1);

    }

}
