package taxi.controller;

import taxi.domain.Operator;
import taxi.exception.AuthenticationException;
import taxi.service.AuthenticationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("success")
public class AuthenticationController {

    public static final Logger log = Logger.getLogger(AuthenticationController.class);

    @Autowired
    private AuthenticationService service;

    @Value("${numberOfTries}")
    private int tries;
    private int countOfTries = 0;

    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }

    @RequestMapping(value = "/authentication.html", method = RequestMethod.POST)
    public String authentication(@RequestParam String login,
                                @RequestParam String password,
                                Model model) {
        try {
            if (login.isEmpty()) throw new AuthenticationException("Login field is empty!");
            if (password.isEmpty()) throw new AuthenticationException("Password field is empty!");

            if (service.authenticate(login, password)) {
                Operator operator = service.findByLogin(login);
                if (operator.isBlocked()) {
                    model.addAttribute("message", "Operator " + login + " is blocked!");
                    log.info("/index controller");
                    return "index";
                }
                int role = operator.getRole().ordinal();
                countOfTries = 0;
                model.addAttribute("role", role);
                model.addAttribute("success", "success");
                log.info("/dashboard controller");
                return "dashboard";
            } else {
                countOfTries++;
                if (countOfTries == tries) {
                    if (service.operatorBlocking(login)) {
                        model.addAttribute("message", "Operator " + login + " is blocked!");
                        countOfTries = 0;
                    } else {
                        model.addAttribute("message", "Operator " + login + " is not exist!");
                        countOfTries = 0;
                    }
                    log.info("/index controller");
                    return "index";
                } else if (tries - countOfTries == 1) {
                    model.addAttribute("message", "Invalid login or password! You have only one try!");
                } else {
                    model.addAttribute("message", "Invalid login or password! Try again!\n" + (tries - countOfTries) + " tries remain!");
                }
                log.info("/index controller");
                return "index";
            }
        } catch (AuthenticationException e) {
            model.addAttribute("message", e.getMessage());
            log.info("/index controller");
            return "index";
        }
    }

    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String index(Model model) {
        if (!model.containsAttribute("success")) {
            log.info("/index controller");
            return "index";
        } else {
            log.info("/dashboard controller");
            return "dashboard";
        }
    }

    @RequestMapping(value = "/registration.html", method = {RequestMethod.GET, RequestMethod.POST})
    public String register() {
        log.info("/register controller");
        return "register";
    }

    public AuthenticationController() {

    }

}
