/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author apprentice
 */
@Controller
public class OrganizationController {

    public OrganizationController() {
    }

    @RequestMapping(value = "/displayOrganizations", method = RequestMethod.GET)
    public String displayOrganizations(Map<String, Object> model) {
        //model.put("message", "Hello from the controller");
        return "organizations";
    }

    @RequestMapping(value = "/createNewOrganization", method = RequestMethod.GET)
    public String createNewOrganization(Map<String, Object> model) {
        //model.put("message", "Hello from the controller");
        return "neworganization";
    }

    @RequestMapping(value = "/createOrganization", method = RequestMethod.POST)
    public String createOrganization(Map<String, Object> model) {
        //model.put("message", "Hello from the controller");
        return "organizations";
    }
}
