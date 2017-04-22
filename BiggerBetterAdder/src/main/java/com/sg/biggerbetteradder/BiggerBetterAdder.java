/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.biggerbetteradder;


import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author apprentice
 */
public class BiggerBetterAdder {
    public static void main(String[] args) {
            int iFirst,iSecond,iThird,iSum;

            iFirst = 1;
            iSecond = 2;
            iThird = 3;
            
            Scanner inputReader = new Scanner(System.in); 
            System.out.println("Give me the first number: ");
            iFirst = inputReader.nextInt();
            System.out.println("Give me the second number: ");
            iSecond = inputReader.nextInt();
            System.out.println("Give me the third number: ");
            iThird = inputReader.nextInt();
            
            System.out.println("First: " + iFirst + " Second: " + iSecond + " Third: " + iThird + "\n");
            
            iSum = iFirst + iSecond;
            System.out.println("First plus Second = " + iSum );
            System.out.println("First plus Second = " + iSum );
    }
   
}

