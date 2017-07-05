/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.service;

import com.sg.superhero.dao.AddressDao;
import com.sg.superhero.dao.ContactDao;
import com.sg.superhero.dao.HeroDao;
import com.sg.superhero.dao.HeroSuperpowerDao;
import com.sg.superhero.dao.LocationDao;
import com.sg.superhero.dao.OrgMemberDao;
import com.sg.superhero.dao.OrganizationDao;
import com.sg.superhero.dao.SightingsDao;
import com.sg.superhero.dao.SuperPowerDao;
import com.sg.superhero.model.Address;
import com.sg.superhero.model.Contact;
import com.sg.superhero.model.Hero;
import com.sg.superhero.model.HeroPower;
import com.sg.superhero.model.Location;
import com.sg.superhero.model.Organization;
import com.sg.superhero.model.Sighting;
import com.sg.superhero.model.SightingLocationHero;
import com.sg.superhero.model.SuperPower;
import java.util.List;
import org.springframework.dao.DataIntegrityViolationException;

/**
 *
 * @author apprentice
 */
public class SuperHeroServiceLayerDBImpl implements SuperHeroServiceLayer {

    HeroDao heroDao;
    ContactDao contactDao;
    AddressDao addressDao;
    OrganizationDao organizationDao;
    SuperPowerDao superpowerDao;
    OrgMemberDao orgMemberDao;
    HeroSuperpowerDao heroSuperPowerDao;
    LocationDao locationDao;
    SightingsDao sightingDao;

    public SuperHeroServiceLayerDBImpl(HeroDao heroDao, ContactDao contactDao, AddressDao addressDao, OrganizationDao organizationDao, SuperPowerDao superpowerDao, OrgMemberDao orgMemberDao, HeroSuperpowerDao heroSuperpowerDao, LocationDao locationDao, SightingsDao sightingDao) {
        this.heroDao = heroDao;
        this.contactDao = contactDao;
        this.addressDao = addressDao;
        this.organizationDao = organizationDao;
        this.superpowerDao = superpowerDao;
        this.orgMemberDao = orgMemberDao;
        this.heroSuperPowerDao = heroSuperpowerDao;
        this.locationDao = locationDao;
        this.sightingDao = sightingDao;
    }

    @Override
    public List<SightingLocationHero> getAllSightingsDetailed() {
        return sightingDao.getAllSightingsDetailed();
    }

    @Override
    public List<Sighting> getSightingByLocation(String locationId) {
        return sightingDao.getSightingByLocation(locationId);
    }

    @Override
    public List<Sighting> getSightingsByHero(String heroId) {
        return sightingDao.getSightingsByHero(heroId);
    }

    @Override
    public List<Sighting> getSightingsByDate(String date) {
        return sightingDao.getSightingsByDate(date);
    }

    @Override
    public Sighting addSighting(String sightingId, Sighting sighting) {
        return sightingDao.addSighting(sightingId, sighting);
    }

    @Override
    public List<Sighting> getAllSightings() {
        return sightingDao.getAllSightings();
    }

    @Override
    public Location addLocation(String locationId, Location location, Address address) {
        String addressId;

        if (address == null) {
            addressId = null;
            location.setAddressId(null);
        } else {
            addAddress("0", address);
            location.setAddressId(address.getAddressId());
        }
        return locationDao.addLocation(locationId, location);
    }

    @Override
    public Location updateLocation(String locationId, Location location, Address address) {
        if (address == null) {
            location.setAddressId(null);
        } else {
            if (address.getAddressId() != null && !address.getAddressId().equals("")) {
                addressDao.updateAddress(address.getAddressId(), address);
            } else {
                addressDao.addAddress("0", address);
                location.setAddressId(address.getAddressId());
            }
        }
        return locationDao.updateLocation(location.getLocationId(), location);
    }

    @Override
    public Location getLocation(String locationId) {
        return locationDao.getLocation(locationId);
    }

    @Override
    public List<Location> getAllLocations() {
        return locationDao.getAllLocations();
    }

    @Override
    public Hero addHero(String heroId, Hero hero) {
        return heroDao.addHero(heroId, hero);
    }

    @Override
    public Hero addHero(Hero hero, Contact contact, Address address) {
        String addressId;

        if (contact != null) {
            if (address == null) {
                addressId = null;
            } else {
                addressDao.addAddress("0", address);
            }
            contact.setAddressId(address.getAddressId());
            contactDao.addContact("0", contact);
            hero.setContactId(contact.getContactId());
        } else {
            hero.setContactId(null);
        }

        return heroDao.addHero("1", hero);

    }

    @Override
    public Hero deleteHero(String heroId, String contactId) {

        Hero removedHero = heroDao.deleteHero(heroId);

        if (contactId != null && !contactId.equals("")) {
            deleteContact(contactId, getContact(contactId).getAddressId());
        } else {
            deleteContact(contactId, null);
        }
        return removedHero;
    }

