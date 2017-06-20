package com.sg.superhero;

import com.sg.superhero.dao.HeroDao;
import com.sg.superhero.dao.SuperPowerDao;
import com.sg.superhero.model.SuperPower;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
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
    public String createSuperHero(Map<String, Object> model) {
        //model.put("message", "Hello from the controller");
        return "heros";
    }

}
