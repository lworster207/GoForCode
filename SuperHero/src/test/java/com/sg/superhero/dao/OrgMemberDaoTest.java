/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.dao;

import static com.sg.superhero.dao.OrganizationDaoTest.dao;
import com.sg.superhero.model.Hero;
import com.sg.superhero.model.OrgMember;
import com.sg.superhero.model.Organization;
import java.util.ArrayList;
import java.util.List;
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

        List<Organization> orgList = organizationDao.getAllOrganizations();
        for (Organization org : orgList) {
            organizationDao.deleteOrganization(org.getOrganizationId());
        }

        List<Hero> heroList = heroDao.getAllHeroes();
        for (Hero hero : heroList) {
            heroDao.deleteHero(hero.getHeroId());
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of deleteMembersByOrganization method, of class OrgMemberDao.
     */
    @Test
    public void testDeleteMembersByOrganization() {
    }

    /**
     * Test of deleteMembersByHero method, of class OrgMemberDao.
     */
    @Test
    public void testDeleteMembersByHero() {
        Hero hero = new Hero("heroName", null, "super hero description");
        Hero hero2 = new Hero("heroName", null, "super hero description");
        Hero hero3 = new Hero("heroName", null, "super hero description");

        hero = heroDao.addHero("1", hero);
        hero2 = heroDao.addHero("2", hero2);
        hero3 = heroDao.addHero("3", hero3);

        String organizationId = "1";
        Organization organization = new Organization();

        organization.setOrgDescription("Organization description");
        organization.setOrgName("Organization Name");

        String organizationId2 = "2";
        Organization organization2 = new Organization();

        organization2.setOrgDescription("OrganizationII description");
        organization2.setOrgName("OrganizationII Name");
        Organization expOrg = organizationDao.addOrganization(organization2.getOrganizationId(), organization2);
        expOrg = organizationDao.addOrganization(organization.getOrganizationId(), organization);

        List<String> orgs = new ArrayList();

        orgs.add(organization.getOrganizationId());
        orgs.add(organization2.getOrganizationId());

        orgMemberDao.updateOrganizationsForHeroByOrganizationIds(hero.getHeroId(), orgs);
        orgMemberDao.updateOrganizationsForHeroByOrganizationIds(hero2.getHeroId(), orgs);
        orgMemberDao.updateOrganizationsForHeroByOrganizationIds(hero3.getHeroId(), orgs);

        orgMemberDao.deleteMembersByHero(hero.getHeroId());
        List<OrgMember> orgMembers = orgMemberDao.getAllOrgMembers();
        assertEquals(4, orgMembers.size());
    }

    /**
     * Test of getAllOrgMembers method, of class OrgMemberDao.
     */
    @Test
    public void testGetAllOrgMembers() {

        Hero hero = new Hero("heroName", null, "super hero description");
        Hero hero2 = new Hero("heroName", null, "super hero description");
        Hero hero3 = new Hero("heroName", null, "super hero description");

        hero = heroDao.addHero("1", hero);
        hero2 = heroDao.addHero("2", hero2);
        hero3 = heroDao.addHero("3", hero3);

        String organizationId = "1";
        Organization organization = new Organization();

        organization.setOrgDescription("Organization description");
        organization.setOrgName("Organization Name");

        String organizationId2 = "2";
        Organization organization2 = new Organization();

        organization2.setOrgDescription("OrganizationII description");
        organization2.setOrgName("OrganizationII Name");
        Organization expOrg = organizationDao.addOrganization(organization2.getOrganizationId(), organization2);
        expOrg = organizationDao.addOrganization(organization.getOrganizationId(), organization);

        List<String> orgs = new ArrayList();

        orgs.add(organization.getOrganizationId());
        orgs.add(organization2.getOrganizationId());

        orgMemberDao.updateOrganizationsForHeroByOrganizationIds(hero.getHeroId(), orgs);
        orgMemberDao.updateOrganizationsForHeroByOrganizationIds(hero2.getHeroId(), orgs);
        orgMemberDao.updateOrganizationsForHeroByOrganizationIds(hero3.getHeroId(), orgs);

        List<OrgMember> orgMembers = orgMemberDao.getAllOrgMembers();
        assertEquals(orgMembers.size(), 6);
    }

    /**
     * Test of getHerosByOrganization method, of class OrgMemberDao.
     */
    @Test
    public void testGetHerosByOrganization() {
        Hero hero = new Hero("heroName", null, "super hero description");
        Hero hero2 = new Hero("heroName", null, "super hero description");
        Hero hero3 = new Hero("heroName", null, "super hero description");

        hero = heroDao.addHero("1", hero);
        hero2 = heroDao.addHero("2", hero2);
        hero3 = heroDao.addHero("3", hero3);

        String organizationId = "1";
        Organization organization = new Organization();

        organization.setOrgDescription("Organization description");
        organization.setOrgName("Organization Name");

        String organizationId2 = "2";
        Organization organization2 = new Organization();

        organization2.setOrgDescription("OrganizationII description");
        organization2.setOrgName("OrganizationII Name");
        Organization expOrg = organizationDao.addOrganization(organization2.getOrganizationId(), organization2);
        expOrg = organizationDao.addOrganization(organization.getOrganizationId(), organization);

        List<String> orgs = new ArrayList();

        orgs.add(organization.getOrganizationId());
        orgs.add(organization2.getOrganizationId());

        orgMemberDao.updateOrganizationsForHeroByOrganizationIds(hero.getHeroId(), orgs);
        orgMemberDao.updateOrganizationsForHeroByOrganizationIds(hero2.getHeroId(), orgs);
        orgMemberDao.updateOrganizationsForHeroByOrganizationIds(hero3.getHeroId(), orgs);

        List<OrgMember> orgMembers = orgMemberDao.getAllOrgMembers();
        assertEquals(6, orgMembers.size());

        List<Hero> heros = orgMemberDao.getHerosByOrganization(organization2.getOrganizationId());
        assertEquals(3, heros.size());

    }

    /**
     * Test of getOrganizationsByHero method, of class OrgMemberDao.
     */
    @Test
    public void testGetOrganizationsByHero() {
        Hero hero = new Hero("heroName", null, "super hero description");
        Hero hero2 = new Hero("heroName", null, "super hero description");
        Hero hero3 = new Hero("heroName", null, "super hero description");

        hero = heroDao.addHero("1", hero);
        hero2 = heroDao.addHero("2", hero2);
        hero3 = heroDao.addHero("3", hero3);

        String organizationId = "1";
        Organization organization = new Organization();

        organization.setOrgDescription("Organization description");
        organization.setOrgName("Organization Name");

        String organizationId2 = "2";
        Organization organization2 = new Organization();

        organization2.setOrgDescription("OrganizationII description");
        organization2.setOrgName("OrganizationII Name");
        Organization expOrg = organizationDao.addOrganization(organization2.getOrganizationId(), organization2);
        expOrg = organizationDao.addOrganization(organization.getOrganizationId(), organization);

        List<String> orgs = new ArrayList();

        orgs.add(organization.getOrganizationId());
        orgs.add(organization2.getOrganizationId());

        orgMemberDao.updateOrganizationsForHeroByOrganizationIds(hero.getHeroId(), orgs);
        orgMemberDao.updateOrganizationsForHeroByOrganizationIds(hero2.getHeroId(), orgs);
        orgMemberDao.updateOrganizationsForHeroByOrganizationIds(hero3.getHeroId(), orgs);

        List<Organization> orgList = orgMemberDao.getOrganizationsByHero(hero.getHeroId());
        assertEquals(2, orgList.size());

    }

    /**
     * Test of truncateOrgMembers method, of class OrgMemberDao.
     */
    @Test
    public void testTruncateOrgMembers() {
    }

    /**
     * Test of updateOrganizationsForHeroByOrganizationIds method, of class
     * OrgMemberDao.
     */
    @Test
    public void testUpdateOrganizationsForHeroByOrganizationIds() {
        Hero hero = new Hero("heroName", null, "super hero description");

        Hero result = heroDao.addHero("1", hero);

        String organizationId = "1";
        Organization organization = new Organization();

        organization.setOrgDescription("Organization description");
        organization.setOrgName("Organization Name");

        String organizationId2 = "2";
        Organization organization2 = new Organization();

        organization2.setOrgDescription("OrganizationII description");
        organization2.setOrgName("OrganizationII Name");
        Organization expOrg = dao.addOrganization(organizationId2, organization2);
        expOrg = dao.addOrganization(organizationId, organization);

        List<String> orgs = new ArrayList();

        orgs.add("1");
        orgs.add("2");

        orgMemberDao.updateOrganizationsForHeroByOrganizationIds("1", orgs);
        List<OrgMember> orgMembers = orgMemberDao.getAllOrgMembers();
        assertEquals(orgMembers.size(), 2);

    }

}
