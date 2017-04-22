/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.windowmaster;

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
public class WindowMaster {

    public static void main(String[] args) {
        // declare variables for height and width
        float height;
        float width;

        // declare other variables
        float areaOfWindow;
        float cost;
        float perimeterOfWindow;

        // request dimensions and convert to float
        height = getDimension("Please enter window height:");
        width = getDimension("Please enter window width:");

        //calculate the area of the window
        areaOfWindow = height * width;

        // calculate the perimeter of the window
        perimeterOfWindow = 2 * (height + width);

        // calculate the total cost - use a hard coded value for
        // material cost
        cost = ((3.50f * areaOfWindow) + (2.25f * perimeterOfWindow));

        System.out.println("Window height = " + height);
        System.out.println("Window width = " + width);
        System.out.println("Window area = " + areaOfWindow);
        System.out.println("Window perimeter = " + perimeterOfWindow);
        System.out.println("Total Cost =  " + cost);

    }

    public static float getDimension(String prompt) {
        String stringInput;

        // declare and initial the Scanner
        Scanner myScanner = new Scanner(System.in);

        System.out.println(prompt);
        stringInput = myScanner.nextLine();

        // convert String value and return as a float
        return (Float.parseFloat(stringInput));
    }
}
