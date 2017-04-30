/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.addressbook.controller;

import com.sg.addressbook.dao.AddressBookDao;
import com.sg.addressbook.dao.AddressBookDaoException;
import com.sg.addressbook.dto.Address;
import com.sg.addressbook.ui.AddressBookView;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class AddressBookController {

    AddressBookView view;
    AddressBookDao dao;

    public final int LIST_ALL_ADDRESSES = 1;
    public final int ADD_ADDRESS = 2;
    public final int FIND_ADDRESS = 3;
    public final int REMOVE_ADDRESS = 4;
    public final int SHOW_ADDRESS_COUNT = 5;
    public final int EDIT_ADDRESS = 6;
    public final int EXIT = 7;

    public void run() {
        boolean keepGoing = true;
        int menuSelection;

        dao.initAddresses();

        try {
            while (keepGoing) {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case ADD_ADDRESS:
                        addAddress();
                        break;
                    case REMOVE_ADDRESS:
                        removeAddress();
                        break;
                    case SHOW_ADDRESS_COUNT:
                        showAddressCount();
                        break;
                    case FIND_ADDRESS:
                        findAddress();
                        break;
                    case LIST_ALL_ADDRESSES:
                        showAddressList();
                        break;
                    case EDIT_ADDRESS:
                        editAddress();
                        break;
                    case EXIT:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

            }
            exitMessage();
        } catch (AddressBookDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection() {
        return view.displayMenuAndGetOption();
    }

    public void addAddress() throws AddressBookDaoException {
        Address newAddress = view.getAddressInfo();
        dao.addAddress(newAddress);
    }

    public void removeAddress() throws AddressBookDaoException {
        view.displayBanner("Delete Address");
        String lastName = view.getLastName();
        Address addressToRemove = dao.getAddressByLastName(lastName);
        view.displayAddressSingleLine(addressToRemove);

        String confirmDelete = view.prompt("Delete this address?");
        if (confirmDelete.toLowerCase().equals("y")) {
            dao.deleteAddress(addressToRemove);
            view.displayBanner("The address has been deleted.");
            view.prompt("Press enter to continue");

        }

    }

    public void showAddressCount() {
        view.displayBanner("Show Address Count");
        int addressCount = dao.getAddressCount();
        view.displayAddressCount(addressCount);
        view.prompt("Press enter to continue");

    }

    public Address findAddress() {
        view.displayBanner("Find Address");
        String lastName = view.getLastName();
        Address address = dao.getAddressByLastName(lastName);

        if (!(address == null)) {

            view.displayAddress(address);
        } else {
            view.prompt("No addresses found for " + lastName + "\nPress Enter to continue");
        }

        return address;

    }

    public void editAddress() throws AddressBookDaoException {
        Address address = findAddress();
        view.displayBanner("EDIT Address");
        view.displayAddress(address);
        view.println("Please enter new information for this address");
        Address newAddress = view.getAddressInfo();

        if (newAddress != null) {
            dao.deleteAddress(address);
            dao.addAddress(newAddress);
            view.prompt("The new information has been saved.\nPress Enter to continue.");
        }
    }

    public void showAddressList() {
        List<Address> addressList = dao.getAddresses();
        view.displayAddressList(addressList);
    }

    private void unknownCommand() {
        view.displayBanner("Unknown Command!");
    }

    private void exitMessage() {
        view.displayBanner("Exiting...");
    }

    public AddressBookController(AddressBookDao dao, AddressBookView view) {
        this.dao = dao;
        this.view = view;
    }

}
