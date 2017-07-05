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
import com.sg.superhero.model.Location;
import com.sg.superhero.model.Organization;
import com.sg.superhero.model.Sighting;
import com.sg.superhero.model.SightingLocationHero;
import com.sg.superhero.model.SuperPower;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface SuperHeroServiceLayer {

    public List<SightingLocationHero> getAllSightingsDetailed();

    public List<Sighting> getSightingByLocation(String locationId);

    public List<Sighting> getSightingsByHero(String heroId);

    public List<Sighting> getSightingsByDate(String date);

    public Sighting addSighting(String sightingId, Sighting sighting);

    public List<Sighting> getAllSightings();

    public Location addLocation(String locationId, Location location, Address address);

    public Location updateLocation(String locationId, Location location, Address address);

    public Location getLocation(String locationId);

    public List<Location> getAllLocations();

    public Hero addHero(String heroId, Hero hero);

    public Hero addHero(Hero hero, Contact contact, Address address);

    public Hero deleteHero(String heroId, String contactId);

    public Hero updateHero(String heroId, Hero hero, Contact contact, Address address);

    public Hero getHero(String heroId);

    public List<Hero> getAllHeroes();

    public List<HeroPower> getAllHeroesAndPowers();

    public Contact addContact(String contactId, Contact contact, Address address);

    public Contact deleteContact(String contactId, String addressId);

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

    public Organization updateOrganization(String organizationId, Organization organization, Address address);

    public Organization getOrganization(String organizationId);

    public List<Hero> getHerosByOrganization(String organizationId);

    public List<Organization> getOrganizationsByHero(String heroId);

    public List<Organization> getAllOrganizations();

    public List<Organization> updateOrganizationsForHeroByOrganizationIds(String heroId, List<String> organizationIds);

    public SuperPower getSuperPower(String superPowerId);

    public SuperPower addSuperPower(String superPowerId, SuperPower superPower);

    public SuperPower deleteSuperPower(String superPowerId);

    public SuperPower updateSuperPower(String superPowerId, SuperPower superPower);

    public List<SuperPower> getAllSuperPowers();

    public List<SuperPower> getSuperPowersByHero(String heroId);

    public List<SuperPower> updateSuperpowersForHero(String heroId, List<SuperPower> superPowers);

    public List<SuperPower> updateSuperpowersForHeroBySuperpowerIds(String heroId, List<String> superPowerIds);

}
