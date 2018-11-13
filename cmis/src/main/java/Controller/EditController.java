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
        // **************** End of common stuff **************************************

        // Get the collection id
        // create a collection object
        // add the collection object to the model


        //Collection aCollection = collectionDao.getACollection(collecId);
        model.addAttribute("collID", collID);
        model.addAttribute("collType", collectionDao.collecType(collID));

        return "EditCollection";
    }

}
