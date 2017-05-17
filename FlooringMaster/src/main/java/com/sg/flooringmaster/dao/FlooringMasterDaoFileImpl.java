/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmaster.dao;

import com.sg.flooringmaster.dto.Order;
import com.sg.flooringmaster.dto.Product;
import com.sg.flooringmaster.dto.TaxRate;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class FlooringMasterDaoFileImpl implements FlooringMasterDao {

    // public HashMap<String,Order> dayOrders = new HashMap<>();
    public HashMap<String, HashMap<String, Order>> orders = new HashMap<String, HashMap<String, Order>>();

    public int lastOrderNumber;
    public static final String DELIMITER = ",";

    @Override
    public Order getOrder(LocalDate orderDate, int orderNumber) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order addOrder(LocalDate orderDate, Order newOrder) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order removeOrder(LocalDate orderDate, int orderNumber) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveAllOrders() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getNextOrderNumber() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean getOrdersByDate(String date) {
        loadOrders(date);
        return true;
    }

    private void loadOrders(String date) {
        Scanner scanner = null;
        Product product;
        TaxRate taxrate;

        Order innerOrder;
        // String ordersFile = "Orders_" + orderDate.toString() + ".txt";
        String ordersFile = "Orders_051717.txt";

        HashMap<String, Order> dayOrders = new HashMap<>();

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(ordersFile)));
        } catch (FileNotFoundException e) {
//            throw new VendingMachinePersistenceException(
//                    "-_- Could not load item data from file: " + itemsFile, e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;

        // order format:
        //OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total
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
            if (currentTokens[0].equals("OrderNumber")) {
                // need to skip
            } else {
                // create the Product Object
                product = new Product(
                        currentTokens[4], // product type
                        new BigDecimal(currentTokens[6]), // costPerSF
                        new BigDecimal(currentTokens[7])); // laborCostPerSF

                taxrate = new TaxRate(
                        currentTokens[2], // state
                        new BigDecimal(currentTokens[3]));  // taxrate

                //   public Order(int orderNumber, String customerName, TaxRate stateTaxRate, Product product, BigDecimal area, BigDecimal materialCost, BigDecimal laborCost, BigDecimal tax, BigDecimal totalCost) {
                Order currentOrder = new Order(Integer.parseInt(currentTokens[0]), currentTokens[1], taxrate, product, new BigDecimal(currentTokens[5]), new BigDecimal(currentTokens[8]), new BigDecimal(currentTokens[9]), new BigDecimal(currentTokens[10]), new BigDecimal(currentTokens[11]));

                // Put the Order into the day map
                // create the Order
                innerOrder = dayOrders.put(currentTokens[0], currentOrder);

                //   orders.put(LocalDate.now(), <currentOrder.getOrderNumber(), currentOrder>>);
            }
        }
        // close scanner
        scanner.close();
        System.out.println("ordersRead: " + dayOrders.size());
        // now store the days orders in the global orders Map.
        orders.put("051717", dayOrders);

    }

}
