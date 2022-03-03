package sg.edu.nus.iss.app.VTTPaddressbook.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import sg.edu.nus.iss.app.VTTPaddressbook.model.Contact;
import sg.edu.nus.iss.app.VTTPaddressbook.util.AddressBook;

@Controller
public class AddressBookController {
    private Logger logger = Logger.getLogger(AddressBookController.class.getName());

    @Autowired // looks for beans of type then name (if multiple of same type)
    private ApplicationArguments applicationArguments;

    @GetMapping("/")
    public String contactForm(Model model) {
        logger.log(Level.INFO, "Show the contact form");
        model.addAttribute("contact", new Contact());
        return "contact";
    }

    @GetMapping("/getContact/{contactId}")
    public String getContact(Model model, @PathVariable(value = "contactId") String contactId) {
        logger.log(Level.INFO, "contactId " + contactId);
        AddressBook book = new AddressBook();
        book.getContactById(model, contactId, applicationArguments);
        return "showContact";
    }

    @PostMapping("/contact") // name for what the page defines
    public String contactSubmit(@ModelAttribute Contact contact, Model model) {
        logger.log(Level.INFO, "ID " + contact.getId());
        logger.log(Level.INFO, "Name " + contact.getName());
        logger.log(Level.INFO, "Phone " + contact.getPhone());
        logger.log(Level.INFO, "Email " + contact.getEmail());
        AddressBook book = new AddressBook();
        book.saveContact(contact, model, applicationArguments);
        return "showContact";
    }
}
