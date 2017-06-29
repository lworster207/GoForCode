package com.sg.superhero;

import com.sg.superhero.model.Address;
import com.sg.superhero.model.Contact;
import com.sg.superhero.model.Hero;
import com.sg.superhero.model.SuperPower;
import com.sg.superhero.service.SuperHeroServiceLayer;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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
        // List<HeroPower> heroList = service.getAllHeroesAndPowers();
        model.addAttribute("heroList", heroList);
        return "heros";
    }

    @RequestMapping(value = "/createNewHero", method = RequestMethod.GET)
    public String createNewHero(Model model) {
        Hero hero = new Hero();
        List<SuperPower> powersList = service.getAllSuperPowers();

        model.addAttribute("powersList", powersList);
        model.addAttribute("hero", hero);
        return "newhero";
    }

    @RequestMapping(value = "/deleteHero", method = RequestMethod.GET)
    public String deleteHero(HttpServletRequest request, Model model) {
        String heroId = request.getParameter("heroId");
        service.deleteHero(heroId);
        return heroId;
    }

    @RequestMapping(value = "/editHero", method = RequestMethod.GET)
    public String editHero(HttpServletRequest request, Model model) {
        String heroId = request.getParameter("heroId");

        List<SuperPower> powersList = service.getAllSuperPowers();
        //List<Organization> organizationsList = service.get

        Contact contact;
        Address address;

        Hero hero = service.getHero(heroId);
        List<SuperPower> heroPowersList = service.getSuperPowersByHero(heroId);

        contact = service.getContact(hero.getContactId());
        if (contact != null) {

            //model.addAttribute("contact", hero.getContact());
            model.addAttribute("contact", contact);
            if (contact.getAddressId() != null) {
                address = service.getAddress(contact.getAddressId());
                if (address != null) {
                    model.addAttribute("address", address);
                }
            }
        }

        model.addAttribute("hero", hero);
        model.addAttribute("powersList", powersList);
        model.addAttribute("heroPowersList", heroPowersList);
        return "edithero";
    }

    // public String createSuperHero(HttpServletRequest request, Model model) {
    @RequestMapping(value = "/createSuperHero", method = RequestMethod.POST)
    public String createSuperHero(@Valid @ModelAttribute("hero") Hero hero, BindingResult result,
            HttpServletRequest request, Model model) {

        if (result.hasErrors()) {
            List<SuperPower> powersList = service.getAllSuperPowers();
            model.addAttribute("powersList", powersList);
            return "newhero";
        }

        Address newAddress;
        Contact newContact;
        String contactId;
        String addressId;

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

        if (request.getParameter("add-first-name").equals("")
                || request.getParameter("add-phone").equals("")) {
            newContact = null;
        } else {
            newContact = new Contact();
            newContact.setFirstName(request.getParameter("add-first-name"));
            newContact.setLastName(request.getParameter("add-last-name"));
            newContact.setAddressId(newAddress.getAddressId());
            newContact.setPhone(request.getParameter("add-phone"));
            newContact.setEmail(request.getParameter("add-email"));
        }

        SuperPower newSuperPower = new SuperPower();
        Hero newHero = new Hero();
        newHero.setHeroName(request.getParameter("heroName"));
        newHero.setDescription(request.getParameter("add-description"));

        //           newHero.setSuperpower(service.getSuperPower(request.getParameter("add-super-power")));
        //newHero.setContact(newContact);
        service.addHero(newHero, newContact, newAddress);
        //model.put("message", "Hello from the controller");

        return "redirect:displayHerosPage";

    }

    @RequestMapping(value = "/updateSuperHero", method = RequestMethod.POST)
    public String updateSuperHero(@Valid @ModelAttribute("hero") Hero hero, BindingResult result,
            HttpServletRequest request, Model model) {
        Address newAddress = null;
        Contact newContact = null;
        String addressId = null;

        if (result.hasErrors()) {
            List<SuperPower> powersList = service.getAllSuperPowers();
            model.addAttribute("powersList", powersList);
            return "edithero?heroId=" + hero.getHeroId();
        }

        if (hero.getContactId() != null) {

            addressId = service.getContact(hero.getContactId()).getAddressId();
        }

        if (request.getParameter("add-first-name") != null && request.getParameter("add-phone") != null) {
            if (request.getParameter("add-address") != null
                    && request.getParameter("add-city") != null
                    && request.getParameter("add-state") != null
                    && request.getParameter("add-postcode") != null) {
                newAddress = new Address(
                        addressId,
                        request.getParameter("add-address"),
                        request.getParameter("add-city"),
                        request.getParameter("add-state"),
                        request.getParameter("add-postcode")
                );
            }

            newContact = new Contact();
            newContact.setContactId(hero.getContactId());
            newContact.setFirstName(request.getParameter("add-first-name"));
            newContact.setLastName(request.getParameter("add-last-name"));
            newContact.setAddressId(addressId);
            newContact.setPhone(request.getParameter("add-phone"));
            newContact.setEmail(request.getParameter("add-email"));
        }

        // SuperPower newSuperPower = new SuperPower();
        // Hero newHero = new Hero();
        // newHero.setHeroName(request.getParameter("add-hero-name"));
        // newHero.setDescription(request.getParameter("add-description"));
        // newHero.setSuperpower(service.getSuperPower(request.getParameter("add-super-power")));
        // newHero.setContact(newContact);
        service.updateHero(hero.getHeroId(), hero, newContact, newAddress);
        //model.put("message", "Hello from the controller");

        return "redirect:displayHerosPage";
    }

}
