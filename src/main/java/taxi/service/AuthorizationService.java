package taxi.service;

import taxi.exception.AuthorizationException;

public interface AuthorizationService {

    boolean register(String login, String id, String pass) throws AuthorizationException;

}
