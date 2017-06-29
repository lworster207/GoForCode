/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.dao;

import com.sg.superhero.model.OrgMember;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
    }

    /**
     * Test of getOrganizationsByHero method, of class OrgMemberDao.
     */
    @Test
    public void testGetOrganizationsByHero() {
    }

}
