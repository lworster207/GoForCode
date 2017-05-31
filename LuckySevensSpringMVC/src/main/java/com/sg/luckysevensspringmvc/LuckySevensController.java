/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.luckysevensspringmvc;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LuckySevensController {

    @RequestMapping(value = "moneyToBet", method = RequestMethod.POST)

    public String moneyToBet(HttpServletRequest request, Map<String, Object> model) {

    }

    public LuckySevensController() {
    }

    @RequestMapping(value = "/sayhi", method = RequestMethod.GET)
    public String sayHi(Map<String, Object> model) {
        model.put("message", "Hello from the controller");
        return "hello";
    }
}
