package exceptions;

public class RuntimeCollisionException extends RuntimeException {
    
    public RuntimeCollisionException(String message) {
        super(message);
    }
    
    @Override
    public String getMessage() {
        return "Столкновение в рантайме: " + super.getMessage();
    }
}