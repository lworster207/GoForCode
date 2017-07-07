/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero;

import com.sg.superhero.model.Address;
import com.sg.superhero.model.Location;
import com.sg.superhero.service.LocationServiceLayer;
import com.sg.superhero.service.SuperHeroServiceLayer;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
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

    @Inject
    private LocationServiceLayer locationService;

    public LocationController(SuperHeroServiceLayer service) {
        this.service = service;
    }

    @RequestMapping(value = "/deleteLocation", method = RequestMethod.GET)
    public String deleteLocation(HttpServletRequest request, Model model) {
        try {
            locationService.deleteLocation(Integer.parseInt(request.getParameter("locationId")));
            return "redirect:displayLocations";
        } catch (DataIntegrityViolationException e) {
            List<Location> locationList = locationService.getAllLocations();
            model.addAttribute("locationList", locationList);
            model.addAttribute("error", "Location is referenced in one or more sightings and cannot be deleted. Please contact your site administrator for more details.");
            return "locations";
        }
    }

    @RequestMapping(value = "/editLocation", method = RequestMethod.GET)
    public String editLocation(HttpServletRequest request, Model model) {
        Location location = locationService.getLocation(Integer.parseInt(request.getParameter("locationId")));

        model.addAttribute("location", location);
        Address address;
        if (location.getAddressId() > 0) {
            address = service.getAddress(location.getAddressId());
            model.addAttribute("address", address);
        }
        return "editlocation";
    }

    @RequestMapping(value = "/displayLocations", method = RequestMethod.GET)
    public String displayLocations(Model model) {
        //model.put("message", "Hello from the controller");
        List<Location> locationList = locationService.getAllLocations();
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
                    0,
                    request.getParameter("add-address"),
                    request.getParameter("add-city"),
                    request.getParameter("add-state"),
                    request.getParameter("add-postcode")
            );

        }

        locationService.addLocation(0, location, newAddress);
        return "redirect:displayLocations";
    }

    @RequestMapping(value = "/updateLocation", method = RequestMethod.POST)
    public String updateLocation(@Valid @ModelAttribute("location") Location location, BindingResult result,
            HttpServletRequest request, Model model) {
        //model.put("message", "Hello from the controller");

        if (result.hasErrors()) {
            model.addAttribute("location", location);
            return "editLocation";
        }

        Address newAddress;
        String addressId;

        if (location.getAddressId() > 0) {

            if (request.getParameter("add-address").equals("")
                    || request.getParameter("add-city").equals("")
                    || request.getParameter("add-state").equals("")
                    || request.getParameter("add-postcode").equals("")) {
                newAddress = null;
            } else {
                newAddress = new Address(
                        0,
                        request.getParameter("add-address"),
                        request.getParameter("add-city"),
                        request.getParameter("add-state"),
                        request.getParameter("add-postcode")
                );

            }
        } else {
            newAddress = new Address(
                    location.getAddressId(),
                    request.getParameter("add-address"),
                    request.getParameter("add-city"),
                    request.getParameter("add-state"),
                    request.getParameter("add-postcode")
            );
        }

        locationService.updateLocation(location.getLocationId(), location, newAddress);
        return "redirect:displayLocations";
    }

}
