package exceptions;

public class InvalidActionException extends Exception {
    
    public InvalidActionException(String message) {
        super(message);
    }
    
    @Override
    public String getMessage() {
        return "Недопустимое действие: " + super.getMessage();
    }
}