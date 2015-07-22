package taxi.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes("success")
public class DashboardController {

    public static final Logger log = Logger.getLogger(DashboardController.class);

    @RequestMapping(value = "/dashboard.html", method = {RequestMethod.GET, RequestMethod.POST})
     public String dashboard(Model model) {
        if (!model.containsAttribute("success")) {
            log.info("/index controller");
            return "index";
        } else {
            log.info("/dashboard controller");
            return "dashboard";
        }
    }

    @RequestMapping(value = "/logout.html", method = {RequestMethod.GET, RequestMethod.POST})
    public String logout(SessionStatus status) {
        status.setComplete();
        log.info("/index controller");
        return "index";
    }

}
