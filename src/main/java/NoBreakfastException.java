public class NoBreakfastException extends RuntimeException {

    //constructors
    public NoBreakfastException() {
        super();
    }

    public NoBreakfastException(String message) {
        super(message);
    }

    public NoBreakfastException(String message, Throwable error) {
        super("Error: Breakfast fail! " + message, error );
    }

}
