package taxi.dao;

import taxi.domain.Operator;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class OperatorDaoImpl implements OperatorDao{

    private static Logger logger = Logger.getLogger(OperatorDaoImpl.class);

    @Autowired
    private SessionFactory factory;

    public SessionFactory getFactory() {
        return factory;
    }

    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }

    public OperatorDaoImpl() {

    }

    @Override
    public Long create(Operator operator) {
        return (Long) factory.getCurrentSession().save(operator);
    }

    @Override
    public Operator read(Long id) {
        return (Operator) factory.getCurrentSession().get(Operator.class, id);
    }

    @Override
    public boolean update(Operator operator) {
        Session session = factory.getCurrentSession();
        session.update(operator);
        return true;
    }

    @Override
    public boolean delete(Operator operator) {
        Session session = factory.getCurrentSession();
        session.delete(operator);
        return true;
    }

    @Override
    public List<Operator> findAll() {
        Session session = factory.getCurrentSession();
        return session.createCriteria(Operator.class).list();
    }

    @Override
    public boolean checkLogin(String login) {
        Session session = factory.getCurrentSession();
        Operator operator = (Operator)session.createCriteria(Operator.class)
                .add(Restrictions.eq("login", login))
                .uniqueResult();
        return operator == null;
    }

    @Override
    public boolean checkID(Long id) {
        Operator operator = read(id);
        return operator == null;
    }

    @Override
    public Operator findByLogin(String login) {
        Session session = factory.getCurrentSession();
        return (Operator)session.createCriteria(Operator.class)
                .add(Restrictions.eq("login", login))
                .uniqueResult();
    }


}
