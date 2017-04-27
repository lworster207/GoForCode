/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.addressbook.dao;

import com.sg.addressbook.dto.Address;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class AddressBookDaoImpl implements AddressBookDao {

    private Map<String, Address> addresses = new HashMap<>();
    public static final String ADDRESS_FILE = "addresses.txt";
    public static final String DELIMITER = "::";

    @Override
    public Address addAddress(Address address) throws AddressBookDaoException {
        addresses.put(address.getLastName(), address);
        writeAddresses();
        return (address);
    }

    @Override
    public Address deleteAddress(Address address) throws AddressBookDaoException {
        Address deletedAddress = addresses.remove(address.getLastName());
        writeAddresses();
        return deletedAddress;
    }

    @Override
    public Address getAddressByLastName(String lastName) {
        return addresses.get(lastName);
    }

    @Override
    public int getAddressCount() {
        return addresses.size();
    }

    @Override
    public List<Address> getAddresses() {
        return (new ArrayList<>(addresses.values()));
        // return ( ( List<address> ) addresses) );
    }

    public void initAddresses() {

        try {
            loadAddresses();
        } catch (AddressBookDaoException e) {
            // file may not exist..
        }
    }

    /*        } catch (IOException e) {
            throw new ClassRosterDaoException(
                    "Could not save student data.", e);
        }
     */
    private void loadAddresses() throws AddressBookDaoException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(ADDRESS_FILE)));
        } catch (FileNotFoundException e) {
            throw new AddressBookDaoException(
                    "-_- Could not load address data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentTokens holds each of the parts of the currentLine after it has
        // been split on our DELIMITER
        // NOTE FOR APPRENTICES: In our case we use :: as our delimiter.  If
        // currentLine looks like this:
        // 1234::Joe::Smith::Java-September2013
        // then currentTokens will be a string array that looks like this:
        //
        // ___________________________________
        // |    |   |     |                  |
        // |1234|Joe|Smith|Java-September2013|
        // |    |   |     |                  |
        // -----------------------------------
        //  [0]  [1]  [2]         [3]
        String[] currentTokens;
        // Go through ROSTER_FILE line by line, decoding each line into a
        // Student object.
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // break up the line into tokens
            currentTokens = currentLine.split(DELIMITER);
            // Create a new Student object and put it into the map of students
            // NOTE FOR APPRENTICES: We are going to use the student id
            // which is currentTokens[0] as the map key for our student object.
            // We also have to pass the student id into the Student constructor
            Address currentAddress = new Address(currentTokens[0], currentTokens[1], currentTokens[2], currentTokens[3], currentTokens[4], currentTokens[5]);

            // Put currentStudent into the map using studentID as the key
            addresses.put(currentAddress.getLastName(), currentAddress);
        }
        // close scanner
        scanner.close();
    }

    /**
     * Writes all students in the roster out to a ROSTER_FILE. See loadRoster
     * for file format.
     *
     * @throws AddressBookDaoException if an error occurs writing to the file
     */
    private void writeAddresses() throws AddressBookDaoException {
        // NOTE FOR APPRENTICES: We are not handling the IOException - but
        // we are translating it to an application specific exception and
        // then simple throwing it (i.e. 'reporting' it) to the code that
        // called us.  It is the responsibility of the calling code to
        // handle any errors that occur.
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(ADDRESS_FILE));
        } catch (IOException e) {
            throw new AddressBookDaoException(
                    "Could not save address data.", e);
        }

        // Write out the Student objects to the roster file.
        // NOTE TO THE APPRENTICES: We could just grab the student map,
        // get the Collection of Students and iterate over them but we've
        // already created a method that gets a List of Students so
        // we'll reuse it.
        List<Address> addressList = this.getAddresses();
        for (Address currentAddress : addressList) {
            // write the Student object to the file
            out.println(currentAddress.getFirstName() + DELIMITER
                    + currentAddress.getLastName() + DELIMITER
                    + currentAddress.getStreet() + DELIMITER
                    + currentAddress.getCity() + DELIMITER
                    + currentAddress.getState() + DELIMITER
                    + currentAddress.getPostCode());
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }
}
