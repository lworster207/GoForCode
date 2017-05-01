/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.section03unittests;

/**
 *
 * @author apprentice
 */
public class Abba {

    // Given two Strings, a and b, return the result of putting
    // them together in the order abba, e.g. "Hi" and "Bye"
    // returns "HiByeByeHi".
    //
    // abba("Hi", "Bye") -> "HiByeByeHi"
    // abba("Yo", "Alice") -> "YoAliceAliceYo"
    // abba("What", "Up") -> "WhatUpUpWhat"
    public String abba(String a, String b) {
        if (a == null || b == null) {
            return null;
        }
        return (a + b + b + a);
    }

}
