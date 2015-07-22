package taxi.exception;

public class AuthenticationException extends Exception {

    public AuthenticationException() {
        super();
    }

    public AuthenticationException(String text){
        super(text);
    }

}
