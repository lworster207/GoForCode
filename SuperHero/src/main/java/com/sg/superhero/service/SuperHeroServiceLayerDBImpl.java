/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.service;

import com.sg.superhero.dao.AddressDao;
import com.sg.superhero.dao.ContactDao;
import com.sg.superhero.dao.HeroDao;
import com.sg.superhero.dao.OrganizationDao;
import com.sg.superhero.dao.SuperPowerDao;
import com.sg.superhero.model.Address;
import com.sg.superhero.model.Contact;
import com.sg.superhero.model.Hero;
import com.sg.superhero.model.HeroPower;
import com.sg.superhero.model.Organization;
import com.sg.superhero.model.SuperPower;
import java.util.List;

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

    public SuperHeroServiceLayerDBImpl(HeroDao heroDao, ContactDao contactDao, AddressDao addressDao, OrganizationDao organizationDao, SuperPowerDao superpowerDao) {
        this.heroDao = heroDao;
        this.contactDao = contactDao;
        this.addressDao = addressDao;
        this.organizationDao = organizationDao;
        this.superpowerDao = superpowerDao;

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
    public Hero deleteHero(String heroId) {
        Hero hero = heroDao.getHero(heroId);

        if (hero.getContactId() != null) {
            deleteContact(hero.getContactId());
        }

        return heroDao.deleteHero(heroId);
    }

    @Override
    public Hero updateHero(String heroId, Hero hero, Contact contact, Address address) {
        if (contact != null) {
            if (hero.getContactId() == null) {
                // adding a new contact
                if (contact != null) {
                    hero.setContactId(addContact(contact.getContactId(), contact, address).getContactId());
                }
            } else {
                // updating a contact
                contact.setContactId(hero.getContactId());
                updateContact(contact.getContactId(), contact, address);
            }

        } else {
            hero.setContactId(null);
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
    public Contact deleteContact(String contactId) {
        return contactDao.deleteContact(contactId);
        /* Contact contact = contactDao.getContact(contactId);
        String addressId = contact.getAddressId();
        if (addressId != null) {
            //addressDao.deleteAddress(addressId);
            return contactDao.deleteContactWithAddress(contactId);
        } else {
            return contactDao.deleteContact(contactId);
        }*/
    }

    @Override
    public Contact deleteContactWithAddress(String contactId) {
        Contact contact = contactDao.getContact(contactId);
        return contactDao.deleteContactWithAddress(contactId);
    }

    @Override
    public Contact updateContact(String contactId, Contact contact, Address address) {
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
    public Contact getContact(String contactId) {
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
    public Address addAddress(String addressId, Address address) {
        return addressDao.addAddress(addressId, address);
    }

    @Override
    public Address deleteAddress(String addressId) {
        return addressDao.deleteAddress(addressId);
    }

    @Override
    public Address updateAddress(String addressId, Address address) {
        return addressDao.updateAddress(addressId, address);
    }

    @Override
    public Address getAddress(String addressId) {
        return addressDao.getAddress(addressId);
    }

    @Override
    public Address findAddress(Address address) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Address> getAllAddresses() {
        return addressDao.getAllAddresses();
    }

    @Override
    public Organization addOrganization(String organizationId, Organization organization) {
        return organizationDao.addOrganization(organizationId, organization);
    }

    @Override
    public Organization deleteOrganization(String organizationId) {
        return organizationDao.deleteOrganization(organizationId);
    }

    @Override
    public Organization updateOrganization(String organizationId, Organization organization) {
        return organizationDao.updateOrganization(organizationId, organization);
    }

    @Override
    public Organization getOrganization(String organizationId) {
        return organizationDao.getOrganization(organizationId);
    }

    @Override
    public List<Organization> getAllOrganizations() {
        return organizationDao.getAllOrganizations();
    }

    @Override
    public SuperPower getSuperPower(String superPowerId) {
        return superpowerDao.getSuperPower(superPowerId);
    }

    @Override
    public SuperPower addSuperPower(String superPowerId, SuperPower superPower) {
        return superpowerDao.addSuperPower(superPowerId, superPower);
    }

    @Override
    public SuperPower deleteSuperPower(String superPowerId) {
        return superpowerDao.deleteSuperPower(superPowerId);
    }

    @Override
    public SuperPower updateSuperPower(String superPowerId, SuperPower superPower) {
        return superpowerDao.updateSuperPower(superPowerId, superPower);
    }

    @Override
    public List<SuperPower> getAllSuperPowers() {
        return superpowerDao.getAllSuperPowers();
    }

    @Override
    public List<SuperPower> getSuperPowersByHero(String heroId) {
        return superpowerDao.getSuperPowersByHero(heroId);
    }

}
