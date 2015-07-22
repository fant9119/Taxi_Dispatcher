package taxi.service;

import taxi.domain.Client;
import taxi.domain.Order;
import taxi.exception.OrderException;
import java.sql.Date;
import java.util.List;

public interface OrderService {

    boolean createOrder(Long id, Client client, String amount, String addressFrom, String addressTo) throws OrderException;
    boolean createOrder(Date date, Client client, String amount, String addressFrom, String addressTo) throws OrderException;
    void editOrder(Long id, Client client, String amount, String addressFrom, String addressTo);
    List showOrders(Long from, Long to);
    List showOrdersByPortion(int start, int portionSize);
    List showOrdersByPortion();

    Order read(Long id);

    Order findOrder(Date date, Client clientTemp, Long amount, String addressFrom, String addressTo);
    boolean updateOrder(Order order);

    List<Order> findAll();

    boolean deleteOrder(Long id);
}
