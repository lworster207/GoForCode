/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmaster.ui;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author apprentice
 */
public interface UserIO {

    void print(String message);

    void println(String message);

    double readDouble(String prompt);

    double readDouble(String prompt, double min, double max);

    float readFloat(String prompt);

    float readFloat(String prompt, float min, float max);

    int readInt(String prompt) throws UserIONoValueException;

    int readInt(String prompt, int min, int max) throws UserIONoValueException;

    long readLong(String prompt);

    long readLong(String prompt, long min, long max);

    String readString(String prompt);

    String readString();

    BigDecimal readBigDecimal(String prompt);

    LocalDate readLocalDate(String prompt, int minMonth, int maxMonth, int minDay, int maxDay, int minYear, int maxYear) throws UserIONoValueException;

    String getNextLine(String prompt) throws UserIONoValueException;

}
