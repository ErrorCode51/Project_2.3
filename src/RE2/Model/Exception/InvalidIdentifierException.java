package RE2.Model.Exception;

// Custom Exception to ensure created stones always have a expected identifier
public class InvalidIdentifierException extends RuntimeException {

    public InvalidIdentifierException(String message) {

        super(message);

    }

}