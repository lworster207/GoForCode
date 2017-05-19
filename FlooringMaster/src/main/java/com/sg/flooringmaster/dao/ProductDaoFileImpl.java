/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmaster.dao;

import static com.sg.flooringmaster.dao.TaxRateDaoFileImpl.DELIMITER;
import com.sg.flooringmaster.dto.Product;
import com.sg.flooringmaster.dto.ProductNotFoundException;
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
public class ProductDaoFileImpl implements ProductDao {

    Map<String, Product> products = new HashMap<>();

    public ProductDaoFileImpl() {
        loadProducts();
    }

    @Override
    public Product getProduct(String name) throws ProductNotFoundException {
        Product retProduct = products.get(name);
        if (retProduct == null) {
            throw new ProductNotFoundException(name + " is not a valid Product.");
        }
        return products.get(name);

        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Product> getAllProducts() {
        return products.values().stream().collect(Collectors.toList());
        //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void loadProducts() {
        String productFile = "products.txt";
        Scanner scanner = null;
        List<Product> retList = null;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(productFile)));
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
                if (currentTokens[0].equals("ProductType")) {
                    // need to skip
                } else {

                    Product product = new Product(
                            currentTokens[0], // product type
                            new BigDecimal(currentTokens[1]),
                            new BigDecimal(currentTokens[2]));
                    products.put(product.getProductType(), product);
                }

            }
            scanner.close();
        }

    }

}