    @Override
    public Hero updateHero(String heroId, Hero hero, Contact contact, Address address) {
        if (contact != null) {
            if (hero.getContactId() == null || hero.getContactId().equals("")) {
                // adding a new contact

                hero.setContactId(addContact(contact.getContactId(), contact, address).getContactId());

            } else {
                // updating a contact
                contact.setContactId(hero.getContactId());
                address.setAddressId(contactDao.getContact(contact.getContactId()).getAddressId());
                updateContact(contact.getContactId(), contact, address);
            }

        } else {
            if (hero.getContactId() != null) {
                // need to remove the contact.
            }

        }
        return heroDao.updateHero(hero.getHeroId(), hero);
    }

    @Override
    public Hero getHero(String heroId) {
        return heroDao.getHero(heroId);
    }

    @Override
    public List<Hero> getAllHeroes() {
        return heroDao.getAllHeroes();
    }

    @Override
    public List<HeroPower> getAllHeroesAndPowers() {
        return heroDao.getAllHeroesAndPowers();
    }

    @Override
    public Contact addContact(String contactId, Contact contact, Address address) {
        if (address != null) {
            contact.setAddressId(addAddress(address.getAddressId(), address).getAddressId());
        } else {
            contact.setAddressId(null);
        }
        return contactDao.addContact(contactId, contact);
    }

    @Override
    public Contact deleteContact(String contactId, String addressId) {
        Contact removedContact = getContact(contactId);
        try {
            removedContact = contactDao.deleteContact(contactId);
            deleteAddress(addressId);
        } catch (DataIntegrityViolationException e) {
            // shared data. cannot delete.
        }
        return removedContact;
    }

    @Override
    public Contact deleteContactWithAddress(String contactId
    ) {
        Contact contact = contactDao.getContact(contactId);
        return contactDao.deleteContactWithAddress(contactId);
    }

    @Override
    public Contact updateContact(String contactId, Contact contact,
            Address address
    ) {
        if (address == null) {
            // need to add check to see if was previously set in case the address was deleted.
            contact.setAddressId(null);
        } else {

            if (contact.getAddressId() == null) {
                contact.setAddressId(addressDao.addAddress("0", address).getAddressId());
            } else {
                address.setAddressId(contact.getAddressId());
                addressDao.updateAddress(address.getAddressId(), address);
            }
        }

        return contactDao.updateContact(contactId, contact);
    }

    @Override
    public Contact getContact(String contactId
    ) {
        if (contactId != null) {
            return contactDao.getContact(contactId);
        } else {
            return null;
        }
    }

    @Override
    public List<Contact> getAllContacts() {
        return contactDao.getAllContacts();
    }

    @Override
    public Address addAddress(String addressId, Address address
    ) {
        return addressDao.addAddress(addressId, address);
    }

    @Override
    public Address deleteAddress(String addressId
    ) {
        return addressDao.deleteAddress(addressId);
    }

    @Override
    public Address updateAddress(String addressId, Address address
    ) {
        return addressDao.updateAddress(addressId, address);
    }

    @Override
    public Address getAddress(String addressId
    ) {
        return addressDao.getAddress(addressId);
    }

    @Override
    public Address findAddress(Address address
    ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Address> getAllAddresses() {
        return addressDao.getAllAddresses();
    }

    @Override
    public Organization addOrganization(String organizationId, Organization organization
    ) {
        return organizationDao.addOrganization(organizationId, organization);
    }

    @Override
    public Organization deleteOrganization(String organizationId) {

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

    @Override
    public SuperPower getSuperPower(String superPowerId
    ) {
        return superpowerDao.getSuperPower(superPowerId);
    }

    @Override
    public SuperPower addSuperPower(String superPowerId, SuperPower superPower
    ) {
        return superpowerDao.addSuperPower(superPowerId, superPower);
    }

    @Override
    public SuperPower deleteSuperPower(String superPowerId
    ) {
        return superpowerDao.deleteSuperPower(superPowerId);
    }

    @Override
    public SuperPower updateSuperPower(String superPowerId, SuperPower superPower
    ) {
        return superpowerDao.updateSuperPower(superPowerId, superPower);
    }

    @Override
    public List<SuperPower> getAllSuperPowers() {
        return superpowerDao.getAllSuperPowers();
    }

    @Override
    public List<SuperPower> getSuperPowersByHero(String heroId
    ) {
        return superpowerDao.getSuperPowersByHero(heroId);
    }

    @Override
    public List<SuperPower> updateSuperpowersForHero(String heroId, List<SuperPower> superPowers
    ) {
        return heroSuperPowerDao.updateSuperpowersForHero(heroId, superPowers);
    }

    @Override
    public List<SuperPower> updateSuperpowersForHeroBySuperpowerIds(String heroId, List<String> superPowerIds
    ) {
        return heroSuperPowerDao.updateSuperpowersForHeroBySuperpowerIds(heroId, superPowerIds);
    }

}
