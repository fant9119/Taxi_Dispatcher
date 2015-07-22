package taxi.dao;

import taxi.domain.Client;
import taxi.domain.Order;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.sql.Date;
import java.util.List;


@Repository
public class OrderDaoImpl implements OrderDao {

    private static Logger logger = Logger.getLogger(OrderDaoImpl.class);

    @Autowired
    private SessionFactory factory;

    public OrderDaoImpl(){}

    public SessionFactory getFactory() {
        return factory;
    }

    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Long create(Order order) {
        return (Long) factory.getCurrentSession().save(order);
    }

    @Override
    public Order read(Long id) {
        return (Order) factory.getCurrentSession().get(Order.class, id);
    }

    @Override
    public boolean update(Order order) {
        Session session = factory.getCurrentSession();
        session.update(order);
        return true;
    }

    @Override
    public boolean delete(Order order) {
        Session session = factory.getCurrentSession();
        session.delete(order);
        return true;
    }

    @Override
    public List<Order> findAll() {
        Session session = factory.getCurrentSession();
        return session.createCriteria(Order.class).list();
    }

    @Override
    public Order findOrder(Date date, Client clientTemp, Long amount, String addressFrom, String addressTo) {
        Session session = factory.getCurrentSession();
        return (Order)session.createCriteria(Order.class)
                .add(Restrictions.eq("dateOfOrder", date))
                .add(Restrictions.eq("client", clientTemp))
                .add(Restrictions.eq("amount", amount))
                .add(Restrictions.eq("addressFrom", addressFrom))
                .add(Restrictions.eq("addressTo", addressTo))
                .uniqueResult();
    }

    @Override
    public List<Order> showOrdersFromTo(Long from, Long to) {
        Session session = factory.getCurrentSession();
        return session.createCriteria(Order.class)
                .add(Restrictions.ge("amount", from))
                .add(Restrictions.le("amount", to))
                .list();
    }

    @Override
    public List<Order> showOrdersByPortion(int start, int portionSize) {
        return factory.getCurrentSession().createCriteria(Order.class)
                .setFirstResult(start)
                .setMaxResults(portionSize)
                .list();
    }
}
