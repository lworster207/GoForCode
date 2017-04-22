/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.summativesums;

/**
 *
 * @author apprentice
 */
public class SummativeSums {

    public static void main(String[] args) {

        // initialize with the indicated test data
        int[] numbersA = {1, 90, -33, -55, 67, -16, 28, -55, 15};
        int[] numbersB = {999, -60, -77, 14, 160, 301};
        int[] numbersC = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150, 160, 170, 180, 190, 200, -99};

        // print the sum for each test array
        System.out.println("#1 Array Sum = " + getArraySum(numbersA));
        System.out.println("#2 Array Sum = " + getArraySum(numbersB));
        System.out.println("#3 Array Sum = " + getArraySum(numbersC));

    }

    public static int getArraySum(int[] numbersToSum) {
        // receive an array of integers and return the sum of it's numbers

        // arraySum stores the sum of the integers
        int arraySum = 0;

        // iterate through the array
        for (int loopCounter = 0; loopCounter < numbersToSum.length; loopCounter++) {
            // add the next integer to the sum
            arraySum += numbersToSum[loopCounter];
        }

        // return the final sum
        return (arraySum);
    }

}
