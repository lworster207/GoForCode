/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero;

import com.sg.superhero.service.SuperHeroServiceLayer;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author apprentice
 */
@Controller
public class LocationController {

    private SuperHeroServiceLayer service;

    public LocationController(SuperHeroServiceLayer service) {
        this.service = service;
    }

    @RequestMapping(value = "/displayLocations", method = RequestMethod.GET)
    public String displayLocations(Map<String, Object> model) {
        //model.put("message", "Hello from the controller");
        return "locations";
    }

    @RequestMapping(value = "/createNewLocation", method = RequestMethod.GET)
    public String createNewLocation(Map<String, Object> model) {
        //model.put("message", "Hello from the controller");
        return "newlocation";
    }

    @RequestMapping(value = "/createLocation", method = RequestMethod.POST)
    public String createLocation(Map<String, Object> model) {
        //model.put("message", "Hello from the controller");
        return "locations";
    }
}
