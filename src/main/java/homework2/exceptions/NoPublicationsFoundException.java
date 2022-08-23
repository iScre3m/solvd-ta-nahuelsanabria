package homework2.exceptions;

public class NoPublicationsFoundException extends RuntimeException {
    public NoPublicationsFoundException(String message) {
        super(message);
    }
}
