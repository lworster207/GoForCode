/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.service;

import com.sg.supercontact.dao.ContactDao;
import com.sg.superhero.dao.AddressDao;
import com.sg.superhero.dao.HeroDao;
import com.sg.superhero.dao.OrganizationDao;
import com.sg.superhero.dao.SuperPowerDao;
import com.sg.superhero.model.Address;
import com.sg.superhero.model.Contact;
import com.sg.superhero.model.Hero;
import com.sg.superhero.model.Organization;
import com.sg.superhero.model.SuperPower;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class SuperHeroServiceLayerDbImpl implements SuperHeroServiceLayer {

    HeroDao heroDao;
    ContactDao contactDao;
    AddressDao addressDao;
    OrganizationDao organizationDao;
    SuperPowerDao superpowerDao;

    public SuperHeroServiceLayerDbImpl(HeroDao heroDao, ContactDao contactDao, AddressDao addressDao, OrganizationDao organizationDao, SuperPowerDao superpowerDao) {
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
    public Hero deleteHero(String heroId) {
        return heroDao.deleteHero(heroId);
    }

    @Override
    public Hero updateHero(String heroId, Hero hero) {
        return heroDao.updateHero(heroId, hero);
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
    public Contact addContact(String contactId, Contact contact) {
        return contactDao.addContact(contactId, contact);
    }

    @Override
    public Contact deleteContact(String contactId) {
        return contactDao.deleteContact(contactId);
    }

    @Override
    public Contact updateContact(String contactId, Contact contact) {
        return contactDao.updateContact(contactId, contact);
    }

    @Override
    public Contact getContact(String contactId) {
        return contactDao.getContact(contactId);
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

}