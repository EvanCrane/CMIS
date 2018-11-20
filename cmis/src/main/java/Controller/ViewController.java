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
public class ViewController {

    @Autowired
    private CollectionDao collectionDao;

    @Autowired
    private UserDAO userDao;

    //Page to view specific collection
    @RequestMapping (value = "/view", method = RequestMethod.GET)
    public String ViewCollection(@RequestParam("id") int collID, Model model, Principal principle){

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

        model.addAttribute("highlevel", collectionDao.collectionHighlights(collID));
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
        model.addAttribute("fullUser", userDao.findFullUserInfo(userName));

        return "ViewCollection";

    }


}

