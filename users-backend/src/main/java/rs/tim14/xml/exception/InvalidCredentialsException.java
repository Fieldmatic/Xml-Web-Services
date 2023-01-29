package rs.tim14.xml.exception;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException() {
        super("Neispravni kredencijali!");
    }
}
