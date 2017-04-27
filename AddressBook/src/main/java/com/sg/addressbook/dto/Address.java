/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.addressbook.dto;

/**
 *
 * @author apprentice
 */
public class Address {

    String firstName;
    String lastName;
    String street;
    String city;
    String state;
    String postCode;

    public Address(String firstName, String lastName, String street, String city, String state, String postCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.state = state;
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStreet() {
        return street;
    }

    public String getPostCode() {
        return postCode;
    }

}
