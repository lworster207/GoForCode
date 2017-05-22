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
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class FlooringMasterDaoFileImpl implements FlooringMasterDao {

    // public HashMap<String,Order> dayOrders = new HashMap<>();
    public HashMap<String, OrderDay> orders = new HashMap<String, OrderDay>();

    public int lastOrderNumber;
    public static final String DELIMITER = ",";

    @Override
    public Order getOrder(String orderDate, String orderNumber) {
        return orders.get(orderDate).getOrder(orderNumber);

        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order addOrder(String orderDate, Order newOrder) {
        //
        Order retOrder = null;

        Integer newOrderNumber = newOrder.getOrderNumber();
        //newOrder.setOrderNumber(newOrderNumber);
        if (orders.containsKey(orderDate)) {
            // date is already in the map
            OrderDay daysOrders = orders.get(orderDate);
            retOrder = daysOrders.addOrder(newOrderNumber.toString(), newOrder);

        } else {
            // see if the orders exist in a file
            loadOrders(orderDate);
            if (orders.containsKey(orderDate)) {
                // date is already in the map
                OrderDay daysOrders = orders.get(orderDate);
                retOrder = daysOrders.addOrder(newOrderNumber.toString(), newOrder);
            } else {
                // first order for this date
                OrderDay newDay = new OrderDay();
                newDay.addOrder(newOrderNumber.toString(), newOrder);
                orders.put(orderDate, newDay);
                retOrder = orders.get(orderDate).getOrder(newOrderNumber.toString());

            }
        }
        return retOrder;
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order removeOrder(String orderDate, String orderNumber) {
        return orders.get(orderDate).removeOrder(orderNumber);
    }

    @Override
    public void saveAllOrders() throws FlooringMasterDaoPersistenceException {
        writeOrders();
    }

    @Override
    public int getNextOrderNumber() {
        Scanner scanner = null;
        PrintWriter out = null;

        int orderNumber = 0;

        String numberFile = "OrderNumber.txt";

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(numberFile)));
        } catch (FileNotFoundException e) {
//            throw new VendingMachinePersistenceException(
//                    "-_- Could not load item data from file: " + orderFile, e);
        }

        if (scanner != null) {
            // currentLine holds the most recent line read from the file
            String currentLine = null;

            // get the next line in the file
            currentLine = scanner.nextLine();

            orderNumber = Integer.parseInt(currentLine);

            scanner.close();
        }

        orderNumber++;

        try {
            out = new PrintWriter(new FileWriter(numberFile));
        } catch (IOException e) {
            //  throw new VendingMachinePersistenceException("Could not save item data.", e);
        }

        if (out != null) {
            out.println(orderNumber);
            out.flush();

            // Clean up
            out.close();
        }
        return orderNumber;
    }

    @Override
    public OrderDay getOrderDayByDate(String date) {
        // check the existing orders to see if there are already ordersfor that date
        if (orders.containsKey(date)) {
            return orders.get(date);
        } else {
            // see if the orders exist in a file
            loadOrders(date);
            return orders.get(date);
        }
    }

    @Override
    public List<Order> getOrdersByDate(String date) throws FlooringMasterNoOrdersForDateException {
        OrderDay orderday = orders.get(date);
        List<Order> dayList = null;

        if (orderday == null) {
            // load a date file
            loadOrders(date);
            orderday = orders.get(date);
            if (orderday == null) {
                throw new FlooringMasterNoOrdersForDateException("No orders found for date: " + date);
            }
        }

        // order Day exists - get the list of orders
        dayList = orderday.getAllOrders();
        if (dayList.isEmpty()) {
            throw new FlooringMasterNoOrdersForDateException("No orders found for date: " + date);
        }

        // return the list
        return dayList;
    }

    private void loadOrders(String date) {
        Scanner scanner = null;
        String ordersFile = "Orders_" + date + ".txt";

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(ordersFile)));
        } catch (FileNotFoundException e) {
            // no orders to read
            return;
        }

        Product product;
        TaxRate taxrate;

        Order innerOrder;

        OrderDay dayOrders = new OrderDay();
        // currentLine holds the most recent line read from the file
        String currentLine;

        // order file format:
        //OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total
        String[] currentTokens;
        // Go through the orders file line by line, decoding each line into an Order object.
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // break up the line into tokens
            currentTokens = currentLine.split(DELIMITER);
            if (currentTokens[0].equals("OrderNumber")) {
                // heading line - need to skip
            } else {
                // create the Product Object
                product = new Product(
                        currentTokens[4], // product type
                        new BigDecimal(currentTokens[6]), // costPerSF
                        new BigDecimal(currentTokens[7])); // laborCostPerSF

                // create a TaxRate object
                taxrate = new TaxRate(
                        currentTokens[2], // state
                        new BigDecimal(currentTokens[3]));  // taxrate

                // create a new Order object
                Order currentOrder = new Order(Integer.parseInt(currentTokens[0]), currentTokens[1], taxrate, product, new BigDecimal(currentTokens[5]), new BigDecimal(currentTokens[8]), new BigDecimal(currentTokens[9]), new BigDecimal(currentTokens[10]), new BigDecimal(currentTokens[11]));

                // Add the Order into the day map
                innerOrder = dayOrders.addOrder(currentTokens[0], currentOrder);
            }
        }
        // close scanner
        scanner.close();

        // now store the days orders in the global orders Map.
        orders.put(date, dayOrders);

    }

    private void writeOrders() throws FlooringMasterDaoPersistenceException {

        PrintWriter out = null;
        // for each day in orders, write out that days orders to a separate file
        // HashMap<String, HashMap<String, Order>> orderDay;
        //HashMap<String, Order> dayOrders;
        // HashMap<String, HashMap<String, Order>> orderDay = new HashMap<>();
        OrderDay orderDay;
        String orderFile;
        String orderRecord = "";

        //  HashMap<String, OrderDay> dayOrders = (HashMap<String, OrderDay>) orders.values();
        // List<OrderDay> orderDates = (List<OrderDay>) orders.keySet();
        for (String date : orders.keySet()) {
            orderFile = "Orders_" + date + ".txt";

            try {
                out = new PrintWriter(new FileWriter(orderFile));
            } catch (IOException e) {
                throw new FlooringMasterDaoPersistenceException("Could not save item data.");
            }

            // order format:
            //OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total
            List<Order> orderList = orders.get(date).getAllOrders();
            for (Order currentOrder : orderList) {
                // write the order object to the file
                orderRecord = currentOrder.getOrderNumber() + DELIMITER
                        + currentOrder.getCustomerName() + DELIMITER
                        + currentOrder.getStateTaxRate().getState() + DELIMITER
                        + currentOrder.getStateTaxRate().getTaxRate() + DELIMITER
                        + currentOrder.getProduct().getProductType() + DELIMITER
                        + currentOrder.getArea() + DELIMITER
                        + currentOrder.getProduct().getCostPerSquareFoot() + DELIMITER
                        + currentOrder.getProduct().getLaborCostPerSquareFoot() + DELIMITER
                        + currentOrder.getMaterialCost() + DELIMITER
                        + currentOrder.getLaborCost() + DELIMITER
                        + currentOrder.getTax() + DELIMITER
                        + currentOrder.getTotalCost();
                // force PrintWriter to write line to the file
                System.out.println("writing: " + orderRecord);
                out.println(orderRecord);
                out.flush();
            }
            // Clean up
            out.close();
        }
    }
}
