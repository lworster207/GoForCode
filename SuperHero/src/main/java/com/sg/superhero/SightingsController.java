/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero;

import com.sg.superhero.model.Hero;
import com.sg.superhero.model.Location;
import com.sg.superhero.model.Sighting;
import com.sg.superhero.model.SightingLocationHero;
import com.sg.superhero.service.SuperHeroServiceLayer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class SightingsController {

    SuperHeroServiceLayer service;

    public SightingsController(SuperHeroServiceLayer service) {
        this.service = service;
    }

    @RequestMapping(value = "/displaySightingsByLocation", method = RequestMethod.POST)
    public String displaySightingsByLocation(HttpServletRequest request, Model model) {
        String locationId = request.getParameter("locationId");

        List<SightingLocationHero> sightingsList = service.getSightingsByLocationDetailed(locationId);
        model.addAttribute("sightingsList", sightingsList);

        List<Hero> herosList = service.getAllHeroes();
        model.addAttribute("herosList", herosList);

        List<Location> locationsList = service.getAllLocations();
        model.addAttribute("locationsList", locationsList);
        return "sightings";
    }

    @RequestMapping(value = "/displaySightingsByHero", method = RequestMethod.POST)
    public String displaySightingsByHero(HttpServletRequest request, Model model) {
        String heroId = request.getParameter("heroId");

        List<SightingLocationHero> sightingsList = service.getSightingsByHeroDetailed(heroId);
        model.addAttribute("sightingsList", sightingsList);

        List<Hero> herosList = service.getAllHeroes();
        model.addAttribute("herosList", herosList);

        List<Location> locationsList = service.getAllLocations();
        model.addAttribute("locationsList", locationsList);
        return "sightings";
    }

    @RequestMapping(value = "/displaySightings", method = RequestMethod.GET)
    public String displaySightings(Model model) {
        //model.put("message", "Hello from the controller");
        List<SightingLocationHero> sightingsList = service.getAllSightingsDetailed();
        model.addAttribute("sightingsList", sightingsList);

        List<Hero> herosList = service.getAllHeroes();
        model.addAttribute("herosList", herosList);

        List<Location> locationsList = service.getAllLocations();
        model.addAttribute("locationsList", locationsList);

        return "sightings";
    }

    @RequestMapping(value = "/createNewSighting", method = RequestMethod.GET)
    public String createNewSighting(Model model) {
        //model.put("message", "Hello from the controller");
        Sighting sighting = new Sighting();

        List<Hero> allHerosList = service.getAllHeroes();

        // to be removed once the dao is ready.
        Map<String, String> herosList = new HashMap<>();
        for (Hero hero : allHerosList) {
            herosList.put(hero.getHeroId(), hero.getHeroName());
        }
        model.addAttribute("herosList", allHerosList);

        List<Location> allLocationsList = service.getAllLocations();
        Map<String, String> locationsList = new HashMap<>();
        for (Location location : allLocationsList) {
            locationsList.put(location.getLocationId(), location.getLocationName());

        }
        model.addAttribute("locationsList", allLocationsList);
        model.addAttribute("sighting", sighting);
        return "newsighting";
    }

    @RequestMapping(value = "/createSighting", method = RequestMethod.POST)
    public String createSighting(@Valid @ModelAttribute("sighting") Sighting sighting, BindingResult result,
            HttpServletRequest request, Model model) {
        service.addSighting("0", sighting);
        //model.put("message", "Hello from the controller");
        return "redirect:displaySightings";
    }

}
