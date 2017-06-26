/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.service;

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
public interface SuperHeroServiceLayer {

    public Hero addHero(String heroId, Hero hero);

    public Hero addHero(Hero hero, Contact contact, Address address);

    public Hero deleteHero(String heroId);

    public Hero updateHero(String heroId, Hero hero, Contact contact, Address address);

    public Hero getHero(String heroId);

    public List<Hero> getAllHeroes();

    public List<HeroPower> getAllHeroesAndPowers();

    public Contact addContact(String contactId, Contact contact, Address address);

    public Contact deleteContact(String contactId);

    public Contact deleteContactWithAddress(String contactId);

    public Contact updateContact(String contactId, Contact contact, Address address);

    public Contact getContact(String contactId);

    public List<Contact> getAllContacts();

    public Address addAddress(String addressId, Address address);

    public Address deleteAddress(String addressId);

    public Address updateAddress(String addressId, Address address);

    public Address getAddress(String addressId);

    public Address findAddress(Address address);

    public List<Address> getAllAddresses();

    public Organization addOrganization(String organizationId, Organization organization);

    public Organization deleteOrganization(String organizationId);

    public Organization updateOrganization(String organizationId, Organization organization);

    public Organization getOrganization(String organizationId);

    public List<Organization> getAllOrganizations();

    public SuperPower getSuperPower(String superPowerId);

    public SuperPower addSuperPower(String superPowerId, SuperPower superPower);

    public SuperPower deleteSuperPower(String superPowerId);

    public SuperPower updateSuperPower(String superPowerId, SuperPower superPower);

    public List<SuperPower> getAllSuperPowers();

    public List<SuperPower> getSuperPowersByHero(String heroId);
}
