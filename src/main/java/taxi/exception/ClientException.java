package taxi.exception;

public class ClientException extends Exception {

    public ClientException() {
        super();
    }

    public ClientException(String text){
        super(text);
    }
}
