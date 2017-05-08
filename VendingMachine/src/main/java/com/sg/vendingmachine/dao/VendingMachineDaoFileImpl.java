/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author apprentice
 */
public class VendingMachineDaoFileImpl implements VendingMachineDao {

    public Map<String, Item> items = new HashMap<>();
    public String itemsFile;
    public static final String DELIMITER = "::";

    public VendingMachineDaoFileImpl(String itemsFileName) {
        this.itemsFile = itemsFileName;
        /*try {
            loadItems();
        } catch (VendingMachinePersistenceException e) {
         */
    }

    @Override
    public String getName(Item item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getQuantity(Item item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BigDecimal getPrice(Item item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        try {
            loadItems();
        } catch (VendingMachinePersistenceException e) {

        }

        return (new ArrayList<>(items.values()));
    }

    @Override
    public Item getItem(String itemId)
            throws VendingMachinePersistenceException {
        //loadItems();
        return items.get(itemId);
    }

    @Override
    public void setQuantity(Item item, int quantity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Item> getAllAvailableItems() {

        try {
            loadItems();
        } catch (VendingMachinePersistenceException e) {

        }

        return (items.values().stream().filter(item -> item.getQuantity() > 0).collect(Collectors.toList()));
    }

    @Override
    public Item addItem(String itemId, Item item)
            throws VendingMachinePersistenceException {
        Item existingItem = items.get(itemId);
        if (existingItem == null) {
            Item newItem = items.put(itemId, item);
            //writeItems();
        }
        return items.get(itemId);
    }

    @Override
    public Item removeItem(String itemId)
            throws VendingMachinePersistenceException {
        Item removedItem = items.remove(itemId);
        //writeItems();
        return removedItem;
    }

    @Override
    public void loadItems() throws VendingMachinePersistenceException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(itemsFile)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException(
                    "-_- Could not load item data from file.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;

        // currentTokens holds each of the parts of the currentLine after it has
        // been split on our DELIMITER
        // NOTE FOR APPRENTICES: In our case we use :: as our delimiter.  If
        // currentLine looks like this:
        // 1234::Joe::Smith::Java-September2013
        // then currentTokens will be a string array that looks like this:
        //
        // ___________________________________
        // |    |   |     |                  |
        // |1234|Joe|Smith|Java-September2013|
        // |    |   |     |                  |
        // -----------------------------------
        //  [0]  [1]  [2]         [3]
        String[] currentTokens;
        // Go through ROSTER_FILE line by line, decoding each line into a
        // Student object.
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // System.out.println("current data line: " + currentLine);
            // break up the line into tokens
            currentTokens = currentLine.split(DELIMITER);
            // Create a new Student object and put it into the map of students
            // NOTE FOR APPRENTICES: We are going to use the student id
            // which is currentTokens[0] as the map key for our student object.
            // We also have to pass the student id into the Student constructor
            Item currentItem = new Item(currentTokens[0], currentTokens[1], new BigDecimal(currentTokens[2]), Integer.parseInt(currentTokens[3]));

            // Put currentItem into the map using name as the key
            items.put(currentItem.getItemId(), currentItem);
        }
        // close scanner
        scanner.close();
    }

    /**
     * Writes all students in the roster out to a ROSTER_FILE. See loadRoster
     * for file format.
     *
     * @throws VendingMachinePersistenceException if an error occurs writing to
     * the file
     */
    @Override
    public void writeItems() throws VendingMachinePersistenceException {
        // NOTE FOR APPRENTICES: We are not handling the IOException - but
        // we are translating it to an application specific exception and
        // then simple throwing it (i.e. 'reporting' it) to the code that
        // called us.  It is the responsibility of the calling code to
        // handle any errors that occur.
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(itemsFile));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException(
                    "Could not save item data.", e);
        }

        // Write out the Student objects to the roster file.
        // NOTE TO THE APPRENTICES: We could just grab the student map,
        // get the Collection of Students and iterate over them but we've
        // already created a method that gets a List of Students so
        // we'll reuse it.
        List<Item> itemList = this.getAllItems();
        for (Item currentItem : itemList) {
            // write the item object to the file
            out.println(currentItem.getItemId() + DELIMITER
                    + currentItem.getName() + DELIMITER
                    + currentItem.getPrice() + DELIMITER
                    + currentItem.getQuantity());
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }

}
