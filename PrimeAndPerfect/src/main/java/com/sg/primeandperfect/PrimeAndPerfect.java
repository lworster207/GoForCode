/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.primeandperfect;

import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class PrimeAndPerfect {
    public static void main(String[] args) {
            int userNumber = 0;
            int numIterations = 0;
            int numberOfFactors = 0;
            int sumOfFactors = 0;
            

            Scanner inputReader = new Scanner(System.in); 
            System.out.println("Please enter the number to be factored: ");
            userNumber = inputReader.nextInt();
            
            System.out.println("Number to Factor: " + userNumber + "\n");
            
            if (userNumber > 0 ) {
                numIterations = userNumber/2;
            }
            
            for (int  currentFactor = 1; currentFactor <= numIterations; currentFactor++) {
                if ( (userNumber % currentFactor) == 0 ) {
                    System.out.println( currentFactor );
                    sumOfFactors += currentFactor;
                    numberOfFactors++;
                }
            }
            
            if ( sumOfFactors == userNumber ) {
                System.out.println(userNumber + " is a perfect number." );
            }
            else {
                System.out.println(userNumber + " is NOT a perfect number." );
            }
            
            
            if ( numberOfFactors == 1 ) {
                System.out.println(userNumber + " is a prime number." );
            }
            else {
                System.out.println(userNumber + " is NOT a prime number." );
            }
            System.out.println(userNumber + " has " + numberOfFactors + " factors." );
            
    }
    
}
