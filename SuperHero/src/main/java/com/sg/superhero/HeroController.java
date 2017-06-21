package com.sg.superhero;

import com.sg.superhero.model.Address;
import com.sg.superhero.model.Contact;
import com.sg.superhero.model.Hero;
import com.sg.superhero.model.SuperPower;
import com.sg.superhero.service.SuperHeroServiceLayer;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HeroController {

    private SuperHeroServiceLayer service;

    @Inject
    public HeroController(SuperHeroServiceLayer service) {
        this.service = service;

    }

    @RequestMapping(value = "/displayHerosPage", method = RequestMethod.GET)
    public String displayHeros(Model model) {
        List<Hero> heroList = service.getAllHeroes();
        model.addAttribute("heroList", heroList);
        return "heros";
    }

    @RequestMapping(value = "/createNewHero", method = RequestMethod.GET)
    public String createNewHero(Model model) {
        List<SuperPower> powersList = service.getAllSuperPowers();

        model.addAttribute("powersList", powersList);
        return "newhero";
    }

    @RequestMapping(value = "/createSuperHero", method = RequestMethod.POST)
    public String createSuperHero(HttpServletRequest request, Model model) {
        Address newAddress;
        Contact newContact;

        if (request.getParameter("add-address") == null || request.getParameter("add-city") == null || request.getParameter("add-state") == null || request.getParameter("add-postcode") == null) {
            newAddress = null;
        } else {
            newAddress = new Address(
                    "noId",
                    request.getParameter("add-address"),
                    request.getParameter("add-city"),
                    request.getParameter("add-state"),
                    request.getParameter("add-postcode")
            );

            if (request.getParameter("add-first-name") == null || request.getParameter("add-phone") == null) {
                newContact = null;
            } else {
                newContact = new Contact();
                newContact.setFirstName(request.getParameter("add-first-name"));
                newContact.setLastName(request.getParameter("add-last-name"));
                newContact.setAddress(newAddress);
                newContact.setPhone(request.getParameter("add-phone"));
                newContact.setEmail(request.getParameter("add-email"));
            }

            SuperPower newSuperPower = new SuperPower();
            Hero newHero = new Hero();
            newHero.setHeroName(request.getParameter("add-hero-name"));
            newHero.setDescription(request.getParameter("add-description"));
            newHero.setSuperpower(service.getSuperPower(request.getParameter("add-super-power")));
            newHero.setContact(newContact);

            service.addHero("1", newHero);
            //model.put("message", "Hello from the controller");
        }
        return "redirect:displayHerosPage";
    }
}
