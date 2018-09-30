package Controllers;

import java.security.Principal;
import java.util.List;

import Models.Collection;
import Daos.CollectionDao;

import Utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    @Autowired
    private CollectionDao collectionDao;

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcomePage(Model model){
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");
        return "WelcomePage";
    }

    // Home page
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String homePage(Model model, Principal principle){

        User loggedInUser = (User) ((Authentication) principle).getPrincipal();

        String userInfo = WebUtils.toString(loggedInUser);
        //model.addAttribute("userInfo", userInfo);

        List<Collection> collectionsList = collectionDao.getAllCollections();
        model.addAttribute("collections", collectionsList);

        return "HomePage";
    }

    //Page to add collection
    @RequestMapping (value = "/add", method = RequestMethod.GET)
    public String AddCollection(Model model){

        Collection collection = new Collection();

        model.addAttribute("collection", collection);
        return "AddCollection";
    }

    //********** POST method for add collection **************

    //Page to view specific collection
    @RequestMapping (value = "/view", method = RequestMethod.GET)
    public String ViewCollection(Model model){

        return "ViewCollection";
    }

    //********* POST method for view collection (edit or delete) **********

    //Page to edit collection
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String EditCollection(Model model){


        return "EditCollection";
    }

    //*********** POST method for edit collection (update) ******************

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {

        return "loginPage";
    }

    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {

        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();

            String userInfo = WebUtils.toString(loginedUser);

            model.addAttribute("userInfo", userInfo);

            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);

        }

        return "403Page";
    }

}
