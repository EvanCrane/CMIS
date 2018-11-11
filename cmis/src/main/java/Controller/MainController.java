package Controller;

import java.lang.reflect.Type;
import java.security.Principal;
import java.util.List;

import Daos.UserDAO;
import Models.Collection;
import Daos.CollectionDao;

import Models.Users;
import Utils.WebUtils;
import com.sun.org.apache.xpath.internal.operations.Mod;
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

    @RequestMapping(value = "/users", method= RequestMethod.GET)
    public String usersMap(Model model, Principal principle)
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
        else {userRole = "Reader";}
        model.addAttribute("userRole", userRole);
        model.addAttribute("isPlayer", isPlayer);
        // **************** End of common stuff **************************************
        List<String> allOrganizations = collectionDao.AllOrgs();
        model.addAttribute("allOrgs", allOrganizations);
        model.addAttribute("users", userDao.getAllUsers());


        return "users";

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
        else {userRole = "Reader";}
        model.addAttribute("userRole", userRole);
        model.addAttribute("isPlayer", isPlayer);
        // **************** End of common stuff **************************************

        model.addAttribute("title", "Home");
        model.addAttribute("message", "Collections");
        model.addAttribute("collections", collectionDao.allCollections());


        return "HomePage";

    }

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
        else {userRole = "Reader";}
        model.addAttribute("userRole", userRole);
        model.addAttribute("isPlayer", isPlayer);
        // **************** End of common stuff **************************************

        Collection collection = new Collection();
        List<String> allOrganizations = collectionDao.AllOrgs();
        List<String> allDesOrgs = collectionDao.AllDesOrgs();

        model.addAttribute("desOrgs", allDesOrgs);
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
        else {userRole = "Reader";}
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
        else {userRole = "Reader";}
        model.addAttribute("userRole", userRole);
        model.addAttribute("isPlayer", isPlayer);
        // **************** End of common stuff **************************************

        model.addAttribute("collID", collID);
        model.addAttribute("collType", collectionDao.collecType(collID));
        return "ViewCollection";
    }

    @GetMapping("/findOne")
    @ResponseBody
    public Users findOne(@RequestParam("id") int userId)
    {
        return userDao.findUser(userId);

    }

    @GetMapping("/deleteUser")
    @ResponseBody
    public boolean deleteUser(@RequestParam("id") int userId)
    {
        return userDao.deleteUser(userId);

    }


    //Below method is for saving user information
    @PostMapping("/updateUser")
    @ResponseBody
    public boolean updateUser(@RequestParam("id") String userId, @RequestParam("name") String userName, @RequestParam("org") String organization,
                              @RequestParam("pass") String unEncPass, @RequestParam("lvl") String accessLvl)
    {

        userDao.manageUser(Integer.parseInt(userId), userName, organization, unEncPass, accessLvl);
        return true;
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
        else {userRole = "Reader";}
        model.addAttribute("userRole", userRole);
        model.addAttribute("isPlayer", isPlayer);
        // **************** End of common stuff **************************************

        return "Delete";
    }

    //********* POST method for view collection (edit or delete) **********

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
        else {userRole = "Reader";}
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

    //*********** POST method for edit collection (update) ******************

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
        else {userRole = "Reader";}
        model.addAttribute("userRole", userRole);
        model.addAttribute("isPlayer", isPlayer);
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
