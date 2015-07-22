package taxi.dao;

import taxi.domain.Client;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Repository
public class ClientDaoImpl implements ClientDao {

    private static Logger logger = Logger.getLogger(ClientDaoImpl.class);

    @Autowired
    private SessionFactory factory;

    public SessionFactory getFactory() {
        return factory;
    }

    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }

    public ClientDaoImpl() {

    }

    @Override
    public Long create(Client client) {
        return (Long) factory.getCurrentSession().save(client);
    }

    @Override
    public Client read(Long id) {
        return (Client) factory.getCurrentSession().get(Client.class, id);
    }

    @Override
    public boolean update(Client client) {
        Session session = factory.getCurrentSession();
        session.update(client);
        return true;
    }

    @Override
    public boolean delete(Client client) {
        Session session = factory.getCurrentSession();
        session.delete(client);
        return true;
    }

    @Override
    public List<Client> findAll() {
        Session session = factory.getCurrentSession();
        return session.createCriteria(Client.class).list();
    }

    @Override
    public List showClientsByPortion(int portionSize) {
        Session session = factory.getCurrentSession();
        return session.createCriteria(Client.class)
                .setFirstResult(0)
                .setMaxResults(portionSize)
                .list();
    }
    @Override
    public List showClientsByPortion(int portionSize, int start) {
        return factory.getCurrentSession().createCriteria(Client.class)
                .setFirstResult(start)
                .setMaxResults(portionSize)
                .list();
    }

    @Override
    public List showClientsGtSum(long sum) {
        return factory.getCurrentSession().createCriteria(Client.class)
                .add(Restrictions.gt("sum", sum))
                .list();
    }

    @Override
    public List showClientsLastMonth() {
        GregorianCalendar now = new GregorianCalendar();
        GregorianCalendar date = new GregorianCalendar();
        date.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH)-1, now.get(Calendar.DATE));
        java.sql.Date lastMonth = new java.sql.Date(date.getTimeInMillis());
        return factory.getCurrentSession().createCriteria(Client.class)
                .add(Restrictions.gt("lastOrderDate", lastMonth))
                .list();
    }

    @Override
    public Client findClient(String name, String surname, String phone) {
        try {
            return (Client) factory.getCurrentSession().createCriteria(Client.class)
                    .add(Restrictions.eq("name", name))
                    .add(Restrictions.eq("surname", surname))
                    .add(Restrictions.eq("phone", phone))
                    .uniqueResult();
        } catch (HibernateException e) {
            e.getMessage();
        }
        return null;
    }
}
