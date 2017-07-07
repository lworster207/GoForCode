package com.sg.superhero;

import com.sg.superhero.model.Address;
import com.sg.superhero.model.Contact;
import com.sg.superhero.model.Hero;
import com.sg.superhero.model.Organization;
import com.sg.superhero.model.Sighting;
import com.sg.superhero.model.SuperPower;
import com.sg.superhero.service.HeroServiceLayer;
import com.sg.superhero.service.OrganizationServiceLayer;
import com.sg.superhero.service.SightingServiceLayer;
import com.sg.superhero.service.SuperHeroServiceLayer;
import com.sg.superhero.service.SuperPowerServiceLayer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @Inject
    private SuperHeroServiceLayer service;

    @Inject
    private HeroServiceLayer heroService;

    @Inject
    private OrganizationServiceLayer organizationService;

    @Inject
    private SuperPowerServiceLayer superPowerService;

    @Inject
    private SightingServiceLayer sightingService;

    public HeroController(SuperHeroServiceLayer service) {
        this.service = service;

    }

    @RequestMapping(value = "/displayHerosByOrganization", method = RequestMethod.POST)
    public String displayHerosByOrganization(HttpServletRequest request, Model model) {

        List<Hero> heroList = organizationService.getHerosByOrganization(Integer.parseInt(request.getParameter("organizationId")));
        // List<HeroPower> heroList = service.getAllHeroesAndPowers();
        model.addAttribute("heroList", heroList);

        List<Organization> organizationList = organizationService.getAllOrganizations();
        if (organizationList != null) {
            model.addAttribute("organizationList", organizationList);

        }
        return "heros";
    }

    @RequestMapping(value = "/displayHerosPage", method = RequestMethod.GET)
    public String displayHeros(Model model) {
        List<Hero> heroList = heroService.getAllHeroes();
        // List<HeroPower> heroList = service.getAllHeroesAndPowers();
        model.addAttribute("heroList", heroList);

        List<Organization> organizationList = organizationService.getAllOrganizations();
        if (organizationList != null) {
            model.addAttribute("organizationList", organizationList);

        }
        return "heros";
    }

    @RequestMapping(value = "/createNewHero", method = RequestMethod.GET)
    public String createNewHero(Model model) {
        Hero hero = new Hero();
        List<SuperPower> powersList = superPowerService.getAllSuperPowers();
        List<Organization> organizationsList = organizationService.getAllOrganizations();

        model.addAttribute("organizationsList", organizationsList);
        model.addAttribute("powersList", powersList);
        model.addAttribute("hero", hero);
        return "newhero";
    }

    @RequestMapping(value = "/deleteHero", method = RequestMethod.GET)
    public String deleteHero(HttpServletRequest request, Model model) {
        Integer heroId = Integer.parseInt(request.getParameter("heroId"));
        Integer contactId = Integer.parseInt(request.getParameter("contactId"));

        heroService.deleteHero(heroId, contactId);
        return "redirect:displayHerosPage";
    }

    @RequestMapping(value = "/viewHero", method = RequestMethod.GET)
    public String viewHero(HttpServletRequest request, Model model) {

        Contact contact;
        Address address;

        Integer heroId = Integer.parseInt(request.getParameter("heroId"));
        Hero hero = heroService.getHero(heroId);

        Map<String, SuperPower> powersList = new HashMap<>();
        Map<String, Organization> organizationsList = new HashMap<>();
        Map<String, Sighting> sightingsList = new HashMap<>();

        List<SuperPower> heroSuperPowers = superPowerService.getSuperPowersByHero(heroId);

        for (SuperPower superPower : heroSuperPowers) {
            powersList.put(superPower.getSuperPowerId().toString(), superPower);
        }
        // set the current super powers to selected

        List<Organization> heroOrganizationList = organizationService.getOrganizationsByHero(heroId);

        for (Organization organization : heroOrganizationList) {
            organizationsList.put(organization.getOrganizationId().toString(), organization);
        }

        List<Sighting> heroSightingsList = sightingService.getSightingsByHero(heroId);
        for (Sighting sighting : heroSightingsList) {
            sightingsList.put(sighting.getSightingId().toString(), sighting);
        }

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
        model.addAttribute("powersList", powersList.values());
        model.addAttribute("organizationsList", organizationsList.values());
        model.addAttribute("sightingsList", sightingsList.values());
        return "viewhero";
    }

    @RequestMapping(value = "/editHero", method = RequestMethod.GET)
    public String editHero(HttpServletRequest request, Model model) {

        Contact contact;
        Address address;

        Integer heroId = Integer.parseInt(request.getParameter("heroId"));
        Hero hero = heroService.getHero(heroId);

        Map<String, SuperPower> powersList = new HashMap<>();
        Map<String, Organization> organizationsList = new HashMap<>();

        List<SuperPower> allSuperpowers = superPowerService.getAllSuperPowers();
        List<SuperPower> heroSuperPowers = superPowerService.getSuperPowersByHero(heroId);

        for (SuperPower superPower : allSuperpowers) {
            powersList.put(superPower.getSuperPowerId().toString(), superPower);
        }
        // set the current super powers to selected
        for (SuperPower superPower : heroSuperPowers) {
            powersList.get(superPower.getSuperPowerId().toString()).setSelected("selected='selected'");
        }

        List<Organization> allOrganizationsList = organizationService.getAllOrganizations();
        List<Organization> heroOrganizationList = organizationService.getOrganizationsByHero(heroId);

        for (Organization organization : allOrganizationsList) {
            organizationsList.put(organization.getOrganizationId().toString(), organization);
        }

        for (Organization organization : heroOrganizationList) {
            organizationsList.get(organization.getOrganizationId().toString()).setSelected("selected='selected'");
        }

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
        model.addAttribute("powersList", powersList.values());
        model.addAttribute("organizationsList", organizationsList.values());
        return "edithero";
    }

    // public String createSuperHero(HttpServletRequest request, Model model) {
    @RequestMapping(value = "/createSuperHero", method = RequestMethod.POST)
    public String createSuperHero(@Valid @ModelAttribute("hero") Hero hero, BindingResult result,
            HttpServletRequest request, Model model) {

        if (result.hasErrors()) {
            List<SuperPower> powersList = superPowerService.getAllSuperPowers();
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
                    0,
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

        //           newHero.setSuperpower(service.getSuperPower(request.getParameter("add-super-power")));
        //newHero.setContact(newContact);
        heroService.addHero(hero, newContact, newAddress);

        String[] superPowersSelected = request.getParameterValues("add-super-power");
        List<Integer> superpowerIdList = new ArrayList();

        for (String superpowerId : superPowersSelected) {
            superpowerIdList.add(Integer.parseInt(superpowerId));
        }
        superPowerService.updateSuperpowersForHeroBySuperpowerIds(hero.getHeroId(), superpowerIdList);

        return "redirect:displayHerosPage";

    }

    @RequestMapping(value = "/updateSuperHero", method = RequestMethod.POST)
    public String updateSuperHero(@Valid @ModelAttribute("hero") Hero hero, BindingResult result,
            HttpServletRequest request, Model model) {
        Address newAddress = null;
        Contact newContact = null;
        Integer addressId = null;

        if (result.hasErrors()) {
            List<SuperPower> powersList = superPowerService.getAllSuperPowers();
            model.addAttribute("powersList", powersList);
            return "edithero?heroId=" + hero.getHeroId();
        }

        if (hero.getContactId().toString() != "" && hero.getContactId().toString() != null) {

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

        String[] superPowersSelected = request.getParameterValues("add-super-power");
        List<Integer> superpowerIdList = new ArrayList();

        for (String superpowerId : superPowersSelected) {
            superpowerIdList.add(Integer.parseInt(superpowerId));
        }
        superPowerService.updateSuperpowersForHeroBySuperpowerIds(hero.getHeroId(), superpowerIdList);

        String[] organizationsSelected = request.getParameterValues("add-organizations");
        List<Integer> organizationIdList = new ArrayList();

        for (String organizationId : organizationsSelected) {
            organizationIdList.add(Integer.parseInt(organizationId));
        }
        organizationService.updateOrganizationsForHeroByOrganizationIds(hero.getHeroId(), organizationIdList);
        heroService.updateHero(hero.getHeroId(), hero, newContact, newAddress);
        //model.put("message", "Hello from the controller");

        return "redirect:displayHerosPage";
    }

}
