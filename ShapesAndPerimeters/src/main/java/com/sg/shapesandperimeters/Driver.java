/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.shapesandperimeters;

/**
 *
 * @author apprentice
 */
public class Driver {

    public static void main(String[] args) {
        Shape square1 = new Square(5);
        System.out.println("Square1 area: " + square1.area() + " square1 perimeter: " + square1.perimeter());

        Shape rect1 = new Rectangle(6, 7);
        System.out.println("Rectangle1 area: " + rect1.area() + " Rectangle1 perimeter: " + rect1.perimeter());

    }
}
