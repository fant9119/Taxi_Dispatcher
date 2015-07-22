package taxi.controller;

import taxi.exception.AuthorizationException;
import taxi.service.AuthorizationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {

    public static final Logger log = Logger.getLogger(RegisterController.class);

    @Autowired
    private AuthorizationService authorizationService;

    @RequestMapping(value = "/register.html", method = RequestMethod.POST)
    public String registration(@RequestParam("login") String login,
                           @RequestParam("password") String password,
                           @RequestParam("confirm") String confirm,
                           @RequestParam("id") String id,
                           Model model) {
        String errorMessage = "";
        if (password.equals(confirm)) {
            try {
                authorizationService.register(login, id, password);
                errorMessage = "Authorization successful!!!";
            } catch (AuthorizationException e) {
                errorMessage = e.getMessage();
            }
        } else {
            errorMessage = "Password and confirm password are different!";
        }
        model.addAttribute("errorMessage", errorMessage);
        return "register";
    }

}
