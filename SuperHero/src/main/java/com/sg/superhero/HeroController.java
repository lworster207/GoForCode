package com.sg.superhero;

import com.sg.superhero.dao.HeroDao;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HeroController {

    private HeroDao dao;

    public HeroController(HeroDao herodao) {
        this.dao = herodao;
    }

    @RequestMapping(value = "/displayHerosPage", method = RequestMethod.GET)
    public String displayHeros(Map<String, Object> model) {
        //model.put("message", "Hello from the controller");
        return "heros";
    }

    @RequestMapping(value = "/createNewHero", method = RequestMethod.GET)
    public String createNewHero(Map<String, Object> model) {
        //model.put("message", "Hello from the controller");
        return "newhero";
    }

    @RequestMapping(value = "/createSuperHero", method = RequestMethod.POST)
    public String createSuperHero(Map<String, Object> model) {
        //model.put("message", "Hello from the controller");
        return "heros";
    }

}
