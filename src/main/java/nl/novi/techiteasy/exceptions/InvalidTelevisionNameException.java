package nl.novi.techiteasy.exceptions;

public class InvalidTelevisionNameException extends RuntimeException {
    public InvalidTelevisionNameException() {
        super("Invalid television name");
    }

    public InvalidTelevisionNameException(String message) {
        super(message);
    }
}
