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
import com.sg.superhero.model.Address;
import com.sg.superhero.model.Contact;
import com.sg.superhero.model.Hero;
import com.sg.superhero.model.HeroPower;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author apprentice
 */
public class HeroServiceLayerImpl implements HeroServiceLayer {

    @Inject
    HeroDao heroDao;

    @Inject
    ContactDao contactDao;

    @Inject
    AddressDao addressDao;

    @Inject
    HeroSuperpowerDao heroSuperPowerDao;

    @Inject
    SuperHeroServiceLayer superHeroService;

    @Override
    public Hero addHero(Integer heroId, Hero hero) {
        return heroDao.addHero(heroId, hero);
    }

    @Override
    public Hero addHero(Hero hero, Contact contact, Address address) {
        String addressId;

        if (contact != null) {
            if (address == null) {
                addressId = null;
            } else {
                addressDao.addAddress(0, address);
            }
            contact.setAddressId(address.getAddressId());
            contactDao.addContact(0, contact);
            hero.setContactId(contact.getContactId());
        } else {
            hero.setContactId(null);
        }

        return heroDao.addHero(1, hero);

    }

    @Override
    public Hero deleteHero(Integer heroId, Integer contactId) {

        heroSuperPowerDao.deleteByHero(heroId);
        Hero removedHero = heroDao.deleteHero(heroId);

        if (contactId != null) {
            superHeroService.deleteContact(contactId, superHeroService.getContact(contactId).getAddressId());
        } else {
            superHeroService.deleteContact(contactId, null);
        }
        return removedHero;
    }

    @Override
    public Hero updateHero(Integer heroId, Hero hero, Contact contact, Address address) {
        if (contact != null) {
            if (hero.getContactId() == null) {
                // adding a new contact

                hero.setContactId(superHeroService.addContact(contact.getContactId(), contact, address).getContactId());

            } else {
                // updating a contact
                contact.setContactId(hero.getContactId());
                address.setAddressId(contactDao.getContact(contact.getContactId()).getAddressId());
                superHeroService.updateContact(contact.getContactId(), contact, address);
            }

        } else {
            if (hero.getContactId() != null) {
                // need to remove the contact.
            }

        }
        return heroDao.updateHero(hero.getHeroId(), hero);
    }

    @Override
    public Hero getHero(Integer heroId) {
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

}
