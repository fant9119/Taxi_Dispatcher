package taxi.controller;

import taxi.domain.Client;
import taxi.domain.Order;
import taxi.exception.OrderException;
import taxi.service.ClientService;
import taxi.service.OrderService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@Controller
public class OrderServlet {

    public static final Logger log = Logger.getLogger(OrderServlet.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private ClientService clientService;

    @RequestMapping(value = "/new_order.html", method = RequestMethod.POST)
    public String newOrder(Model model) {
        model.addAttribute("title", "New Order");
        model.addAttribute("formURL","/order_create.html");
        log.info("/order controller");
        return "order";
    }

    @RequestMapping(value = "/edit_order.html", method = RequestMethod.POST)
    public String editOrder(@RequestParam String orderId, Model model) {
        model.addAttribute("formURL","/order_edit/" + orderId + ".html");
        model.addAttribute("title", "Edit Order ID:");
        try {
            if (orderId.isEmpty() || orderId ==null) {
                throw new OrderException("Order id field is empty!");
            }
            Long id = Long.parseLong(orderId);
            Order order = orderService.read(id);
            if (order == null) {
                throw new OrderException("Can't find order with id: " + id);
            }
            model.addAttribute("dateOfOrder", order.getDateOfOrder());
            model.addAttribute("clientId", order.getClient().getId());
            model.addAttribute("amount", order.getAmount());
            model.addAttribute("addressFrom", order.getAddressFrom());
            model.addAttribute("addressTo", order.getAddressTo());
            model.addAttribute("orderId", id);
            log.info("/order controller");
            return "order";
        } catch (NumberFormatException e) {
            model.addAttribute("errorMessage", "Order id must be a number!");
            return "dashboard";
        } catch (OrderException e1) {
            model.addAttribute("errorMessage", e1.getMessage());
            return "dashboard";
        }
    }

    @RequestMapping(value = "/order_create.html", method = RequestMethod.POST)
    public String checkOrder(@RequestParam String dateOfOrder,
                             @RequestParam String client,
                             @RequestParam String amount,
                             @RequestParam String addressFrom,
                             @RequestParam String addressTo,
                             Model model) {
        try {
            Long clientId = Long.parseLong(client);
            Client clientTemp = clientService.read(clientId);
            Date date = Date.valueOf(dateOfOrder);
            if (clientTemp == null) {
                throw new OrderException("Can't find a client with id: " + client);
            }
            orderService.createOrder(date, clientTemp, amount, addressFrom, addressTo);
            Order order = orderService.findOrder(date, clientTemp, Long.parseLong(amount), addressFrom, addressTo);
            clientTemp.getOrders().add(order);
            long clientSum = clientTemp.getSum();
            clientTemp.setSum(clientSum + Long.parseLong(amount));
            clientTemp.setLastOrderDate(date);
            clientService.update(clientTemp);
            model.addAttribute("errorMessage", "New Order created!");
        } catch (NumberFormatException e) {
            model.addAttribute("errorMessage", "Client id must be a numeric value!");
        } catch (IllegalArgumentException e){
            model.addAttribute("errorMessage", "Date format must be: YYYY-MM-DD!");
        } catch (OrderException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "order";
    }

    @RequestMapping(value = "/order_edit/{orderId}.html", method = RequestMethod.POST)
    public String editOrder(@RequestParam String dateOfOrder,
                             @RequestParam String client,
                             @RequestParam String amount,
                             @RequestParam String addressFrom,
                             @RequestParam String addressTo,
                             @PathVariable String orderId,
                             Model model) {
        try {
            if (addressFrom.isEmpty() || addressFrom == null) {
                throw new OrderException("Address From field can't be empty!");
            }
            if (addressTo.isEmpty() || addressFrom == null) {
                throw new OrderException("Address To field can't be empty!");
            }
            Long id = Long.parseLong(orderId);
            Order order = orderService.read(id);
            Long oldAmount = order.getAmount();
            Long clientId = Long.parseLong(client);
            Client clientTemp = clientService.read(clientId);
            if (clientTemp == null) {
                throw new OrderException("Can't find a client with id: " + client);
            }
            Long newAmount = Long.parseLong(amount);
            Long sum = clientTemp.getSum();
            clientTemp.setSum(sum - oldAmount + newAmount);
            clientService.update(clientTemp);

            Date date = Date.valueOf(dateOfOrder);

            order.setDateOfOrder(date);
            order.setClient(clientTemp);
            order.setAmount(newAmount);
            order.setAddressFrom(addressFrom);
            order.setAddressTo(addressTo);
            orderService.updateOrder(order);
            model.addAttribute("errorMessage", "Order " + orderId +" updated!");
        } catch (NumberFormatException e) {
            model.addAttribute("errorMessage", "Client id and Amount fields are numeric values!");
        } catch (IllegalArgumentException e){
            model.addAttribute("errorMessage", "Date format must be: YYYY-MM-DD!");
        } catch (OrderException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "order";
    }

    @RequestMapping(value = "/orders_from_to.html", method = RequestMethod.POST)
    public String ordersFromTo(@RequestParam  String min,  @RequestParam String max, Model model) {
        try {
            if (min.isEmpty() || min ==null) {
                throw new OrderException("Min field is empty!");
            }
            if (max.isEmpty() || max ==null) {
                throw new OrderException("Max field is empty!");
            }

            Long minValue = Long.parseLong(min);
            Long maxValue = Long.parseLong(max);
            if (minValue > maxValue) {
                throw new OrderException("Min field can't be greater than Max field!");
            }

            List<Order> orders = orderService.showOrders(minValue, maxValue);
            if (orders.isEmpty()) {
                throw new OrderException("Can't find any order!");
            }
            model.addAttribute("list", orders);
            log.info("/orders controller");
            return "orders";
        } catch (NumberFormatException e) {
            model.addAttribute("errorMessage", "Min and Max fields are numeric values!");
            return "dashboard";
        } catch (OrderException e1) {
            model.addAttribute("errorMessage", e1.getMessage());
            return "dashboard";
        }
    }

    @RequestMapping(value = "/orders.html", method = RequestMethod.POST)
    public String ordersFromTo(Model model) {
            List<Order> orders = orderService.showOrdersByPortion(0, 5);
        if (orders.isEmpty()) {
            model.addAttribute("errorMessage", "Can't find any order!");
            log.info("/dashboard controller");
            return "dashboard";
        } else {
            model.addAttribute("list", orders);
            log.info("/orders controller");
            return "orders";
        }
    }

    @RequestMapping(value = "/getAllOrders.html", method = RequestMethod.GET)
    public @ResponseBody String hello(Model model) {
       List<Order> orders = orderService.findAll();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < orders.size(); i++) {
            if (i == orders.size()-1) {
                sb.append(orders.get(i).toString());
            } else {
                sb.append(orders.get(i).toString() + "|");
            }
        }
        return sb.toString();
    }

    @RequestMapping(value = "/takeOrder/{order_id}", method = RequestMethod.GET)
    public void takeOrder(@PathVariable("order_id") String id) {
        orderService.deleteOrder(Long.parseLong(id));
    }
}
