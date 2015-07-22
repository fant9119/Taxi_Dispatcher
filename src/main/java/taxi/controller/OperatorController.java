package taxi.controller;


import taxi.service.OperatorService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class OperatorController {

    public static final Logger log = Logger.getLogger(OperatorController.class);

    @Autowired
    private OperatorService operatorService;

    @RequestMapping(value = "/operators.html", method = RequestMethod.POST)
    public String operators(Model model) {
        model.addAttribute("operators", operatorService.findAllOperators());
        log.info("/operators controller");
        return "operators";
    }
}
