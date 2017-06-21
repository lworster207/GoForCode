/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.sg.superhero.dao.HeroDao;
import com.sg.superhero.dao.SuperPowerDao;
import com.sg.superhero.model.SuperPower;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author apprentice
 */
@CrossOrigin
@Controller
public class SuperHeroRESTController {

    private HeroDao herodao;
    private SuperPowerDao superPowerDao;

    @Inject
    public SuperHeroRESTController(HeroDao herodao, SuperPowerDao superPowerDao) {
        this.herodao = herodao;
        this.superPowerDao = superPowerDao;
    }

    @RequestMapping(value = "/superpowers/{id}", method = RequestMethod.GET)
    @ResponseBody
    public List<SuperPower> getHeroSuperPowers(@PathVariable("id") String id) {
        return superPowerDao.getSuperPowersByHero(id);
    }

}
