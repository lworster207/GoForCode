/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero;

import com.sg.superhero.model.Address;
import com.sg.superhero.model.Location;
import com.sg.superhero.service.SuperHeroServiceLayer;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public String displayLocations(Model model) {
        //model.put("message", "Hello from the controller");
        List<Location> locationList = service.getAllLocations();
        model.addAttribute("locationList", locationList);
        return "locations";
    }

    @RequestMapping(value = "/createNewLocation", method = RequestMethod.GET)
    public String createNewLocation(Model model) {
        //model.put("message", "Hello from the controller");
        Location location = new Location();
        model.addAttribute("location", location);
        return "newlocation";
    }

    @RequestMapping(value = "/createLocation", method = RequestMethod.POST)
    public String createLocation(@Valid @ModelAttribute("location") Location location, BindingResult result,
            HttpServletRequest request, Model model) {
        //model.put("message", "Hello from the controller");

        if (result.hasErrors()) {
            model.addAttribute("location", location);
            return "createNewLocation";
        }

        Address newAddress;

        if (request.getParameter("add-address").equals("")
                || request.getParameter("add-city").equals("")
                || request.getParameter("add-state").equals("")
                || request.getParameter("add-postcode").equals("")) {
            newAddress = null;
        } else {
            newAddress = new Address(
                    "noId",
                    request.getParameter("add-address"),
                    request.getParameter("add-city"),
                    request.getParameter("add-state"),
                    request.getParameter("add-postcode")
            );

        }

        service.addLocation("0", location, newAddress);
        return "redirect:displayLocations";
    }
}
