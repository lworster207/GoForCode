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
public class Rectangle extends Shape {

    public double length;
    public double width;

    @Override
    public double area() {
        return (length * width);
    }

    @Override
    public double perimeter() {
        return (2 * (length + width));
    }

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }
// getters and setters

    public double getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

}
