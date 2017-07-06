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
import java.util.List;
import javax.inject.Inject;
import org.springframework.dao.DataIntegrityViolationException;

/**
 *
 * @author apprentice
 */
public class SuperHeroServiceLayerDBImpl implements SuperHeroServiceLayer {

    @Inject
    ContactDao contactDao;

    @Inject
    AddressDao addressDao;

    public SuperHeroServiceLayerDBImpl(HeroDao heroDao, ContactDao contactDao, AddressDao addressDao, OrganizationDao organizationDao, SuperPowerDao superpowerDao, OrgMemberDao orgMemberDao, HeroSuperpowerDao heroSuperpowerDao, LocationDao locationDao, SightingsDao sightingDao) {

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

}
