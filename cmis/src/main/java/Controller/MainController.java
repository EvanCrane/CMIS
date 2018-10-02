package Controller;

import java.lang.reflect.Type;
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
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.OnError;

@Controller
public class MainController {

    //@Autowired
    // Fix later
    // private CollectionDao collectionDao;


//    @RequestMapping(value = "/error", method = RequestMethod.GET)
//    @ResponseBody
//    public String errorPage(HttpServletRequest request){
//        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
//        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
//        return String.format("<html><body><h2>Error Page</h2><div>Status code: <b>%s</b></div>"
//                        + "<div>Exception Message: <b>%s</b></div><body></html>",
//                statusCode, exception==null? "N/A": exception.getMessage());
//
//    }


    //Going to delete welcome page?
//    @RequestMapping(value = {"/WelcomePage"}, method = RequestMethod.GET)
//    public String welcomePage(Model model){
//        model.addAttribute("title", "Welcome");
//        model.addAttribute("message", "This is welcome page!");
//        return "WelcomePage";
//    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String basicErrorController(){
        return "403Page";
    }

    @OnError
    @RequestMapping
    public String errorCatch(){
        return "403Page";
    }






    // Home page
    @RequestMapping(value = {"/","/home"}, method = RequestMethod.GET)
    public String homePage(Model model, Principal principle){

        //User loggedInUser = (User) ((Authentication) principle).getPrincipal();

        model.addAttribute("title", "Home");
        model.addAttribute("message", "This is the home page");



        return "HomePage";
    }

    //Page to add collection
    @RequestMapping (value = "/add", method = RequestMethod.GET)
    public String AddCollection(Model model){

        //Collection collection = new Collection();

        //model.addAttribute("collection", collection);
        model.addAttribute("message", "This is the Add Collection Page");
        model.addAttribute("title", "Add");
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
    public String loginPage() {

        return "login";
    }

    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessful";
    }

    @RequestMapping(value = "/403")
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

        // Reference for path variable handling
        // https://stackoverflow.com/questions/21061638/spring-mvc-how-to-return-custom-404-errorpages#21115267
    }

}
