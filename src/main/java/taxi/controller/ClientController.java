package taxi.controller;

import taxi.domain.Client;
import taxi.exception.OrderException;
import taxi.service.ClientService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@Controller
public class ClientController {

    @Autowired
    private ClientService clientService;

    public static final Logger log = Logger.getLogger(ClientController.class);

    @RequestMapping(value = "/client_registration_form.html", method = RequestMethod.POST)
    public String registerClient() {
        log.info("/registerClient controller");
        return "registerClient";
    }

    @RequestMapping(value = "/client_registration.html", method = RequestMethod.POST)
    public String registerClient(@RequestParam Map<String, String> reqPar, Model model) {
        try {
            clientService.createClient(reqPar.get("name"), reqPar.get("surname"),
                    reqPar.get("phone"), reqPar.get("address"));
            Date date = Date.valueOf(reqPar.get("lastOrderDate"));
            Client client = clientService.findClient(reqPar.get("name"), reqPar.get("surname"),
                    reqPar.get("phone"));
            if (client == null) {
                model.addAttribute("Ops =(, Error!");
                return "registerClient";
            } else {
                client.setLastOrderDate(date);
                client.setSum(Long.parseLong(reqPar.get("sum")));
            }
            clientService.update(client);
            model.addAttribute("errorMessage","Client registration successful!");
        } catch (OrderException e) {
            model.addAttribute("errorMessage", e.getMessage());
        } catch (NumberFormatException e2) {
            model.addAttribute("errorMessage", "Sum field is a numeric field!");
        } catch (IllegalArgumentException e1) {
            model.addAttribute("errorMessage", "Date format must be: YYYY-MM-DD!");
        }
        log.info("/registerClient controller");
        return "registerClient";
    }

    @RequestMapping(value = "/clients.html", method = RequestMethod.POST)
    public String clients(@RequestParam Map<String, String> reqPar, Model model) {
        if (reqPar.containsKey("portion")) {
            String portion = reqPar.get("portion");
            if (portion.isEmpty() || portion == null) {
                model.addAttribute("errorMessage", "Portion field is empty!");
                log.info("/dashboard controller");
                return "dashboard";
            }
            try {
                List<Client> clients = clientService.showClientsByPortion(Integer.parseInt(portion));
                if (clients.isEmpty()) {
                    model.addAttribute("errorMessage", "Can't find any client!");
                    log.info("/dashboard controller");
                    return "dashboard";
                }
                model.addAttribute("list", clients);
            } catch (NumberFormatException e) {
                model.addAttribute("errorMessage", "Portion field is a numeric field!");
                log.info("/dashboard controller");
                return "dashboard";
            }
        } else if (reqPar.containsKey("sum")) {
            String sum = reqPar.get("sum");
            if (sum.isEmpty() || sum == null) {
                model.addAttribute("errorMessage", "Sum field is empty!");
                log.info("/dashboard controller");
                return "dashboard";
            }
            try {
                List<Client> clients = clientService.showClientsGtSum(Integer.parseInt(sum));
                if (clients.isEmpty()) {
                    model.addAttribute("errorMessage", "Can't find any client!");
                    log.info("/dashboard controller");
                    return "dashboard";
                }
                model.addAttribute("list", clients);
            } catch (NumberFormatException e) {
                model.addAttribute("errorMessage", "Sum field is a numeric field!");
                log.info("/dashboard controller");
                return "dashboard";
            }
        } else if (reqPar.containsKey("month")) {
            List<Client> clients = clientService.showClientsLastMonth();
            if (clients.isEmpty()) {
                model.addAttribute("errorMessage", "Can't find any client!");
                log.info("/dashboard controller");
                return "dashboard";
            }
            model.addAttribute("list", clients);
        }
        log.info("/clients controller");
        return "clients";
    }
}
