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
public class Square extends Shape {

    public double lengthOfSide;

    @Override
    public double area() {
        return (lengthOfSide * lengthOfSide);
    }

    @Override
    public double perimeter() {
        return (lengthOfSide * 4);
    }

    // constructors
    public Square(double lengthOfSide) {
        this.lengthOfSide = lengthOfSide;
    }

    public Square(int lengthOfSide) {
        this.lengthOfSide = lengthOfSide;
    }

    public Square() {

    }

    // getters and setters
    public double getLengthOfSide() {
        return lengthOfSide;
    }

    public void setLengthOfSide(double lengthOfSide) {
        this.lengthOfSide = lengthOfSide;
    }

}
