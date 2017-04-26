/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.doggenetics;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class DogGenetics {

    public static void main(String[] args) {

        // declarations
        Random getBreedPercentage = new Random();
        Scanner userInput = new Scanner(System.in);

        int totalBreedPercentage = 0;
        int currentBreedPercentage = 0;

        String[] dogBreeds = {"Siberian Husky", "Pug", "Labrador Retriever", "Golden Retriever", "German Shepherd"};

        String dogsName = "";

        // get the dog's name
        System.out.print("Please enter the dog's name: ");
        dogsName = userInput.nextLine();

        // output the dog's genetics
        System.out.println("Well then, I have this highly reliable report on " + dogsName + "'s prestigious background right here.\n");
        System.out.println(dogsName + " is:\n");
        for (int loopCounter = 0; loopCounter < dogBreeds.length - 1; loopCounter++) {
            // randomly select a % between 1 and 19.
            currentBreedPercentage = getBreedPercentage.nextInt(19) + 1;

            // display the percentage for the breed
            System.out.println(currentBreedPercentage + "% " + dogBreeds[loopCounter]);

            // save the total percentage accounted for
            totalBreedPercentage += currentBreedPercentage;
        }

        // ensure we have 100%
        currentBreedPercentage = 100 - totalBreedPercentage;

        // display the last breed.
        System.out.println(currentBreedPercentage + "% " + dogBreeds[dogBreeds.length - 1]);

        System.out.println("Wow, that's QUITE the dog!");

    }
}
