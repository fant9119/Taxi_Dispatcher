package taxi.service;

import taxi.domain.Operator;
import taxi.exception.AuthenticationException;
import java.util.List;

public interface AuthenticationService {

    boolean authenticate(String login, String pass) throws AuthenticationException;
    List<Operator> findAll();
    boolean operatorBlocking(String login);
    Operator findByLogin(String login);
}
