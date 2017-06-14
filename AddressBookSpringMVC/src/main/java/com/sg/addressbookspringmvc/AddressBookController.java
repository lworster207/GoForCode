package com.sg.addressbookspringmvc;

import com.sg.addressbookspringmvc.dao.AddressBookDao;
import com.sg.addressbookspringmvc.model.Address;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author ward
 */
@Controller
public class AddressBookController {

    AddressBookDao dao;

    @Inject
    public AddressBookController(AddressBookDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/displayAddressPage", method = RequestMethod.GET)
    public String displayAddressPage(Model model) {
        // Get all the AddressBooks from the DAO
        List<Address> addressList = dao.getAddresses();
        int count = dao.getAddressCount();

        // Put the List of AddressBooks on the Model
        model.addAttribute("addressList", addressList);
        model.addAttribute("count", count);

        // Return the logical name of our View component
        return "addresses";
    }

    @RequestMapping(value = "/createAddress", method = RequestMethod.POST)
    public String createAddress(HttpServletRequest request) {
        // grab the incoming values from the form and create a new AddressBook
        // object
        Address address = new Address();
        address.setFirstName(request.getParameter("firstName"));
        address.setLastName(request.getParameter("lastName"));
        address.setStreet(request.getParameter("street"));
        address.setCity(request.getParameter("city"));
        address.setState(request.getParameter("state"));
        address.setPostCode(request.getParameter("postcode"));

        // persist the new AddressBook
        dao.addAddress(address);

        // we don't want to forward to a View component - we want to
        // redirect to the endpoint that displays the AddressBooks Page
        // so it can display the new AddressBook in the table.
        return "redirect:displayAddressPage";
    }

    @RequestMapping(value = "/deleteAddress", method = RequestMethod.GET)
    public String deleteAddress(HttpServletRequest request) {
        String addressIdParameter = request.getParameter("addressId");
        long addressId = Long.parseLong(addressIdParameter);
        dao.deleteAddressById(addressId);
        return "redirect:displayAddressPage";
    }

}
