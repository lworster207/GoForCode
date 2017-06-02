/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibraryspringmvc.dao;

/**
 *
 * @author apprentice
 */
public class DvdLibraryPersistenceException extends Exception {

    /**
     * Creates a new instance of <code>DvdLibraryPersistenceException</code>
     * without detail message.
     */
    public DvdLibraryPersistenceException() {
    }

    /**
     * Constructs an instance of <code>DvdLibraryPersistenceException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public DvdLibraryPersistenceException(String msg) {
        super(msg);
    }

    public DvdLibraryPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }

}
