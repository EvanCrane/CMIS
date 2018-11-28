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

import javax.servlet.http.HttpServletRequest;
import javax.websocket.OnError;

@Controller
public class MainController {

    @Autowired
    private CollectionDao collectionDao;

    @Autowired
    private UserDAO userDao;


    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String basicErrorController(Model model, Principal principle){

//        // ********** Common stuff for each controller so that the header menu works **********
//        User loggedInUser = (User) ((Authentication) principle).getPrincipal();
//        String userRole;
//        boolean isPlayer = true;
//        String userName = loggedInUser.getUsername();
//        int roleID = userDao.getUserRole(userName);
//        if(roleID!=3)
//        {
//            isPlayer = false;
//        }
//        model.addAttribute("name", userName);
//        if(roleID == 1){userRole = "Admin";}
//        else if(roleID == 2){userRole = "Manager";}
//        else {userRole = "Reader";}
//        model.addAttribute("userRole", userRole);
//        model.addAttribute("isPlayer", isPlayer);
//
//        // **************** End of common stuff **************************************

        return "Error";
    }


    // Home page
    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String homePage(Model model, Principal principle){

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
        model.addAttribute("fullUser", userDao.findFullUserInfo(userName));
        model.addAttribute("userOrg", userDao.findFullUserInfo(userName).getOrganization());
        // **************** End of common stuff **************************************

        model.addAttribute("title", "Home");
        model.addAttribute("message", "Collections");
        model.addAttribute("collections", collectionDao.allCollections());


        return "HomePage";

    }

    //Page to delete specific collection
    @RequestMapping (value = "/delete", method = RequestMethod.GET)
    public String DeleteCollection(@RequestParam("id") int collID, Model model, Principal principle)
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
        model.addAttribute("userOrg", userDao.findFullUserInfo(userName).getOrganization());
        // **************** End of common stuff **************************************

        collectionDao.deleteCollection(collID);

        return "redirect:/home";
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {

        return "login";
    }

    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessful";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {

        // ********** Common stuff for each controller so that the header menu works **********
        User loggedInUser = (User) ((Authentication) principal).getPrincipal();
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

        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();

            String userInfo = WebUtils.toString(loginedUser);

            model.addAttribute("userInfo", userInfo);

            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);

        }

        return "403Page";

        // Reference for path variable handling
        // https://stackoverflow.com/questions/21061638/spring-mvc-how-to-return-custom-404-errorpages#21115267
    }

}
