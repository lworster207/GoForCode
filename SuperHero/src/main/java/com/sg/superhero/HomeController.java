/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero;

import com.sg.superhero.model.Hero;
import com.sg.superhero.model.Location;
import com.sg.superhero.model.SightingLocationHero;
import com.sg.superhero.service.HeroServiceLayer;
import com.sg.superhero.service.LocationServiceLayer;
import com.sg.superhero.service.SightingServiceLayer;
import com.sg.superhero.service.SuperHeroServiceLayer;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author apprentice
 */
@Controller
public class HomeController {

    SuperHeroServiceLayer service;

    @Inject
    SightingServiceLayer sightingService;

    @Inject
    HeroServiceLayer heroService;

    @Inject
    LocationServiceLayer locationService;

    public HomeController(SuperHeroServiceLayer service) {
        this.service = service;
    }

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String loadHome(Model model, HttpServletRequest request) {

        List<SightingLocationHero> sightingsList = sightingService.getAllSightingsDetailed();
        List<Hero> herosList = heroService.getAllHeroes();
        List<Location> locationsList = locationService.getAllLocations();

        Integer totalSightings = 0;
        Integer totalHeros = 0;
        Integer totalLocations = 0;

        if (herosList != null) {
            totalHeros = herosList.size();
        }

        if (sightingsList != null) {
            totalSightings = sightingsList.size();
        }

        if (locationsList != null) {
            totalLocations = locationsList.size();
        }

        model.addAttribute("totalLocations", totalLocations);
        model.addAttribute("totalHeros", totalHeros);
        model.addAttribute("totalSightings", totalSightings);
        model.addAttribute("sightingsList", sightingsList);
        return "superherohome";
    }
}
