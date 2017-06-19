package com.sg.superhero;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HeroController {

    public HeroController() {
    }

    @RequestMapping(value = "/sayhi", method = RequestMethod.GET)
    public String sayHi(Map<String, Object> model) {
        model.put("message", "Hello from the controller");
        return "hello";
    }

    @RequestMapping(value = "/displayHerosPage", method = RequestMethod.GET)
    public String displayHeros(Map<String, Object> model) {
        //model.put("message", "Hello from the controller");
        return "heros";
    }

    @RequestMapping(value = "/displayOrganizations", method = RequestMethod.GET)
    public String displayOrganizations(Map<String, Object> model) {
        //model.put("message", "Hello from the controller");
        return "organizations";
    }

    @RequestMapping(value = "/displaySightings", method = RequestMethod.GET)
    public String displaySightings(Map<String, Object> model) {
        //model.put("message", "Hello from the controller");
        return "sightings";
    }

    @RequestMapping(value = "/displayLocations", method = RequestMethod.GET)
    public String displayLocations(Map<String, Object> model) {
        //model.put("message", "Hello from the controller");
        return "locations";
    }
}
