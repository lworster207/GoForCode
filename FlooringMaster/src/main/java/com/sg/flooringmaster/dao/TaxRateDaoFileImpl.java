/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmaster.dao;

import static com.sg.flooringmaster.dao.TaxRateDaoFileImpl.DELIMITER;
import com.sg.flooringmaster.dto.TaxRate;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author apprentice
 */
public class TaxRateDaoFileImpl implements TaxRateDao {

    public static final String DELIMITER = ",";

    public Map<String, TaxRate> taxrates = new HashMap<>();

    public TaxRateDaoFileImpl() {
        loadTaxRates();
    }

    @Override
    public TaxRate getTaxRate(String state) throws TaxRateNotFoundException {
        TaxRate taxrate = taxrates.get(state);
        if (taxrate == null) {
            throw new TaxRateNotFoundException(state + " is an invalid state option");
        }

        return taxrate;
    }

    @Override
    public List<TaxRate> getAllTaxRates() {
        String taxFile = "taxes.txt";
        Scanner scanner = null;
        List<TaxRate> retList = null;

        if (taxrates.size() == 0) {
            // load the tax file - check for exception
            //  loadTaxRates();

        }

        return taxrates.values().stream().collect(Collectors.toList());

    }

    private void loadTaxRates() {
        String taxFile = "taxes.txt";
        Scanner scanner = null;
        List<TaxRate> retList = null;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(taxFile)));
        } catch (FileNotFoundException e) {
            //            throw new VendingMachinePersistenceException(
            //                    "-_- Could not load item data from file: " + orderFile, e);
        }

        if (scanner != null) {
            // currentLine holds the most recent line read from the file
            String currentLine = null;

            // get the next line in the file
            String[] currentTokens;
            // Go through the orders file line by line, decoding each line into a
            // Order object.
            // Process while we have more lines in the file
            while (scanner.hasNextLine()) {
                // get the next line in the file
                currentLine = scanner.nextLine();
                // System.out.println("current data line: " + currentLine);
                // break up the line into tokens
                currentTokens = currentLine.split(DELIMITER);
                if (currentTokens[0].equals("State")) {
                    // need to skip
                } else {

                    TaxRate taxrate = new TaxRate(
                            currentTokens[0], // product type
                            new BigDecimal(currentTokens[1]));
                    taxrates.put(taxrate.getState(), taxrate);
                }

            }
            scanner.close();
        }

    }
}
