/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.dao;

import com.sg.superhero.model.Address;
import com.sg.superhero.model.Contact;
import com.sg.superhero.model.Organization;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author apprentice
 */
public class OrganizationDaoTest {

    public static OrganizationDao dao = new OrganizationDaoInMemImpl();

    public OrganizationDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        List<Organization> orgList = dao.getAllOrganizations();
        for (Organization org : orgList) {
            dao.deleteOrganization(org.getOrganizationId());
        }
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addOrganization method, of class OrganizationDao.
     */
    @Test
    public void testAddOrganization() {

        Address address = new Address();
        address.setAddressId("1");
        address.setStreetAddress("123 Elm Street");
        address.setCity("Portland");
        address.setStateProvince("Maine");
        address.setPostCode("04101");

        Contact contact = new Contact();
        contact.setContactId("1");
        contact.setFirstName("Bob");
        contact.setLastName("Jones");
        contact.setAddressId(address.getAddressId());

        String organizationId = "1";
        Organization organization = new Organization();
        organization.setAddressId(address.getAddressId());
        organization.setContactId(contact.getContactId());
        organization.setDescription("Organization description");
        organization.setName("Organization Name");

        Organization expOrg = dao.addOrganization(organizationId, organization);
        Organization resultOrg = dao.getOrganization(organizationId);
        Assert.assertEquals(expOrg, resultOrg);

    }

    /**
     * Test of deleteOrganization method, of class OrganizationDao.
     */
    @Test
    public void testDeleteOrganization() {

        Address address = new Address();
        address.setAddressId("1");
        address.setStreetAddress("123 Elm Street");
        address.setCity("Portland");
        address.setStateProvince("Maine");
        address.setPostCode("04101");

        Contact contact = new Contact();
        contact.setContactId("1");
        contact.setFirstName("Bob");
        contact.setLastName("Jones");
        contact.setAddressId(address.getAddressId());

        String organizationId = "1";
        Organization organization = new Organization();
        organization.setAddressId(address.getAddressId());
        organization.setContactId(contact.getContactId());
        organization.setDescription("Organization description");
        organization.setName("Organization Name");

        Organization expOrg = dao.addOrganization(organizationId, organization);
        Organization resultOrg = dao.getOrganization(organizationId);
        Assert.assertEquals(expOrg, resultOrg);

        dao.deleteOrganization(organizationId);
        resultOrg = dao.getOrganization(organizationId);
        Assert.assertEquals(null, resultOrg);
    }

    /**
     * Test of updateOrganization method, of class OrganizationDao.
     */
    @Test
    public void testUpdateOrganization() {

        Address address = new Address();
        address.setAddressId("1");
        address.setStreetAddress("123 Elm Street");
        address.setCity("Portland");
        address.setStateProvince("Maine");
        address.setPostCode("04101");

        Contact contact = new Contact();
        contact.setContactId("1");
        contact.setFirstName("Bob");
        contact.setLastName("Jones");
        contact.setAddressId(address.getAddressId());

        String organizationId = "1";
        Organization organization = new Organization();
        organization.setAddressId(address.getAddressId());
        organization.setContactId(contact.getContactId());
        organization.setDescription("Organization description");
        organization.setName("Organization Name");

        Organization expOrg = dao.addOrganization(organizationId, organization);
        Organization resultOrg = dao.getOrganization(organizationId);
        Assert.assertEquals(expOrg, resultOrg);

    }

    /**
     * Test of getOrganization method, of class OrganizationDao.
     */
    @Test
    public void testGetOrganization() {
        Address address = new Address();
        address.setAddressId("1");
        address.setStreetAddress("123 Elm Street");
        address.setCity("Portland");
        address.setStateProvince("Maine");
        address.setPostCode("04101");

        Contact contact = new Contact();
        contact.setContactId("1");
        contact.setFirstName("Bob");
        contact.setLastName("Jones");
        contact.setAddressId(address.getAddressId());

        String organizationId = "1";
        Organization organization = new Organization();
        organization.setAddressId(address.getAddressId());
        organization.setContactId(contact.getContactId());
        organization.setDescription("Organization description");
        organization.setName("Organization Name");

        String organizationId2 = "2";
        Organization organization2 = new Organization();
        organization.setAddressId(address.getAddressId());
        organization.setContactId(contact.getContactId());
        organization.setDescription("OrganizationII description");
        organization.setName("OrganizationII Name");

        Organization expOrg = dao.addOrganization(organizationId, organization);
        Organization resultOrg = dao.getOrganization(organizationId);
        Assert.assertEquals(expOrg, resultOrg);

        dao.addOrganization(organizationId2, organization2);
        expOrg = dao.getOrganization(organizationId2);

        dao.updateOrganization(organizationId, organization2);
        resultOrg = dao.getOrganization(organizationId);
        Assert.assertEquals(expOrg, resultOrg);

    }

    /**
     * Test of getAllOrganizations method, of class OrganizationDao.
     */
    @Test
    public void testGetAllOrganizations() {
        Address address = new Address();
        address.setAddressId("1");
        address.setStreetAddress("123 Elm Street");
        address.setCity("Portland");
        address.setStateProvince("Maine");
        address.setPostCode("04101");

        Contact contact = new Contact();
        contact.setContactId("1");
        contact.setFirstName("Bob");
        contact.setLastName("Jones");
        contact.setAddressId(address.getAddressId());

        String organizationId = "1";
        Organization organization = new Organization();
        organization.setAddressId(address.getAddressId());
        organization.setContactId(contact.getContactId());
        organization.setDescription("Organization description");
        organization.setName("Organization Name");

        String organizationId2 = "2";
        Organization organization2 = new Organization();
        organization.setAddressId(address.getAddressId());
        organization.setContactId(contact.getContactId());
        organization.setDescription("OrganizationII description");
        organization.setName("OrganizationII Name");
        Organization expOrg = dao.addOrganization(organizationId2, organization2);
        Organization resultOrg = dao.getOrganization(organizationId2);
        assertEquals(expOrg, resultOrg);

        List<Organization> orgList = dao.getAllOrganizations();
        assertEquals(2, orgList.size());

    }

}
