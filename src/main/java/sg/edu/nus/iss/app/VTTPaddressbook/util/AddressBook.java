package sg.edu.nus.iss.app.VTTPaddressbook.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.boot.ApplicationArguments;
import org.springframework.ui.Model;

import sg.edu.nus.iss.app.VTTPaddressbook.model.Contact;

public class AddressBook {
    private Logger logger = Logger.getLogger(AddressBook.class.getName());
    public void saveContact(Contact contact, Model model, ApplicationArguments applicationArguments){
        String dataFilename = contact.getId();
        Set<String> optnames = applicationArguments.getOptionNames();
        String[] optnamesArray = optnames.toArray(new String[optnames.size()]);
        List<String> optValues = applicationArguments.getOptionValues(optnamesArray[0]);
        String[] optValuesArray = optValues.toArray(new String[optValues.size()]);
        PrintWriter printWriter = null;
        FileWriter fileWriter = null;
        try{
            fileWriter = new FileWriter(optValuesArray[0] + "/" + dataFilename, Charset.forName("UTF-8"));
            printWriter = new PrintWriter(fileWriter);
            printWriter.println(contact.getName());
            printWriter.println(contact.getEmail());
            printWriter.println(contact.getPhone());
        }catch(IOException e){
            logger.log(Level.WARNING, e.getMessage());
        }finally{
            printWriter.close(); 
            try{
                fileWriter.close(); //must be inside a try-catch block (why?)
            }catch(IOException e){
                logger.log(Level.WARNING, e.getMessage());
            }
        }
        model.addAttribute("contact", new Contact(contact.getName(), contact.getPhone(), contact.getEmail()));
    }
    public void getContactById(Model model, String  contactId, ApplicationArguments applicationArguments){
        Set<String> optnames = applicationArguments.getOptionNames();
        String[] optnamesArray = optnames.toArray(new String[optnames.size()]);
        List<String> optValues = applicationArguments.getOptionValues(optnamesArray[0]);
        String[] optValuesArray = optValues.toArray(new String[optValues.size()]);
        Contact contactResp = new Contact();
        try {
            Path filePath = new File(optValuesArray[0]+"/"+contactId).toPath();
            Charset charset = Charset.forName("UTF-8");
            List<String> contactValList = Files.readAllLines(filePath, charset);
            contactResp.setName(contactValList.get(0));
            contactResp.setEmail(contactValList.get(1));
            contactResp.setPhone(contactValList.get(2));
            contactResp.setId(contactId);
        } catch (IOException e) {
            logger.log(Level.WARNING, e.getMessage());
        }
        model.addAttribute("contact", contactResp);
    }
}
