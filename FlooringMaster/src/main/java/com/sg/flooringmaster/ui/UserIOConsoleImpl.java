/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmaster.ui;

import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class UserIOConsoleImpl implements UserIO {

    Scanner userInput = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.print(message);
    }

    @Override
    public void println(String message) {
        System.out.println(message);
    }

    @Override
    public double readDouble(String prompt) {
        System.out.println(prompt);
        return (Double.parseDouble(userInput.nextLine()));
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        double userValue;

        do {
            System.out.println(prompt);
            userValue = Double.parseDouble(userInput.nextLine());
        } while (userValue < min || userValue > max);
        return (userValue);
    }

    @Override
    public float readFloat(String prompt) {
        System.out.println(prompt);
        return (Float.parseFloat(userInput.nextLine()));
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        float userValue;

        do {
            System.out.println(prompt);
            userValue = Float.parseFloat(userInput.nextLine());
        } while (userValue < min || userValue > max);
        return (userValue);
    }

    @Override
    public int readInt(String prompt) throws UserIONoValueException {
        //System.out.println(prompt);
        return (Integer.parseInt(getNextLine(prompt)));

    }

    @Override
    public int readInt(String prompt, int min, int max) throws UserIONoValueException {
        int userValue;

        do {
            //System.out.println(prompt);
            userValue = Integer.parseInt(getNextLine(prompt));
        } while (userValue < min || userValue > max);
        return (userValue);
    }

    @Override
    public long readLong(String prompt) {
        System.out.println(prompt);
        return (Long.parseLong(userInput.nextLine()));
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        long userValue;

        do {
            System.out.println(prompt);
            userValue = Long.parseLong(userInput.nextLine());
        } while (userValue < min && userValue > max);
        return (userValue);
    }

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        return (readString());
    }

    @Override
    public String readString() {
        String retString = userInput.nextLine();
        return retString;
    }

    @Override
    public LocalDate readLocalDate(String prompt, int minMonth, int maxMonth, int minDay, int maxDay, int minYear, int maxYear) throws UserIONoValueException {
        //int curYear = LocalDate.now().getYear();
        Boolean invalidDate = true;
        LocalDate ld = null;

        while (invalidDate) {
            System.out.println(prompt);
            /*
            int month = readInt("Month: ", minMonth, maxMonth);
            int day = readInt("Day: ", minDay, maxDay);
            int year = readInt("Year: ", minYear, maxYear);
             */
            try {
                int month = readInt("Month(" + minMonth + "-" + maxMonth + "): ", minMonth, maxMonth);
                int day = readInt("Day(" + minDay + "-" + maxDay + "): ", minDay, maxDay);
                int year = readInt("Year(" + minYear + "-" + maxYear + "): ", minYear, maxYear);

                ld = LocalDate.of(year, month, day);
                invalidDate = false;
            } catch (DateTimeException e) {
                System.out.println(e.getMessage());
            }

        }

        return (ld);

    }

    @Override
    public BigDecimal readBigDecimal(String prompt) {
        Boolean invalidEntry = true;
        BigDecimal bd = null;

        while (invalidEntry) {
            //System.out.println(prompt);
            /*
            int month = readInt("Month: ", minMonth, maxMonth);
            int day = readInt("Day: ", minDay, maxDay);
            int year = readInt("Year: ", minYear, maxYear);
             */

            try {
                bd = new BigDecimal(readString(prompt));
                invalidEntry = false;
            } catch (NumberFormatException e) {
                System.out.println("Invalid data entered.");
            }
            //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        return bd;
    }

    @Override
    public String getNextLine(String prompt) throws UserIONoValueException {
        System.out.println(prompt);
        String retVal = userInput.nextLine();
        if (retVal.equals("")) {
            throw new UserIONoValueException();
        }
        return retVal;
    }
}
