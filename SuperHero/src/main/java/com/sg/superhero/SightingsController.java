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
public class SightingsController {

    SuperHeroServiceLayer service;

    public SightingsController(SuperHeroServiceLayer service) {
        this.service = service;
    }

    @RequestMapping(value = "/displaySightings", method = RequestMethod.GET)
    public String displaySightings(Map<String, Object> model) {
        //model.put("message", "Hello from the controller");
        return "sightings";
    }

    @RequestMapping(value = "/createNewSighting", method = RequestMethod.GET)
    public String createNewSighting(Map<String, Object> model) {
        //model.put("message", "Hello from the controller");
        return "newsighting";
    }

    @RequestMapping(value = "/createSighting", method = RequestMethod.POST)
    public String createSighting(Map<String, Object> model) {
        //model.put("message", "Hello from the controller");
        return "sightings";
    }
}
