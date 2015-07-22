package taxi.service;

import taxi.dao.OperatorDao;
import taxi.domain.Operator;
import taxi.exception.AuthorizationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
@Transactional
public class AuthorizationServiceImpl implements AuthorizationService {

    @Autowired
    private OperatorDao operatorDao;

    public AuthorizationServiceImpl() {

    }

    @Transactional
    @Override
    public boolean register(String login, String id, String pass) throws AuthorizationException {
        if (!operatorDao.checkLogin(login)) {
            throw new AuthorizationException("This login is already used!");
        }

        try {
            if (!operatorDao.checkID(Long.parseLong(id))) {
                throw new AuthorizationException("This ID is already used!");
            }
        } catch (NumberFormatException e){
            throw new AuthorizationException("ID must be only 10 numbers without other symbols!");
        }

        Pattern p = Pattern.compile("[a-zA-Z0-9&&\\S]{4,}");
        Matcher m = p.matcher(login);
        if (!m.matches()) {
            throw new AuthorizationException("Login must be more than 4 symbols without spaces!");
        }

        p = Pattern.compile("[0-9]{10}");
        m = p.matcher(id);
        if (!m.matches()) {
            throw new AuthorizationException("ID must be only 10 numbers without other symbols!");
        }

        p = Pattern.compile("[a-zA-Z0-9]{8,}");
        m = p.matcher(pass);
        if (!m.matches()) {
            throw new AuthorizationException("Bad password must be more than 8 symbols!");
        }

        Operator operator = new Operator();
        operator.setLogin(login);
        operator.setPassword(pass);
        operator.setId(Long.parseLong(id));
        operatorDao.create(operator);
        return true;
    }
}
