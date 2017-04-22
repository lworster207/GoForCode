/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.healthyhearts;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class HealthyHearts {

    public static void main(String[] args) {

        // declare variables
        int userAge;
        int maxHeartRate;

        Scanner userInput = new Scanner(System.in);
        Random getRangePercentage = new Random();
        /*
        Their maximum heart rate should be 220 - their age.
        The target heart rate zone is the 50 - 85% of the maximum.
         */

        // get the age
        System.out.print("Please enter your age: ");
        userAge = Integer.parseInt(userInput.nextLine());

        // calculate the maximum heartrate for that age
        maxHeartRate = 220 - userAge;

        // display the maximum heart rate
        System.out.println("Your maximum heartrate should be " + maxHeartRate + ".");

        // display the calculated heart rate target zone
        System.out.println("Your target  HR Zone is " + (int) Math.round(maxHeartRate * .5) + " - " + (int) Math.round(maxHeartRate * .85) + " beats per minute");

    }

}
