package taxi.dao;

import taxi.domain.Client;
import taxi.domain.Order;
import java.sql.Date;
import java.util.List;

public interface OrderDao {

    Long create(Order order);
    Order read(Long id);
    boolean update(Order order);
    boolean delete(Order order);
    List<Order> findAll();

    Order findOrder(Date date, Client clientTemp, Long amount, String addressFrom, String addressTo);

    List<Order> showOrdersFromTo(Long from, Long to);

    List<Order> showOrdersByPortion(int start, int portionSize);
}
