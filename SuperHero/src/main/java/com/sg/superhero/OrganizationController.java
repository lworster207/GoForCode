/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero;

import com.sg.superhero.model.Hero;
import com.sg.superhero.model.Organization;
import com.sg.superhero.service.OrganizationServiceLayer;
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

/**
 *
 * @author apprentice
 */
@Controller
public class OrganizationController {

    private SuperHeroServiceLayer service;

    @Inject
    private OrganizationServiceLayer organizationService;

    public OrganizationController(SuperHeroServiceLayer service) {
        this.service = service;
    }

    @RequestMapping(value = "/viewOrganization", method = RequestMethod.GET)
    public String viewOrganization(HttpServletRequest request, Model model) {
        Integer organizationId = Integer.parseInt(request.getParameter("organizationId"));

        Organization organization = organizationService.getOrganization(organizationId);
        model.addAttribute("organization", organization);

        List<Hero> heroList = organizationService.getHerosByOrganization(organizationId);

        model.addAttribute("heroList", heroList);

        // service.deleteOrganization(organizationId,addressId);
        return "vieworganization";
    }

    @RequestMapping(value = "/deleteOrganization", method = RequestMethod.GET)
    public String deleteOrganization(HttpServletRequest request, Model model) {
        Integer organizationId = Integer.parseInt(request.getParameter("organizationId"));

        organizationService.deleteOrganization(organizationId);
        return "redirect:displayOrganizations";
    }

    @RequestMapping(value = "/displayOrganizations", method = RequestMethod.GET)
    public String displayOrganizations(Model model) {
        //model.put("message", "Hello from the controller");
        List<Organization> orgsList = organizationService.getAllOrganizations();
        if (orgsList != null) {
            model.addAttribute("orgsList", orgsList);
        }
        return "organizations";
    }

    @RequestMapping(value = "/createNewOrganization", method = RequestMethod.GET)
    public String createNewOrganization(Model model) {
        //model.put("message", "Hello from the controller");
        Organization newOrg = new Organization();
        model.addAttribute("organization", newOrg);
        return "neworganization";
    }

    @RequestMapping(value = "/createOrganization", method = RequestMethod.POST)
    public String createOrganization(@Valid @ModelAttribute("organization") Organization organization, BindingResult result,
            HttpServletRequest request, Model model) {
        //model.put("message", "Hello from the controller");

        if (result.hasErrors()) {

            model.addAttribute("organization", organization);
            return "neworganization";
        }

        organizationService.addOrganization(0, organization);
        List<Organization> orgsList = organizationService.getAllOrganizations();

        if (orgsList != null) {
            model.addAttribute("orgsList", orgsList);
        }
        return "organizations";
    }

    @RequestMapping(value = "/displayEditOrganizationForm", method = RequestMethod.GET)
    public String displayEditOrganizationForm(HttpServletRequest request, Model model) {
        Integer organizationId = Integer.parseInt(request.getParameter("organizationId"));

        Organization organization = organizationService.getOrganization(organizationId);

        model.addAttribute("organization", organization);
        return "editorganization";
    }

    @RequestMapping(value = "/updateOrganization", method = RequestMethod.POST)
    public String updateOrganization(@Valid @ModelAttribute("organization") Organization organization, BindingResult result,
            HttpServletRequest request, Model model) {
        //model.put("message", "Hello from the controller");

        if (result.hasErrors()) {

            model.addAttribute("organization", organization);
            return "editorganization";
        }

        organizationService.updateOrganization(organization.getOrganizationId(), organization);
        List<Organization> orgsList = organizationService.getAllOrganizations();

        if (orgsList != null) {
            model.addAttribute("orgsList", orgsList);
        }
        return "organizations";
    }
}
