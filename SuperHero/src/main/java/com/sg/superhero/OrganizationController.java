/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero;

import com.sg.superhero.model.Address;
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
        String organizationId = request.getParameter("organizationId");

        Organization organization = organizationService.getOrganization(organizationId);
        model.addAttribute("organization", organization);

        List<Hero> heroList = organizationService.getHerosByOrganization(organizationId);

        model.addAttribute("heroList", heroList);

        // service.deleteOrganization(organizationId,addressId);
        return "vieworganization";
    }

    @RequestMapping(value = "/deleteOrganization", method = RequestMethod.GET)
    public String deleteOrganization(HttpServletRequest request, Model model) {
        String organizationId = request.getParameter("organizationId");
        String addressId = request.getParameter("addressId");

        organizationService.deleteOrganization(organizationId, addressId);
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

        Address newAddress;
        String addressId;

        if (request.getParameter("add-address").equals("")
                || request.getParameter("add-city").equals("")
                || request.getParameter("add-state").equals("")
                || request.getParameter("add-postcode").equals("")) {
            newAddress = null;
        } else {
            newAddress = new Address(
                    "",
                    request.getParameter("add-address"),
                    request.getParameter("add-city"),
                    request.getParameter("add-state"),
                    request.getParameter("add-postcode")
            );
            service.addAddress(newAddress.getAddressId(), newAddress);
            organization.setAddressId(newAddress.getAddressId());

        }
        organizationService.addOrganization("", organization);
        List<Organization> orgsList = organizationService.getAllOrganizations();

        if (orgsList != null) {
            model.addAttribute("orgsList", orgsList);
        }
        return "organizations";
    }

    @RequestMapping(value = "/displayEditOrganizationForm", method = RequestMethod.GET)
    public String displayEditOrganizationForm(HttpServletRequest request, Model model) {
        String organizationId = request.getParameter("organizationId");

        Address address;

        Organization organization = organizationService.getOrganization(organizationId);

        if (organization.getAddressId() != null) {
            address = service.getAddress(organization.getAddressId());
            if (address != null) {
                model.addAttribute("address", address);
            }
        }

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

        Address newAddress;
        String addressId;

        addressId = organization.getAddressId();
        newAddress = new Address(
                addressId,
                request.getParameter("add-address"),
                request.getParameter("add-city"),
                request.getParameter("add-state"),
                request.getParameter("add-postcode")
        );

        organizationService.updateOrganization(organization.getOrganizationId(), organization, newAddress);
        List<Organization> orgsList = organizationService.getAllOrganizations();

        if (orgsList != null) {
            model.addAttribute("orgsList", orgsList);
        }
        return "organizations";
    }
}
