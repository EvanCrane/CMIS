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
public class UsersController {

    @Autowired
    private CollectionDao collectionDao;

    @Autowired
    private UserDAO userDao;

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
        else if(roleID == 4){userRole = "Developer";}
        else{userRole = "Reader";}
        model.addAttribute("userRole", userRole);
        model.addAttribute("isPlayer", isPlayer);
        // **************** End of common stuff **************************************
        List<String> allOrganizations = collectionDao.AllOrgs();

        model.addAttribute("currentUser", loggedInUser.getUsername());
        model.addAttribute("allOrgs", allOrganizations);
        model.addAttribute("users", userDao.getAllUsers());


        return "users";

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

}
