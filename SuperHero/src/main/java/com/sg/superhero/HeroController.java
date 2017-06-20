package com.sg.superhero;

import com.sg.superhero.dao.HeroDao;
import com.sg.superhero.dao.SuperPowerDao;
import com.sg.superhero.model.Address;
import com.sg.superhero.model.Contact;
import com.sg.superhero.model.Hero;
import com.sg.superhero.model.SuperPower;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HeroController {

    private HeroDao dao;
    private SuperPowerDao spdao;

    @Inject
    public HeroController(HeroDao herodao, SuperPowerDao spdao) {
        this.dao = herodao;
        this.spdao = spdao;
    }

    @RequestMapping(value = "/displayHerosPage", method = RequestMethod.GET)
    public String displayHeros(Model model) {
        //model.put("message", "Hello from the controller");
        return "heros";
    }

    @RequestMapping(value = "/createNewHero", method = RequestMethod.GET)
    public String createNewHero(Model model) {
        List<SuperPower> powersList = spdao.getAllSuperPowers();

        model.addAttribute("powersList", powersList);
        return "newhero";
    }

    @RequestMapping(value = "/createSuperHero", method = RequestMethod.POST)
    public String createSuperHero(HttpServletRequest request, Model model) {

        request.getParameter("add-hero-name");
        request.getParameter("add-super-power");
        request.getParameter("add-description");

        request.getParameter("add-first-name");
        request.getParameter("add-last-name");
        request.getParameter("add-phone");
        request.getParameter("add-email");

        request.getParameter("add-address");
        request.getParameter("add-city");
        request.getParameter("add-state");
        request.getParameter("add-postcode");

        Address newAddress = new Address(
                "noId",
                request.getParameter("add-address"),
                request.getParameter("add-city"),
                request.getParameter("add-state"),
                request.getParameter("add-postcode")
        );

        Contact newContact = new Contact();

        Hero newHero = new Hero();
        newHero.setHeroName(request.getParameter("add-hero-name"));
        newHero.setDescription(request.getParameter("add-description"));
        newHero.setSuperpower(request.getParameter("add-super-power"));
        .
        addHero(heroId, hero);
        //model.put("message", "Hello from the controller");
        return "heros";
    }

}
