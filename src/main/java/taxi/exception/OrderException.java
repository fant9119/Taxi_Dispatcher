package taxi.exception;

public class OrderException extends Exception {

    public OrderException() {
        super();
    }

    public OrderException(String text){
        super(text);
    }
}
