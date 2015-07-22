package taxi.service;

import taxi.dao.ClientDao;
import taxi.dao.OrderDao;
import taxi.domain.Client;
import taxi.domain.Order;
import taxi.exception.OrderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Date;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public OrderServiceImpl() {
    }

    @Override
    public boolean createOrder(Long id, Client client, String amount, String addressFrom, String addressTo) throws OrderException {
        return false;
    }

    @Override
    @Transactional
    public boolean createOrder(Date date, Client client, String amount, String addressFrom, String addressTo) throws OrderException {
        if (amount.isEmpty() || amount == null) {
            throw new OrderException("Amount field can't be empty!");
        }
        if (addressFrom.isEmpty() || addressFrom == null) {
            throw new OrderException("Address From field can't be empty!");
        }
        Order order = new Order(date, client, Long.parseLong(amount), addressFrom, addressTo);
        orderDao.create(order);
        return true;
    }

    @Override
    @Transactional
    public void editOrder(Long id, Client client, String amount, String addressFrom, String addressTo) {
        //updateOrder(Order order)
    }

    @Override
    @Transactional
    public List showOrders(Long from, Long to) {
        return orderDao.showOrdersFromTo(from, to);
    }

    @Override
    @Transactional
    public List showOrdersByPortion(int start, int portionSize) {
        return orderDao.showOrdersByPortion(start, portionSize);
    }

    @Override
    public List showOrdersByPortion() {
        return null;
    }

    @Override
    @Transactional
    public Order read(Long id) {
        return orderDao.read(id);
    }

    @Override
    @Transactional
    public Order findOrder(Date date, Client clientTemp, Long amount, String addressFrom, String addressTo) {
        return orderDao.findOrder(date, clientTemp, amount, addressFrom, addressTo);
    }

    @Override
    @Transactional
    public boolean updateOrder(Order order) {
        orderDao.update(order);
        return true;
    }

    @Override
    @Transactional
    public List<Order> findAll() {
        return orderDao.findAll();
    }

    @Override
    @Transactional
    public boolean deleteOrder(Long id) {
        Order order = read(id);
        return orderDao.delete(order);
    }
}
