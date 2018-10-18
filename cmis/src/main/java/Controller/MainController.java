package Controller;

import java.lang.reflect.Type;
import java.security.Principal;
import java.util.List;

import Models.Collection;
import Models.SearchQuery;
import Daos.CollectionDao;

import Utils.WebUtils;
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



    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String basicErrorController(){
        return "Error";
    }



    // Home page
    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String homePage(Model model, Principal principle){

        //User loggedInUser = (User) ((Authentication) principle).getPrincipal();

        model.addAttribute("title", "Home");
        model.addAttribute("message", "Collections");
        model.addAttribute("collections", collectionDao.allCollections());


        return "HomePage";

    }
    //Get method for searching
    /*
    @RequestMapping(value = "/home",params = {"q"},method = RequestMethod.GET)
    public String searchCollections(Model model, Principal principle, @RequestParam(value = "q") String wildcard){

        //User loggedInUser = (User) ((Authentication) principle).getPrincipal();
        model.addAttribute("title", "Home");
        model.addAttribute("message", "Collections");
        model.addAttribute("collections", collectionDao.searchCollections(wildcard));


        return "HomePage";

    }
    */

    @RequestMapping(value = {"/", "/home"}, params={"q"},method = RequestMethod.GET)
    public String searchCollections(Model model, Principal principle, @RequestParam(value = "q") String query){

        //User loggedInUser = (User) ((Authentication) principle).getPrincipal();
        model.addAttribute("title", "Home");
        model.addAttribute("message", "Collections");
        model.addAttribute("collections", collectionDao.searchCollections(query));


        return "HomePage";

    }

    //Page to add collection
    @RequestMapping (value = "/add", method = RequestMethod.GET)
    public String AddCollection(Model model){

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
    public String SaveCollection(Model model, Principal principal, @ModelAttribute(value = "collection") Collection collection, BindingResult result)
    {
//        if(result.hasErrors()){
//
//            model.addAttribute("message", "Input was not accepted");
//            return "/add";
//        }
//        try{
//            collection.setCollecIid(-1);
//
//            collectionDao.addCollection(collection);
//        }
//        catch(Exception e){}
        collection.setCollecIid(-1);
        collectionDao.addCollection(collection);

        model.addAttribute("title", "Home");
        model.addAttribute("message", "Collections");
        model.addAttribute("collections", collectionDao.allCollections());
        return "HomePage";
    }

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
