package Controller;

import java.security.Principal;

import Daos.UserDAO;
import Models.Collection;
import Daos.CollectionDao;

//import com.sun.org.apache.xpath.internal.operations.Mod;
import Models.Contacts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sun.misc.Request;

@Controller
public class EditController {

    @Autowired
    private CollectionDao collectionDao;

    @Autowired
    private UserDAO userDao;

    //Page to edit collection
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String EditCollection(@RequestParam("id") int collID, Model model, Principal principle){

        // ********** Common stuff for each controller so that the header menu works **********
        User loggedInUser = (User) ((Authentication) principle).getPrincipal();
        String userRole;
        boolean isPlayer = true;
        String userName = loggedInUser.getUsername();
        int roleID = userDao.getUserRole(userName);
        if(roleID!=3)
        {
            isPlayer = false;
        }
        model.addAttribute("name", userName);
        if(roleID == 1){userRole = "Admin";}
        else if(roleID == 2){userRole = "Manager";}
        else if(roleID == 4){userRole = "Developer";}
        else{userRole = "Reader";}
        model.addAttribute("userRole", userRole);
        model.addAttribute("isPlayer", isPlayer);
        model.addAttribute("userOrg", userDao.findFullUserInfo(userName).getOrganization());
        // **************** End of common stuff **************************************

        Collection fullCollection = collectionDao.getFullCollection(collID);
        model.addAttribute("highlevel", fullCollection);
        model.addAttribute("collecOrg", collectionDao.getCollecOrg(collID));
        model.addAttribute("collecDesOrg", collectionDao.getCollecDesOrg(collID));
        model.addAttribute("general", collectionDao.getGeneral(collID));
        model.addAttribute("controlsImpacts", collectionDao.getControlsImpacts(collID));
        String collecType = collectionDao.collecType(collID);

        if(collecType.equals("Environment"))
        { model.addAttribute("environment", collectionDao.getEnvironment(collID)); }

        else if(collecType.equals("Logical Server"))
        {
            model.addAttribute("logicalServer", collectionDao.getLogicalServer(collID));
        }
        else if(collecType.equals("Instance Application"))
        {
            model.addAttribute("myApplication", collectionDao.getApplication(collID));
        }

        model.addAttribute("collID", collID);
        model.addAttribute("collType", collecType);
        model.addAttribute("allOrgs", collectionDao.AllOrgs());
        model.addAttribute("servType", fullCollection.getServiceType());
        model.addAttribute("collection", collectionDao.getFullCollection(collID));


        return "EditCollection";
    }

    @RequestMapping(value="/edit", method =RequestMethod.POST)
    public String editCollection(Model model, Principal principle, @ModelAttribute(value="collection") Collection collection, BindingResult result)
    {
        collectionDao.updateCollection(collection);
        return "redirect:view?id="+collection.getCollecIid();
    }

    @RequestMapping(value="/editContact", method =RequestMethod.GET)
    @ResponseBody
    public Contacts editContact(@RequestParam("id") int collID, @RequestParam("uID") int userID)
    {
        return collectionDao.getAContact(collID, userID);
    }

}
