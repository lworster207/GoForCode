/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.section04functionalunittests;

/**
 *
 * @author apprentice
 */
// Given a String, return true if the first instance
// of "x" in the String is immediately followed by
// another "x".
//
// doubleX("axxbb") -> true
// doubleX("axaxxax") -> false
// doubleX("xxxxx") -> true
public class DoubleX {

    public boolean doubleX(String str) {
        String[] chars;
        int indexOfX;
        Boolean retVal;

        // find the first instance of "x"
        indexOfX = str.indexOf("x");

        if (indexOfX < 0) {
            // no x found
            retVal = false;
        } else {
            if (indexOfX == str.length() - 1) {
                retVal = false;
            } else {
                retVal = (str.indexOf("x", indexOfX + 1) == indexOfX + 1);
            }
        }
        return retVal;
    }
}
