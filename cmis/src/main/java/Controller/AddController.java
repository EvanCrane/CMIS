package Controller;

import java.lang.reflect.Type;
import java.security.Principal;
import java.util.List;

import Daos.UserDAO;
import Models.Collection;
import Daos.CollectionDao;

import Models.Users;
import Utils.WebUtils;
//import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.UserDataHandler;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.OnError;

@Controller
public class AddController {

    @Autowired
    private CollectionDao collectionDao;

    @Autowired
    private UserDAO userDao;

    //Page to add collection
    @RequestMapping (value = "/add", method = RequestMethod.GET)
    public String AddCollection(Model model, Principal principle){

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
        // **************** End of common stuff **************************************

        Collection collection = new Collection();
        List<String> allOrganizations = collectionDao.AllOrgs();



        model.addAttribute("curUser", userDao.findFullUserInfo(userName));
        model.addAttribute("allOrgs", allOrganizations);
        model.addAttribute("message", "Add Collection");
        model.addAttribute("title", "Add");
        model.addAttribute("collection", collection);
        return "AddCollection";
    }

    //********** POST method for add collection **************
    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String SaveCollection(Model model, Principal principle, @ModelAttribute(value = "collection") Collection collection, BindingResult result)
    {
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
        // **************** End of common stuff **************************************

        collection.setCollecIid(-1);
        collectionDao.addCollection(collection);

        model.addAttribute("title", "Home");
        model.addAttribute("message", "Collections");
        model.addAttribute("collections", collectionDao.allCollections());
        return "redirect:/home";
    }

}
