/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.project3scanner;

/**
 *
 * @author apprentice
 */
package com.tsguild.foundations.scanner;

import java.util.Scanner;

public class DontForgetToStoreIt {

    public static void main(String[] args) {

        int meaningOfLifeAndEverything = 42;
        double pi = 3.14159;
        String cheese, color;

        Scanner inputReader = new Scanner(System.in);

        System.out.println("Give me pi to at least 5 decimals: ");
        pi = inputReader.nextDouble();
        inputReader.nextLine();
        System.out.println("What is the meaning of life, the universe & everything? ");
        inputReader.nextLine();

        System.out.println("What is your favorite kind of cheese? ");
        cheese = inputReader.nextLine();

        System.out.println("Do you like the color red or blue more? ");
        color = inputReader.nextLine();

           System.out.println("Ooh, " + color + " " + cheese +" sounds delicious!");
           System.out.println("The circumference of life is " +( 2 * pi * meaningOfLifeAndEverything));
    }
}

