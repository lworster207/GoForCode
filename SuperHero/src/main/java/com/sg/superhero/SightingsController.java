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
import java.util.ArrayList;
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

    @RequestMapping(value = "/updateSighting", method = RequestMethod.POST)
    public String updateSighting(@Valid @ModelAttribute("sighting") Sighting sighting, BindingResult result,
            HttpServletRequest request, Model model) {
        service.updateSighting(sighting.getSightingId(), sighting);
        return "redirect:displaySightings";
    }

    @RequestMapping(value = "/displaySightingsByLocation", method = RequestMethod.POST)
    public String displaySightingsByLocation(HttpServletRequest request, Model model) {
        List<SightingLocationHero> sightingsList = service.getAllSightingsDetailed();

        Map<String, String> dateMap = new HashMap<>();
        if (sightingsList != null) {
            for (SightingLocationHero slh : sightingsList) {
                dateMap.put(slh.getSightingDate(), slh.getSightingDate());
            }
            model.addAttribute("dateList", dateMap.keySet());
        }

        String locationId = request.getParameter("locationId");

        sightingsList = service.getSightingsByLocationDetailed(locationId);
        model.addAttribute("sightingsList", sightingsList);

        List<Hero> herosList = service.getAllHeroes();
        model.addAttribute("herosList", herosList);

        List<Location> locationsList = service.getAllLocations();
        model.addAttribute("locationsList", locationsList);
        return "sightings";
    }

    @RequestMapping(value = "/displaySightingsByDate", method = RequestMethod.POST)
    public String displaySightingsByDate(HttpServletRequest request, Model model) {
        String date = request.getParameter("date");
        List<SightingLocationHero> sightingsList = service.getAllSightingsDetailed();

        Map<String, String> dateMap = new HashMap<>();
        if (sightingsList != null) {
            for (SightingLocationHero slh : sightingsList) {
                dateMap.put(slh.getSightingDate(), slh.getSightingDate());
            }
            model.addAttribute("dateList", dateMap.keySet());
        }

        sightingsList = service.getSightingsByDateDetailed(date);
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

        List<SightingLocationHero> sightingsList = service.getAllSightingsDetailed();

        Map<String, String> dateMap = new HashMap<>();
        if (sightingsList != null) {
            for (SightingLocationHero slh : sightingsList) {
                dateMap.put(slh.getSightingDate(), slh.getSightingDate());
            }
            model.addAttribute("dateList", dateMap.keySet());
        }

        sightingsList = service.getSightingsByHeroDetailed(heroId);
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

        Map<String, String> dateMap = new HashMap<>();
        if (sightingsList != null) {
            for (SightingLocationHero slh : sightingsList) {
                dateMap.put(slh.getSightingDate(), slh.getSightingDate());
            }
            model.addAttribute("dateList", dateMap.keySet());
        }

        List<Hero> herosList = service.getAllHeroes();
        model.addAttribute("herosList", herosList);

        List<Location> locationsList = service.getAllLocations();
        model.addAttribute("locationsList", locationsList);

        return "sightings";
    }

    @RequestMapping(value = "/editSighting", method = RequestMethod.GET)
    public String editSighting(HttpServletRequest request, Model model) {
        //model.put("message", "Hello from the controller");

        Sighting sighting = service.getSighting(request.getParameter("sightingId"));

        List<Hero> allHerosList = service.getAllHeroes();
        List<HeroSelected> herosList = new ArrayList<>();

        for (Hero hero : allHerosList) {
            if (sighting.getHeroId().equals(hero.getHeroId())) {
                herosList.add(new HeroSelected(hero.getHeroId(), hero.getHeroName(), "selected='selected'"));
            } else {
                herosList.add(new HeroSelected(hero.getHeroId(), hero.getHeroName(), ""));
            }
        }

        model.addAttribute("herosList", herosList);

        List<Location> allLocationsList = service.getAllLocations();
        List<LocationSelected> locationsList = new ArrayList();
        for (Location location : allLocationsList) {
            if (sighting.getLocationId().equals(location.getLocationId())) {
                locationsList.add(new LocationSelected(location.getLocationId(), location.getLocationName(), "selected='selected'"));
            } else {
                locationsList.add(new LocationSelected(location.getLocationId(), location.getLocationName(), ""));
            }
        }

        model.addAttribute("locationsList", locationsList);
        model.addAttribute("sighting", sighting);

        return "editsighting";
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

    public class HeroSelected extends Hero {

        String selected;

        public HeroSelected(String heroId, String heroName, String selected) {
            super(heroId, heroName);
            this.selected = selected;
        }

        public String getSelected() {
            return selected;
        }

        public void setSelected(String selected) {
            this.selected = selected;
        }

    }

    public class LocationSelected extends Location {

        String selected;

        public LocationSelected(String locationId, String locationName, String selected) {
            super(locationId, locationName);
            this.selected = selected;
        }

        public String getSelected() {
            return selected;
        }

        public void setSelected(String selected) {
            this.selected = selected;
        }

    }
}
