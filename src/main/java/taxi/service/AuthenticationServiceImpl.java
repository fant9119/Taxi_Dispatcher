package taxi.service;

import taxi.dao.OperatorDao;
import taxi.domain.Operator;
import taxi.exception.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private OperatorDao operatorDao;

    public AuthenticationServiceImpl() {

    }

    public AuthenticationServiceImpl(OperatorDao operatorDao) {
        this.operatorDao = operatorDao;
    }


    @Override
    @Transactional
    public boolean authenticate(String login, String pass) throws AuthenticationException {
        Operator operator = new Operator(1L, login.trim(), pass.trim());

        List<Operator> operators = operatorDao.findAll();

        for (Operator oper : operators) {
            if (oper.equals(operator)) {
                return true;
            }
        }
        return false;
    }

    @Override
    @Transactional
    public List<Operator> findAll() {
        return operatorDao.findAll();
    }

    @Override
    @Transactional
    public boolean operatorBlocking(String login) {
        Operator op = operatorDao.findByLogin(login);
        if (op == null) return false;
        op.setBlocking(true);
        operatorDao.update(op);
        return true;
    }

    @Override
    public Operator findByLogin(String login) {
        return operatorDao.findByLogin(login);
    }

}
