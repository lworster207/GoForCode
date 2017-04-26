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
public class Circle extends Shape {

    public double radius;

    @Override
    public double area() {
        return (Math.pow(Math.PI * radius, 2));
    }

    @Override
    public double perimeter() {
        return (2 * Math.PI * radius);
    }
}
