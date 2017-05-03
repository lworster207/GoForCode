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
public class SameFirstLast {

    // Given an array of ints, return true if the array is length
    // 1 or more, and the first element and the last element are equal.
    //
    // sameFirstLast({1, 2, 3}) -> false
    // sameFirstLast({1, 2, 3, 1}) -> true
    // sameFirstLast({1, 2, 1}) -> true
    public boolean sameFirstLast(int[] numbers) {
        if (numbers.length > 0) {
            return numbers[0] == numbers[numbers.length - 1];
        }
        return false;
    }
}
