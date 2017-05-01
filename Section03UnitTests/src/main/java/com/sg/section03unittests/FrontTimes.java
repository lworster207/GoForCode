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
public class FrontTimes {

    // Given a String and a non-negative int n, we'll say that the
    // front of the String is the first 3 chars, or whatever is there
    // if the String is less than length 3. Return n copies of the front;
    //
    // frontTimes("Chocolate", 2) -> "ChoCho"
    // frontTimes("Chocolate", 3) -> "ChoChoCho"
    // frontTimes("Abc", 3) -> "AbcAbcAbc"
    public String frontTimes(String str, int n) {

        String frontString;

        if (str == null || n <= 0) {
            return null;
        }

        if (str.length() > 3) {
            frontString = str.substring(0, 3);
        } else {
            frontString = str;
        }

        String returnString = frontString;

        for (int loopCtr = 1; loopCtr < n; loopCtr++) {
            returnString = returnString + frontString;
        }

        return returnString;
    }

}
