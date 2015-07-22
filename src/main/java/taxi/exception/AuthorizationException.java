package taxi.exception;

public class AuthorizationException extends Exception {

    public AuthorizationException() {
        super();
    }

    public AuthorizationException(String text){
        super(text);
    }
}
