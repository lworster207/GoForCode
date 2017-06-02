/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.contactlistspringmvc;

import com.sg.contactlistspringmvc.dao.ContactListDao;
import com.sg.contactlistspringmvc.model.Contact;
import javax.inject.Inject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author apprentice
 */
@CrossOrigin
@Controller
public class RESTController {

    private ContactListDao dao;

    @Inject
    public RESTController(ContactListDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/contact", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Contact createContact(@RequestBody Contact contact) {
        return dao.addContact(contact);
    }

    @RequestMapping(value = "/contact/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContact(@PathVariable("id") long id) {
        dao.removeContact(id);
    }

    @RequestMapping(value = "/contact/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateContact(@PathVariable("id") long id, @RequestBody Contact contact) {
        // favor the path variable over the id in the object if they differ
        contact.setContactId(id);
        dao.updateContact(contact);
    }

    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
    @ResponseBody
    public List<Contact> getAllContacts() {
        return dao.getAllContacts();
    }

}