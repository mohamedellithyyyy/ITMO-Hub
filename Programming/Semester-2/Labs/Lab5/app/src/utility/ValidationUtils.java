package utility;
import exceptions.InvalidInputException;

public class ValidationUtils {
    public static void validateNotNull(Object value, String fieldName) {
        if (value == null) {
            throw new InvalidInputException(fieldName + " cannot be null");
        }
    }
    public static void validateNotEmpty(String value, String fieldName) {
        if (value == null || value.isEmpty()){
            throw new InvalidInputException(fieldName + " cannot be empty");
        }
    }
    public static void validatePositive(long value, String fieldName) {
        if (value <= 0) {
            throw new InvalidInputException(fieldName + " cannot be less than 0");
        }
    }
    public static void validateMaxY(Long value, String fieldName) {
        if (value > 433) {
            throw new InvalidInputException(fieldName + " cannot be greater than 433");
        }
    }

}