package exceptions;

public class CollisionException extends Exception {

    public CollisionException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Столкновение: " + super.getMessage();
    }
}
