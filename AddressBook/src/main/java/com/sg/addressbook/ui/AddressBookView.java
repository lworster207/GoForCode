/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.addressbook.ui;

import com.sg.addressbook.dto.Address;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class AddressBookView {

    private UserIO io;

    public int displayMenuAndGetOption() {
        io.println("Main Menu");
        io.println("1. Display All Addresses");
        io.println("2. Create New Address");
        io.println("3. Find an Address");
        io.println("4. Remove an Address");
        io.println("5. Display the Total Number of Addresses");
        io.println("6. Edit Address");
        io.println("7. Exit");

        return io.readInt("Please select from the above choices.", 1, 7);

    }

    public Address getAddressInfo() {
        displayBanner("Enter the information for new address");
        String firstName = io.readString("Please enter First Name: ");
        String lastName = io.readString("Please enter Last Name: ");
        String address = io.readString("Please enter the street address: ");
        String city = io.readString("Please enter the City: ");
        String state = io.readString("Please enter the State: ");
        String postCode = io.readString("Please enter the post code: ");

        Address newAddress = new Address(firstName, lastName, address, city, state, postCode);

        return newAddress;
    }

    public String getLastName() {

        return (io.readString("Please enter the Last Name: "));

    }

    public String Confirm(String prompt) {
        return io.readString(prompt);
    }

    public void displayAddress(Address address) {
        io.println(address.getFirstName() + " " + address.getLastName());
        io.println(address.getStreet());
        io.println(address.getCity() + " " + address.getState() + " " + address.getPostCode());
    }

    public void displayAddressSingleLine(Address address) {
        io.println(address.getFirstName() + " " + address.getLastName()
                + " | " + address.getStreet() + " | " + address.getCity()
                + " | " + address.getState() + " | " + address.getPostCode());
    }

    public void displayAddressCount(Integer addressCount) {
        displayBanner("Address Count");
        io.println("Number of Addresses: " + addressCount);
    }

    public void displayAddressList(List<Address> addresses) {

        displayBanner("All Addresses");
        for (Address address : addresses) {
            displayAddressSingleLine(address);
        }
    }

    public void displayBanner(String banner) {
        io.println("=== " + banner + " ===");
    }

    public void print(String message) {
        io.print(message);
    }

    public void println(String message) {
        io.println(message);
    }

    public void displayErrorMessage(String errorMsg) {
        io.println("=== ERROR ===");
        io.print(errorMsg);
    }

    public AddressBookView(UserIO io) {
        this.io = io;
    }

}
